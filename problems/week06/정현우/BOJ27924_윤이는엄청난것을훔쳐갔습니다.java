import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 27924 윤이는 엄청난 것을 훔쳐갔습니다
 * - 428 ms
 * - BFS
 * - b, c, a 에서 각각 BFS로 출발
 * - a 에서 출발한 탐색이
 * -   리프 노드에 도달하면 YES 출력
 * - a 에서 출발한 탐색인지 확인
 * -   노드 번호를 IS_A flag 와 OR 연산하여 표시
 * - IS_A = 가장 왼쪽 비트만 1 인 int 로 설정
 * */
public class BOJ27924_윤이는엄청난것을훔쳐갔습니다 {
    private static final int IS_A = Integer.MIN_VALUE; // 가장 왼쪽 비트만 1 인 int
    private static final char[] YES = {'Y', 'E', 'S'};
    private static final char[] NO = {'N', 'O'};

    private static final class Edge {
        int to;
        Edge next;

        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }

    private static int n;
    private static Edge[] adj;

    private static final boolean bfs(int a, int b, int c) {
        int curr;
        int flag;
        boolean[] visited;
        Edge edge;
        ArrayDeque<Integer> q;

        visited = new boolean[n + 1];
        q = new ArrayDeque<>(n);
        visited[a] = visited[b] = visited[c] = true;
        q.addLast(b); // b, c 를 a 보다 먼저 출발 시킴
        q.addLast(c);
        q.addLast(a | IS_A); // a 에서 출발 표시
        while (!q.isEmpty()) {
            curr = q.pollFirst();
            flag = curr & IS_A; // flag : a 에서 출발한 탐색인지 여부
            curr ^= flag; // flag 제거하여 노드 번호만 추출
            if (adj[curr].next == null && flag == IS_A) {
                return true; // a 에서 출발한 탐색이 리프 노드에 도달
            }
            for (edge = adj[curr]; edge != null; edge = edge.next) {
                if (!visited[edge.to]) {
                    visited[edge.to] = true; // 다음 노드
                    q.addLast(flag | edge.to); // flag 와 OR 연산하여 표시
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        int u;
        int v;
        int i;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new Edge[n + 1];
        for (i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            adj[u] = new Edge(v, adj[u]); // 양방향 간선 입력
            adj[v] = new Edge(u, adj[v]);
        }
        st = new StringTokenizer(br.readLine(), " ", false);
        if (bfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
            System.out.print(YES); // BFS 가 true 반환
        } else {
            System.out.print(NO); // BFS 가 false 반환
        }
    }
}
