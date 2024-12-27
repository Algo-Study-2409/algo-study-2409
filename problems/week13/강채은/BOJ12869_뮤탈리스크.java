import java.util.*;
import java.io.*;

public class BOJ12869_뮤탈리스크 {

    static int [][] attack = new int [][] {{9,3,1},{9,1,3},{3,9,1},{3,1,9},{1,9,3},{1,3,9}};
    static int [][][] dp;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int [] scv = new int[3];
        dp = new int[61][61][61];

        st = new StringTokenizer(br.readLine());

        //SCV 입력 받기 
        for(int i = 0; i<n; i++){
            scv[i] = Integer.parseInt(st.nextToken());
        }

        dfs(scv, 0);

        System.out.println(min);
    }

    static void dfs(int [] arr , int cnt){

        int s1 = arr[0];
        int s2 = arr[1];
        int s3 = arr[2];

        if(cnt >= min) return;

        if(dp[s1][s2][s3] != 0 && dp[s1][s2][s3] <= cnt) return;

        dp[s1][s2][s3] = cnt;

        if(s1 == 0 && s2 == 0 && s3 == 0){
            min = Math.min(cnt, min);
            return;
        }
        
        for(int i = 0; i<attack.length; i++){
            int new1 = Math.max(s1 - attack[i][0], 0);
            int new2 = Math.max(s2 - attack[i][1], 0);
            int new3 = Math.max(s3 - attack[i][2], 0);

            dfs(new int [] {new1,new2,new3}, cnt + 1);
        }
    }

}