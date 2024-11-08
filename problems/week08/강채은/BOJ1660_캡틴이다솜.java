import java.util.*;
import java.io.*;

public class BOJ1660_캡틴이다솜 {
    public static void main(String [] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int maxNum = 0;

        int [] dp = new int[n+1];

        for(int i = 1; i<=n; i++){
            
            int num = i;
            int result = 0 ;

            while(num > 0) {
                result+=num;
                num -- ;
            }
            
            if(dp[i-1]+result > n) break;
            dp[i] = dp[i-1]+result;
            maxNum = Math.max(maxNum,i);
        }
        
        int[] dp2 = new int[n + 1];
        Arrays.fill(dp2, Integer.MAX_VALUE);
        dp2[0] = 0;
        dp2[1] = 1;
        
        for (int i = 2 ; i <= n ; i++) {
            for (int j = 1 ; j <= maxNum ; j++) {
                if (dp[j] > i) break;
                dp2[i] = Math.min(dp2[i], dp2[i - dp[j]] + 1);
            }
        }
        System.out.println(dp2[n]);

    }
}
