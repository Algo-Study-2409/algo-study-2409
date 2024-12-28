import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17484_진우의달여행 {

    private static int n,m;
    private static int result = Integer.MAX_VALUE;

    private static int [][] fuel;

    private static int [] dx ={1,1,1};
    private static int [] dy ={-1,0,1};

    public static void main(String[] args) throws Exception {
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        fuel = new int[n][m]; //연료의 양 

        //연료 값 저장
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                fuel[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<m; i++){
            dfs(0,i,-1,fuel[0][i],1);
        }

        System.out.println(result);

    }

    private static void dfs(int x, int y, int dir, int cnt, int dep){

        if(dep == n){
            result = Math.min(result,cnt);
            return;

        }

        for(int i = 0 ; i<3; i++){
            
            if(dir==i) continue;

            int mvX = x + dx[i];
            int mvY = y + dy[i];

            if(mvX < 0 || mvY < 0 || mvX >=n || mvY >= m) continue;
            dfs(mvX,mvY,i,cnt+fuel[mvX][mvY],dep+1);

        }


    }
    
}