import java.io.*;
import java.util.*;

public class BOJ1446_지름길{

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //지름길의 개수
        int d = Integer.parseInt(st.nextToken()); //고속도로의 길이 

        int [][] dist = new int[n][3];

        //지름길 정보 저장
        for(int i = 0; i<n; i++){

            st = new StringTokenizer(br.readLine());

            dist[i][0] = Integer.parseInt(st.nextToken());
            dist[i][1] = Integer.parseInt(st.nextToken());
            dist[i][2] = Integer.parseInt(st.nextToken());

        }

        int [] dp = new int[d+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i<=d; i++){

            dp[i] = Math.min(dp[i], dp[i-1]+1);

            for(int j = 0; j<n; j++){
                if(dist[j][1] == i){ //지름길이 존재한다면 지름길 이용하는게 더 효과적인지 판단
                    dp[i] = Math.min(dp[i], dp[dist[j][0]] + dist[j][2]);
                }
            }

        }

        System.out.println(dp[d]);
        
        
    }

}