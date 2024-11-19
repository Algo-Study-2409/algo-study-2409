import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : SFT 6246 순서대로 방문하기
 * - 71 ms
 * - DFS
 * - map 1 차원 변환
 * - 방문해야 하는 위치에 순번 부여
 * - 현재 찾는 순번보다 큰 위치는 방문 하지 않음
 * - 현재 찾는 순번과 일치하면 다음 순번 찾기
 * - 마지막 위치 찾으면 경로 개수 1 증가
 * */
public class SFT6246_순서대로방문하기 {
    private static final int WALL = '1';

    private static int col;
    private static int cnt;
    private static int end;
    private static int[] map;

    private static final void dfs(int pos, int num) {
        int temp;

        if (map[pos] > num) { // 현재 찾는 순번보다 큰 위치
            return; // 방문 하지 않음
        }
        if (pos == end) { // 마지막 위치 찾으면
            cnt++; // 경로 개수 1 증가
            return;
        }
        if (map[pos] == num) { // 현재 찾는 순번과 일치
            num++; // 다음 순번 찾기
        }
        temp = map[pos];
        map[pos] = WALL; // 방문 처리
        dfs(pos - col, num); // 4방 탐색
        dfs(pos + 1, num);
        dfs(pos + col, num);
        dfs(pos - 1, num);
        map[pos] = temp; // 원상 복구
    }

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int j;
        int thr;
        int start;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        col = n + 2;
        thr = (n + 1) * col;
        map = new int[col * col]; // map 1 차원 변환
        for (i = 1; i <= n; i++) { // 위쪽 벽 세우기
            map[i] = WALL;
        }
        for (i = col; i < thr; i += col) {
            map[i] = WALL; // 왼쪽 벽 세우기
            for (j = 1; j <= n; j++) {
                if (br.read() == WALL) { // 벽 위치
                    map[i + j] = WALL;
                }
                br.read();
            }
            map[i + j] = WALL; // 오른쪽 벽 세우기
        }
        System.arraycopy(map, 1, map, thr + 1, n); // 아래쪽 벽 세우기
        m--;
        st = new StringTokenizer(br.readLine(), " ", false); // 시작점
        start = Integer.parseInt(st.nextToken()) * col + Integer.parseInt(st.nextToken());
        for (i = 1; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ", false); // 순번 부여
            map[Integer.parseInt(st.nextToken()) * col + Integer.parseInt(st.nextToken())] = i;
        }
        st = new StringTokenizer(br.readLine(), " ", false); // 끝점
        end = Integer.parseInt(st.nextToken()) * col + Integer.parseInt(st.nextToken());
        map[end] = (char) m; // 마지막 순번
        cnt = 0;
        dfs(start, 1); // DFS
        System.out.print(cnt); // 경로 개수 출력
    }
}
