import java.io.*;

public class BOJ1699_제곱수의합 {

    public static void main(String[] args) throws Exception {
     
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int [] dp = new int[100001];

        for(int i = 1; i<=n ; i++){
            dp[i] = i;
        }

        for(int i = 1; i<=n; i++){
            for(int j = 1; j * j <= i; j++){
                if(dp[i] > dp[i - (j*j)] +1) dp[i] = dp[i-(j*j)] + 1; 
            }
        }

        System.out.println(dp[n]);
    }

}