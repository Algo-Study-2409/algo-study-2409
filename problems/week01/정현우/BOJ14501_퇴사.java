import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 14501 퇴사
 * - 64 ms
 * - DP
 * - 1일 ~ N일 상담, N + 1일 퇴사
 * - -> 0일 ~ N - 1일 상담, N일 퇴사
 * - dp[i] = i일 전까지 최대 금액 합
 * - i일의 상담을 진행하는 경우 dp[i + T] 갱신
 * - i일의 상담을 진행하지 않는 경우 dp[i + 1] 갱신
 * - 퇴사 전까지 최대 금액 합 dp[N]
 */
public class BOJ14501_퇴사 {
	public static void main(String[] args) throws IOException {
		int n;
		int t;
		int i;
		int[] dp;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new int[n + 1]; // dp[i] = i일 전까지 최대 금액 합
		for (i = 0; i < n; i++) { // 0일 ~ N - 1일 상담, N일 퇴사
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			if (i + t <= n) { // i일의 상담을 진행하는 경우
				dp[i + t] = Math.max(dp[i + t], dp[i] + Integer.parseInt(st.nextToken())); // dp[i + T] 갱신
			}
			dp[i + 1] = Math.max(dp[i + 1], dp[i]); // i일의 상담을 진행하지 않는 경우 dp[i + 1] 갱신
		}
		System.out.print(dp[n]); // 퇴사 전까지 최대 금액 합 dp[N]
	}
}
