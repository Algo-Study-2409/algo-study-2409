import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 14503 로봇 청소기
 * - 64 ms
 * - map 1 차원 변환
 * - 청소되지 않은 빈 칸을 찾을 때까지 반시계 회전
 * - 청소되지 않은 빈 칸 찾으면 전진 후 청소
 * - 못 찾으면 후진, 벽이면 작동 멈춤
 * */
public class BOJ14503_로봇청소기 {
    private static final char WALL = '1';
    private static final char EMPTY = '0';

    private static int clean(int pos, int d, int[] delta, char[] map) {
        int i;
        int cnt;

        map[pos]--; // 시작 칸 청소
        cnt = 1; // 청소된 칸 = 1
        for (;;) {
            for (i = 4; i != 0 && map[pos + delta[d = (d + 3) & 3]] != EMPTY; i--); // 청소 되지 않은 빈 칸을 찾을 때까지 반시계 회전
            if (i != 0) { // 청소되지 않은 빈 칸 찾음
                map[pos += delta[d]]--; // 전진 후 청소
                cnt++; // 청소된 칸 1 증가
            } else if (map[pos += delta[(d + 2) & 3]] == WALL) { // 못 찾으면 후진, 벽이면
                break; // 작동 멈춤
            }
        }
        return cnt; // 청소된 칸
    }

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int r;
        int c;
        int d;
        char[] map;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()) << 1;
        st = new StringTokenizer(br.readLine(), " ", false);
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new char[n * m];
        br.read(map, 0, n * m - 1); // map 1 차원 변환
        System.out.print(clean(r * m + (c << 1), d, new int[] {-m, 2, m, -2}, map));
    }
}
