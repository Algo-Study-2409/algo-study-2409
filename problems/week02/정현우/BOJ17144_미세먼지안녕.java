import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 BOJ 17144 미세먼지 안녕!
 * - 172 ms
 * - 구현
 * - map 1 차원 변환
 * - 미세먼지 확산
 * -   4방 탐색
 * -   diff[][]에 확산값 저장 후 확산값 일괄 적용
 * - 공기청정기 작동
 * -   흡입구부터 배출구 까지 흡입 방향으로 한 칸씩 이동
 * -   배출구 미세먼지 0
 * */
public class BOJ17144_미세먼지안녕 {
    private static final int WALL = Integer.MAX_VALUE;

    private static int r;
    private static int c;
    private static int ap1;
    private static int ap2;
    private static int thr;
    private static int col;
    private static int[] d;
    private static int[] map;
    private static int[] diff;

    private static void diffuse(int pos) { // 1 칸 확산
        int i;
        int val;
        int npos;

        val = map[pos] / 5; // 한 칸당 확산량
        for (i = 0; i < 4; i++) { // 4방 탐색
            npos = pos + d[i]; // 인접 칸
            if (map[npos] != WALL) { // 벽이 아니면
                map[pos] -= val; // 확산
                diff[npos] += val; // 확산값 저장
            }
        }
    }

    private static void diffuseAll() { // 미세먼지 확산
        int i;
        int j;

        for (i = col + 1; i < ap1; i += col) { // 1 열 (공기청정기 위)
            diffuse(i); // 확산
        }
        for (i = ap2 + col; i < thr; i += col) { // 1 열 (공기청정기 아래)
            diffuse(i); // 확산
        }
        for (i = col; i < thr; i += col) { // 전체 행
            for (j = 2; j <= c; j++) { // 2 열 ~ C 열
                diffuse(i + j); // 확산
            }
        }
        for (i = col; i < thr; i += col) { // 전체 행
            for (j = i + 1; j <= i + c; j++) { // 전체 열
                map[j] += diff[j]; // 확산값 적용
                diff[j] = 0; // 확산값 초기화
            }
        }
    }

    private static void purify() { // 공기청정기 작동
        int i;

        for (i = ap1 - col; i > col + 1; i -= col) { // 1 열 (공기청정기 위)
            map[i] = map[i - col];
        }
        System.arraycopy(map, col + 2, map, col + 1, c - 1); // 1 행
        for (i = col + c; i < ap1; i += col) { // C 열 (공기청정기 위)
            map[i] = map[i + col];
        }
        System.arraycopy(map, ap1 + 1, map, ap1 + 2, c - 2); // 공기청정기 첫 번째 행
        map[ap1 + 1] = 0; // 공기청정기 첫 번째 배출구
        for (i = ap2 + col; i < thr - col; i += col) { // 1 열 (공기청정기 아래)
            map[i] = map[i + col];
        }
        System.arraycopy(map, r * col + 2, map, r * col + 1, c - 1); // R 행
        for (i = r * col + c; i > ap2 + col; i -= col) { // C 열 (공기청정기 아래)
            map[i] = map[i - col];
        }
        System.arraycopy(map, ap2 + 1, map, ap2 + 2, c - 2); // 공기청정기 두 번째 행
        map[ap2 + 1] = 0; // 공기청정기 두 번째 배출구
    }

    public static void main(String[] args) throws IOException {
        int t;
        int i;
        int j;
        int cnt;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        col = c + 2;
        thr = (r + 1) * col;
        map = new int[(r + 2) * col]; // map 1 차원 변환
        for (i = 1; i <= c; i++) { // 0 행 벽
            map[i] = WALL;
        }
        for (i = col; i < thr; i += col) {
            map[i] = WALL; // 0 열 벽
            st = new StringTokenizer(br.readLine(), " ", false);
            map[i + 1] = Integer.parseInt(st.nextToken()); // 1 열 입력
            if (map[i + 1] == -1) { // 공기청정기
                map[i + 1] = WALL; // 공기청정기 벽 취급
                ap2 = i + 1; // 공기청정기 두 번째 칸 좌표
            }
            for (j = 2; j <= c; j++) { // 2 열 ~ C 열 입력
                map[i + j] = Integer.parseInt(st.nextToken());
            }
            map[i + c + 1] = WALL; // C + 1 열 벽
        }
        ap1 = ap2 - col; // 공기청정기 첫 번째 칸 좌표
        System.arraycopy(map, 1, map, (r + 1) * col + 1, c); // R + 1 행 벽
        diff = new int[(r + 1) * col]; // 확산값
        d = new int[] {-col, 1, col, -1}; // 4방 탐색
        while (t-- > 0) {
            diffuseAll(); // 미세먼지 확산
            purify(); // 공기청정기 작동
        }
        map[ap1] = 0; // 공기청정기 칸 미세먼지 0
        map[ap2] = 0;
        cnt = 0;
        for (i = col; i < thr; i += col) {
            for (j = 1; j <= c; j++) {
                cnt += map[i + j]; // 미세먼지 합
            }
        }
        System.out.print(cnt);
    }
}
