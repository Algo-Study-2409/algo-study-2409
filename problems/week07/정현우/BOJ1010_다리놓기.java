import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1010 다리 놓기
 * - 68 ms
 * - DP
 * - M >= N
 * - 다리를 지을 수 있는 경우의 수 : (M)C(N)
 * - (M)C(N) = (M)C(M - N)
 * - N > (M / 2) 이면 N = M - N
 * - 30C15 = 155,117,520 < Integer.MAX_VALUE
 * - 15 ~ 30 까지 곱 > LONG.MAX_VALUE
 * - (M)C(N) = (M - 1)C(N - 1) + (M - 1)C(N)
 * - (N)C(N) = 1, (M)C0 = 1
 * - Top-Down DP
 * */
public class BOJ1010_다리놓기 {
    private static final int MAX_M = 30;
    private static final char LINE_BREAK = '\n';

    private static int[][] dp;

    private static final int getDp(int m, int n) { // Top-Down DP
        if (dp[m][n] != 0) { // 이미 계산된 값
            return dp[m][n];
        }
        if (m == n || n == 0) { // (N)C(N) = 1, (M)C0 = 1
            return dp[m][n] = 1;
        }
        return dp[m][n] = getDp(m - 1, n - 1) + getDp(m - 1, n); // (M)C(N) = (M - 1)C(N - 1) + (M - 1)C(N)
    }

    public static void main(String[] args) throws IOException {
        int t;
        int n;
        int m;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        dp = new int[MAX_M + 1][MAX_M + 1];
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine(), " ", false);
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n > m >> 1) { // N > (M / 2)
                n = m - n; // (M)C(N) = (M)C(M - N)
            }
            sb.append(getDp(m, n)).append(LINE_BREAK); // (M)C(N) 출력
        }
        System.out.println(sb.toString());
    }
}
