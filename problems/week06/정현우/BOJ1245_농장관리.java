import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1245 농장 관리
 * - 84 ms
 * - DFS
 * - map 1 차원 변환
 * - 8방 탐색
 * - 현재 높이보다 높은 칸이 있으면
 * -   isPeak 를 false 로 표시
 * - 현재 높이와 같은 칸
 * -   해당 칸으로 DFS 탐색
 * - isPeak 이 true 로 남아있으면
 * -   봉우리 개수 추가
 * */
public class BOJ1245_농장관리 {
    private static final int WALL = -1;

    private static int[] d;
    private static int[] map;
    private static boolean isPeak;
    private static boolean[] visited;

    private static final void dfs(int pos) {
        int i;
        int npos;

        visited[pos] = true; // 방문 처리
        for (i = 0; i < 8; i++) { // 8방 탐색
            npos = pos + d[i];
            if (isPeak && map[npos] > map[pos]) { // 현재 높이보다 높은 칸이 있으면
                isPeak = false; // isPeak 를 false 로 표시
            } else if (map[npos] == map[pos] && !visited[npos]) { // 현재 높이와 같은 칸
                dfs(npos); // 해당 칸으로 DFS 탐색
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int j;
        int col;
        int thr;
        int cnt;
        int size;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        col = m + 2;
        thr = (n + 1) * col;
        size = thr + col;
        map = new int[size]; // map 1 차원 변환
        for (i = 0; i < col; i++) {
            map[i] = WALL; // 위쪽 벽
        }
        for (i = col; i < thr; i += col) {
            map[i] = WALL; // 왼쪽 벽
            st = new StringTokenizer(br.readLine(), " ", false);
            for (j = 1; j <= m; j++) { // 농장 입력
                map[i + j] = Integer.parseInt(st.nextToken());
            }
            map[i + m + 1] = WALL; // 오른쪽 벽
        }
        System.arraycopy(map, 0, map, thr, col); // 아래쪽 벽
        visited = new boolean[size]; // 방문 여부
        d = new int[] {-col, -col + 1, 1, col + 1, col, col - 1, -1, -col - 1}; // 8방 탐색
        cnt = 0;
        for (i = col; i < thr; i += col) {
            for (j = 1; j <= m; j++) {
                if (!visited[i + j]) { // 방문하지 않은 노드
                    isPeak = true; // isPeak 초기화
                    dfs(i + j); // DFS
                    if (isPeak) { // isPeak 이 true 로 남아있으면
                        cnt++; // 봉우리 개수 추가
                    }
                }
            }
        }
        System.out.print(cnt);
    }
}
