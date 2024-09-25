import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 15686 치킨 배달
 * - 76 ms
 * - DFS
 * - Dist(치킨집, 거리) 객체 생성
 * - dists[i][x] = Dist(j, dist)
 * - -> 집 i와 치킨집 j 사이 거리가 dist
 * - 각 dists[i]들을 dist 오름차순 정렬
 * - 치킨집 선택하면서 DFS 조합
 * - M 개의 치킨집 선택 시
 * -   각 dists[i]들에서 가장 앞의 선택된 치킨집의 dist 합 계산
 * - 최소값 갱신
 * */
public class BOJ15686_치킨배달 {
    private static final int MAX_M = 13;
    private static final int EMPTY = '0';
    private static final int HOME = '1';
    private static final int CHICKEN = '2';
    private static final int INF = Integer.MAX_VALUE;

    private static final class Dist implements Comparable<Dist> {
        int idx;
        int dist;

        Dist(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Dist o) {
            return Integer.compare(dist, o.dist);
        }
    }

    private static int n;
    private static int m;
    private static int min;
    private static int homeIdx;
    private static int chickenIdx;
    private static boolean[] selected;
    private static Dist[][] dists;

    private static int getDist(int pos1, int pos2) { // 두 좌표 사이 거리 계산
        return Math.abs(pos1 / n - pos2 / n) + Math.abs(pos1 % n - pos2 % n);
    }

    private static void dfs(int start, int depth) {
        int i;
        int sum;

        if (depth == m) { // M 개의 치킨집 선택 시
            sum = 0;
            for (i = 0; i < homeIdx; i++) { // 각 dists[i]들에서
                for (Dist dist : dists[i]) {
                    if (selected[dist.idx]) { // 가장 앞의 선택된 치킨집
                        sum += dist.dist; // dist 합 계산
                        break;
                    }
                }
            }
            if (sum < min) { // 최소값 갱신
                min = sum;
            }
            return;
        }
        depth++;
        for (i = start; i < chickenIdx; i++) { // DFS 조합
            selected[i] = true; // 치킨집 선택
            dfs(i + 1, depth); // 다음 depth
            selected[i] = false; // 선택 해제
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        int j;
        int size;
        int home;
        int[] homes;
        int[] chickens;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        size = n * n;
        homes = new int[n << 1];
        chickens = new int[MAX_M];
        switch (br.read()) { // (0, 0) 입력
            case EMPTY:
                break;
            case HOME:
                homes[homeIdx++] = 0;
                break;
            case CHICKEN:
                chickens[chickenIdx++] = 0;
                break;
        }
        for (i = 1; i < size; i++) { // (0, 1) ~ (N - 1, N - 1) 입력
            br.read();
            switch (br.read()) {
                case EMPTY:
                    break;
                case HOME:
                    homes[homeIdx++] = i; // 집 좌표
                    break;
                case CHICKEN:
                    chickens[chickenIdx++] = i; // 치킨집 좌표
                    break;
            }
        }
        dists = new Dist[homeIdx][chickenIdx];
        for (i = 0; i < homeIdx; i++) {
            home = homes[i]; // 집 i
            for (j = 0; j < chickenIdx; j++) { // 치킨집 j
                dists[i][j] = new Dist(j, getDist(home, chickens[j])); // 집 i와 치킨집 j 사이 거리
            }
            Arrays.sort(dists[i]); // 각 dists[i]들을 dist 오름차순 정렬
        }
        min = INF;
        selected = new boolean[chickenIdx];
        dfs(0, 0); // DFS
        System.out.print(min);
    }
}
