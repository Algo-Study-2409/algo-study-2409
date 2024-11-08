import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 정현우 : SFT 7699 Hanyang Cherry Picking Contest
 * - 780 ms
 * - 게임 이론 + 그리디 + 트리 DP
 * - 항상 DFS 순서로 정점이 선택됨
 * -   = 정점을 선택하면 해당 정점을 루트로 하는 서브 트리가 전부 끝난 후 돌아옴
 * - 정점의 가치 : 해당 정점을 루트로 하는 서브 트리를 모두 선택했을 때
 * -   (내가 얻는 점수) - (상대가 얻는 점수)
 * - 선택할 수 있는 상황 : 특정 부모 정점의 자식 정점들 중에 하나를 선택
 * - 정점 타입
 * -   A. 정점 선택 후 서브 트리에 대한 선택이 끝났을 때 다시 내 턴인 정점
 * -     A1. 정점의 가치가 양수
 * -     A2. 정점의 가치가 음수 (가치 0 은 의미 없음, 임의로 A2 에 포함)
 * -   B. 정점 선택 후 서브 트리에 대한 선택이 끝났을 때 턴이 전환되는 정점
 * - 최적의 선택
 * -   1. 처음 선택권을 가진 플레이어(부모 정점 선택자의 상대편)가 A1 타입들을 모두 선택
 * -   2. B 타입들을 가치가 높은 것부터 번갈아가며 선택
 * -   3. 마지막에 턴을 받은 플레이어가 A2 타입들을 모두 선택
 * -   자식 정점을 A1 - B - A2 순으로 정렬 후 B 타입을 만날 때마다 턴 전환하며 하나씩 반영
 * - 부모 정점의 가치
 * -   (부모 정점에 쓰인 숫자)
 * -     - (부모 정점 선택자의 상대편이 선택하게 된 자식 정점의 가치 합)
 * -     + (부모 정점 선택자가 선택하게 된 자식 정점의 가치 합)
 * - 정점 1 의 가치가 양수면 세훈 승, 음수면 철민 승, 0 이면 비김
 * */
public class SFT7699_HanyangCherryPickingContest {
    private static final int NIL = 0;
    private static final char[] SEHUN = {'S', 'e', 'h', 'u', 'n'};
    private static final char[] CHEOLMIN = {'C', 'h', 'e', 'o', 'l', 'm', 'i', 'n'};
    private static final char[] DRAW = {'D', 'r', 'a', 'w'};
    private static final Comparator CMP = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) { // 자식 정점 정렬 기준
            if (o1 == exclude) { // 제외된 정점(부모 정점) 가장 뒤로 보내기
                return 1;
            }
            if (o2 == exclude) { // 제외된 정점(부모 정점) 가장 뒤로 보내기
                return -1;
            }
            if (!isChange[o1]) { // A 타입
                if (score[o1] > 0L) { // A1 타입
                    return -1; // 가장 앞으로
                } else { // A2 타입
                    return 1; // 가장 뒤로
                }
            }
            if (!isChange[o2]) { // A 타입
                if (score[o2] > 0L) { // A1 타입
                    return 1; // 가장 앞으로
                } else { // A2 타입
                    return -1; // 가장 뒤로
                }
            }
            if (score[o1] > score[o2]) { // B 타입 : 정점의 가치 내림차순
                return -1;
            }
            return 1;
        }
    };

    private static int exclude;
    private static long[] score;
    private static boolean[] isChange;
    private static ArrayList<Integer>[] adj;

    private static final void dfs(int parent, int curr) {
        int i;
        int child;
        int degree;
        boolean flag;
        ArrayList<Integer> children;

        children = adj[curr]; // 자식 정점들
        degree = children.size() - 1; // 진출 차수 (부모 제외)
        for (int next : children) {
            if (next == parent) {
                continue;
            }
            dfs(curr, next); // 자식 정점 가치 계산
        }
        exclude = parent; // 부모 정점 제외
        Collections.sort(children, CMP); // 정점 정렬
        flag = true; // 시작은 부모 정점을 선택한 플레이어의 상대편 턴
        for (i = 0; i < degree; i++) { // 자식 정점 순회
            child = children.get(i); // 자식 정점
            if (flag) { // 부모 정점을 선택한 플레이어의 상대편 턴
                score[curr] -= score[child]; // 부모 정점의 가치 -= 자식 정점의 가치
            } else { // 부모 정점을 선택한 플레이어의 턴
                score[curr] += score[child]; // 부모 정점의 가치 += 자식 정점의 가치
            }
            flag ^= isChange[child]; // 자식 정점이 B 타입이면 턴 전환
        }
        isChange[curr] = flag; // 마지막 턴을 가져간 플레이어에 따라 부모 정점의 타입 결정
    }

    public static void main(String[] args) throws IOException {
        int n;
        int u;
        int v;
        int i;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        for (i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (i = 1; i < n; i++) { // 간선 입력
            st = new StringTokenizer(br.readLine(), " ", false);
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        score = new long[n + 1];
        st = new StringTokenizer(br.readLine(), " ", false);
        for (i = 1; i <= n; i++) {
            score[i] = Long.parseLong(st.nextToken());
        }
        isChange = new boolean[n + 1]; // 선택했을 때 턴이 전환되는 B 타입 정점인지
        adj[1].add(NIL); // 정점 1 에 임의의 0 번 정점을 부모로 추가
        dfs(NIL, 1); // 부모 0, 정점 1 에서 DFS 출발
        if (score[1] > 0L) { // 정점 1 의 가치가 양수
            System.out.print(SEHUN);
        } else if (score[1] < 0L) { // 정점 1 의 가치가 음수
            System.out.print(CHEOLMIN);
        } else { // 정점 1 의 가치가 0
            System.out.print(DRAW);
        }
    }
}
