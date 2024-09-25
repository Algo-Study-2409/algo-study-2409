import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1182 부분수열의 합
 * - 80 ms
 * - 비트필드 DP
 * - 1 ~ (1 << N) - 1 비트마스크 부분집합
 * - dp[i] = 숫자 i가 나타내는 부분집합의 숫자들의 합
 * - dp[i] = dp[i - lowestOneBit(i)] + dp[lowestOneBit(i)]
 * - 값이 S인 dp[i]의 개수
 * */
public class BOJ1182_부분수열의합 {
    public static void main(String[] args) throws IOException {
        int n;
        int s;
        int i;
        int cnt;
        int[] dp;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = 1 << Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        dp = new int[n];
        st = new StringTokenizer(br.readLine(), " ", false);
        for (i = 1; i < n; i <<= 1) { // 1비트가 하나인 위치듫에 각 숫자 삽입
            dp[i] = Integer.parseInt(st.nextToken());
        }
        cnt = 0;
        for (i = 1; i < n; i++) { // 1 ~ (1 << N) - 1 비트마스크 부분집합
            if ((dp[i] = dp[i ^ (i & -i)] + dp[i & -i]) == s) { // dp[i] == S 이면
                cnt++; // 경우의 수 증가
            } // dp[i] = dp[i - lowestOneBit(i)] + dp[lowestOneBit(i)]
        }
        System.out.print(cnt); // 값이 S인 dp[i]의 개수
    }
}
