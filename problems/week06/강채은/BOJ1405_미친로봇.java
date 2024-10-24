import java.util.*;
import java.io.*;

public class BOJ1405_미친로봇 {

    private static int N ;
    private static double total = 0; // 이동경로가 단순할 확률

    private static double [] dir; //동서남북 
    private static int [] dx ={-1,1,0,0};
    private static int [] dy ={0,0,-1,1};

    private static boolean [][] visited;

    private static void dfs(int x, int y, int dep , double per){

        if(dep == N) {
            total += per;
            return ;
        }

        visited[x][y] = true;
        
        for(int i = 0; i<4; i++){
            
            int mvX = x + dx[i];
            int mvY = y + dy[i];

            if(mvX < 0 || mvY < 0 || mvX >= 30 || mvY >= 30 || visited[mvX][mvY]) continue; 

            visited[mvX][mvY] = true;
            dfs(mvX, mvY, dep + 1, per * dir[i]);
            visited[mvX][mvY] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        
        dir = new double[4];
        visited = new boolean[30][30];

        //각 방향으로 이동할 확률 입력
        for(int i = 0; i<4; i++){
            dir[i] = Integer.parseInt(st.nextToken()) * 0.01 ;
        }

        dfs(15,15,0,1);
        System.out.println(total);

    }
}
