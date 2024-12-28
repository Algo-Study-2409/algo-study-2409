import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1520_내리막길 {

    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,1,-1};

    static int m;
    static int n;

    static int [][] map;
    static int [][] dp;

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); 
        n = Integer.parseInt(st.nextToken()); 

        map = new int[m][n];
        dp = new int[m][n];

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for(int j = 0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0,0));

    }

    static int dfs(int x, int y){
        
        if (x == m-1 && y == n-1){
            return 1;
        }

        if(dp[x][y] != -1 ) return dp[x][y];

        dp[x][y] = 0;

        for(int i = 0; i<4; i++){

            int mvX = x + dx[i];
            int mvY = y + dy[i];
            
            if(mvX < 0 || mvY < 0 || mvX >= m || mvY >=n) continue;
            if(map[x][y] <= map[mvX][mvY]) continue;

            dp[x][y] += dfs(mvX, mvY);

        }

        return dp[x][y];


    }

}