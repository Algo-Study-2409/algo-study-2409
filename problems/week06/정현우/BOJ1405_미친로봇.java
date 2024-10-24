import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1405 미친 로봇
 * - 108 ms
 * - 브루트포스
 * - (2N + 1) * (2N + 1) map
 * - map 1 차원 변환
 * - (N, N) 출발
 * - 모든 경로 4방 탐색
 * - 방문한 지점은 재방문 하지 않음
 * - 현재 경로가 선택될 확률 계산
 * - N 길이 경로의 확률 합 출력
 * */
public class BOJ1405_미친로봇 {
    private static final double TOTAL = 100.0;

    private static int n;
    private static int col;
    private static double ans;
    private static double east;
    private static double west;
    private static double south;
    private static double north;
    private static boolean[] visited;

    private static void dfs(int pos, double probability, int depth) {
        if (visited[pos]) { // 이미 방문한 지점
            return;
        }
        if (depth == n) { // N 길이 경로
            ans += probability; // 확률 합 계산
            return;
        }
        depth++; // 다음 depth
        visited[pos] = true; // 방문 처리
        dfs(pos + 1, probability * east, depth); // 4방 탐색
        dfs(pos - 1, probability * west, depth); // 현재 경로 확률 * 방향별 확률
        dfs(pos + col, probability * south, depth);
        dfs(pos - col, probability * north, depth);
        visited[pos] = false; // 방문 여부 원상 복구
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        east = Integer.parseInt(st.nextToken()) / TOTAL; // 동
        west = Integer.parseInt(st.nextToken()) / TOTAL; // 서
        south = Integer.parseInt(st.nextToken()) / TOTAL; // 남
        north = Integer.parseInt(st.nextToken()) / TOTAL; // 북
        col = (n << 1) + 1; // 2N + 1
        visited = new boolean[col * col]; // map 1 차원 변환
        dfs(n * col + n, 1.0, 0); // (N, N) 출발
        System.out.print(ans); // 확률 합 출력
    }
}
