import java.util.*;
import java.io.*;

public class BOJ1010_다리놓기{

    private static int [][] dp;
    private static StringBuilder sb;

    private static int combi(int n, int r){

        if(dp[n][r] > 0 ) return dp[n][r];
        
        if(n == r || r == 0) return dp[n][r] = 1;

        return dp[n][r] = combi(n-1, r) + combi(n-1, r-1);
    }
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        dp = new int[30][30];
    
        StringTokenizer st;
        while(T -- > 0){

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            sb.append(combi(m, n)).append("\n");

        }

        System.out.println(sb.toString().trim());

    }

}
