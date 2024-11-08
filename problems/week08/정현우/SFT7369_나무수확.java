import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : SFT 7369 나무 수확
 * - 305 ms
 * - DP
 * - (i, j) 에서 수로가 끝날 때
 * - dp[i][j] : 스프링클러 없이 최대 수확량
 * - sprinkler[i][j] : 스프링클러 포함 최대 수확량
 * - dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
 * - sprinkler[i][j]
 * -   = max(sprinkler[i - 1][j], sprinkler[i][j - 1],
 * -     dp[i][j] + ((i, j) 수확량))
 * - (i, j) 가 (i - 1, j) 와 (i, j - 1) 만 참조하므로
 * -   배열 1 차원으로 변환
 * */
public class SFT7369_나무수확 {
    public static void main(String[] args) throws IOException {
        int n;
        int i;
        int j;
        int val;
        int[] dp;
        int[] sprinkler;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1]; // 스프링클러 없이 최대 수확량
        sprinkler = new int[n + 1]; // 스프링클러 포함 최대 수확량
        for (i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            for (j = 1; j <= n; j++) {
                val = Integer.parseInt(st.nextToken()); // (i, j) 수확량
                dp[j] = Math.max(dp[j], dp[j - 1]) + val; // max(dp[i - 1][j], dp[i][j - 1])
                sprinkler[j] = Math.max(Math.max(sprinkler[j], sprinkler[j - 1]), dp[j]) + val;
            } // max(sprinkler[i - 1][j], sprinkler[i][j - 1], dp[i][j] + ((i, j) 수확량))
        }
        System.out.println(sprinkler[n]);
    }
}
