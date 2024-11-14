import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 10815 숫자 카드
 * - 592 ms
 * - 구현
 * - -10,000,000 ~ 10,000,000 을 0 ~ 20,000,000 으로 변환
 * - contains 배열 숫자 카드에 해당하는 위치 true
 * - 숫자 위치가 true 이면 1, false 이면 0 출력
 * */
public class BOJ10815_숫자카드 {
    private static final int DIFF = 10_000_000;
    private static final char TRUE = '1';
    private static final char FALSE = '0';
    private static final char SPACE = ' ';

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        boolean[] contains;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        contains = new boolean[(DIFF << 1) + 1]; // 0 ~ 20,000,000 으로 변환
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ", false);
        while (n-- > 0) { // contains 배열 숫자 카드에 해당하는 위치 true
            contains[Integer.parseInt(st.nextToken()) + DIFF] = true;
        }
        m = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ", false);
        while (m-- > 0) { // 숫자 위치가 true 이면 1, false 이면 0 출력
            if (contains[Integer.parseInt(st.nextToken()) + DIFF]) {
                sb.append(TRUE).append(SPACE);
            } else {
                sb.append(FALSE).append(SPACE);
            }
        }
        System.out.print(sb.toString());
    }
}
