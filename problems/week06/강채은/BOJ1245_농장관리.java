import java.io.*;
import java.util.*;

public class BOJ1245_농장관리 {

    private static int N,M;
    private static int [][] farm;

    private static int [] dx = {-1,1,0,0,-1,-1,1,1};
    private static int [] dy = {0,0,-1,1,-1,1,-1,1};

    private static boolean [][] visited;
    private static boolean check;

    private static void dfs(int x, int y){
        
        visited[x][y] = true;

        for(int i = 0; i<8; i++){
            
            int mvX = x + dx[i];
            int mvY = y + dy[i];
            
            if(mvX <0 || mvY<0 || mvX >= N || mvY >= M ) continue;
            if(farm[mvX][mvY] > farm[x][y]) check = false;
            if(!visited[mvX][mvY] && farm[mvX][mvY] == farm[x][y]) dfs(mvX, mvY);
            
        }

    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        farm = new int[N][M];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<M; j++){
                farm[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        visited = new boolean[N][M];
        check = true;

        for(int i = 0; i<N; i++){
            for(int j = 0 ; j<M; j++){
                if(!visited[i][j]) {
                    check = true;
                    dfs(i,j);
                    if(check) cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

}