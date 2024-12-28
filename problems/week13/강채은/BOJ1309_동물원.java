import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1309_동물원 {

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        final int DIV = 9901;
        
        int [][] dp = new int[n+1][3];
        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for(int i = 2; i<=n; i++){
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % DIV;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % DIV;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % DIV;

        }

        int ans = (dp[n][0] + dp[n][1] + dp[n][2]) % DIV;
        System.out.println(ans);

    }

}