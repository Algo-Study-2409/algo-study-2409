import java.io.*;
import java.util.*;

public class SFT_나무수확 {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int [] dx = {0,1};
        int [] dy = {1,0};
        
        int n = Integer.parseInt(br.readLine());
        int [][] fruit = new int[n+1][n+1]; //열매 수확량 

        //열매 수확량 입력
        for(int i = 1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=n; j++){
                fruit[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int [][] dp = new int[n+1][n+1]; //수로 설치 시 열매 수확량
        int [][] dp2 = new int[n+1][n+1]; //스프링클러 설치 시 열매 수확량 
        
        dp[1][1] = fruit[1][1]; 
        dp2[1][1] = fruit[1][1]*2;

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                for(int k = 0; k<2; k++){
                    int mvX = i+dx[k];
                    int mvY = j+dy[k];

                    if(mvX <= 0 || mvY <=0 || mvX > n || mvY > n) continue;
                    dp[mvX][mvY] = Math.max(dp[mvX][mvY],dp[i][j] + fruit[mvX][mvY]); 
                    dp2[mvX][mvY] = Math.max(dp2[mvX][mvY]
                                             ,Math.max((dp[i][j]+fruit[mvX][mvY]*2),(dp2[i][j]+fruit[mvX][mvY])));
                }

            }
        }  

        System.out.println(dp2[n][n]);
    }
}

