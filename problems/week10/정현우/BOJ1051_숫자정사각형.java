import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1051 숫자 정사각형
 * - 64 ms
 * - 브루트포스
 * - 만들 수 있는 가장 큰 정사각형부터
 * - 크기 줄여가며 탐색
 * - 정사각형 발견 시 크기 출력
 * */
public class BOJ1051_숫자정사각형 {
    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int j;
        int row;
        int col;
        int size;
        char ch;
        char[][] map;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][++m];
        for (i = 0; i < n; i++) { // 직사각형 입력
            br.read(map[i], 0, m);
        }
        m--;
        for (size = Math.min(n, m) - 1; size >= 0; size--) { // size : 크기 - 1
            row = n - size; // 탐색할 행 한계
            for (i = 0; i < row; i++) {
                col = m - size; // 탐색할 열 한계
                for (j = 0; j < col; j++) {
                    ch = map[i][j]; // 정사각형의 왼쪽 위
                    if (map[i + size][j] == ch && map[i][j + size] == ch && map[i + size][j + size] == ch) {
                        System.out.print((size + 1) * (size + 1)); // 정사각형 발견 시 크기 출력
                        return;
                    }
                }
            }
        }
    }
}
