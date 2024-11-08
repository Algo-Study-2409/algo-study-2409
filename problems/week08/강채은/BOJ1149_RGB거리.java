import java.util.*;
import java.io.*;

public class BOJ1149_RGB거리 {

    private static final int color = 3;
    private static int N;
    
    private static int [][] cost;
    private static int [][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); //집 개수

        cost = new int[N][color]; //집을 칠하는 비용
        dp = new int[N][color]; //집을 칠하는 최소 누적 비용

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0 ; j<color; j++) {
                int num = Integer.parseInt(st.nextToken());   
                cost[i][j] = num;
            }
        }

        dp[0] = cost[0].clone();

        for(int i = 1; i<N; i++){
            dp[i][0] = cost[i][0] + Math.min(dp[i-1][1],dp[i-1][2]);
            dp[i][1] = cost[i][1] + Math.min(dp[i-1][0],dp[i-1][2]);
            dp[i][2] = cost[i][2] + Math.min(dp[i-1][0],dp[i-1][1]);
        }

        System.out.println(Math.min(dp[N-1][0],Math.min(dp[N-1][1],dp[N-1][2])));

    }

}