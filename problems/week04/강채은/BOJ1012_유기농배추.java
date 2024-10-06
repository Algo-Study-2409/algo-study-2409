import java.io.*;
import java.util.*;

public class BOJ1012_유기농배추 {

    private static int M,N,K; //가로, 세로, 배추 위치 
    private static int [][] farm ;
    private static boolean [][] visited;

    private static int [] dx = {-1,1,0,0};
    private static int [] dy = {0,0,-1,1};

    private static void dfs (int x, int y){
        
        visited[x][y] = true;

        for(int i = 0; i<4; i++){
            
            int posX = x+dx[i];
            int posY = y+dy[i];

            if(posX >= 0 && posY >= 0 && posX<M && posY<N && !visited[posX][posY] && farm[posX][posY] ==1){
                dfs(posX,posY);
            }

        }
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        StringTokenizer st;
        
        while(T-- > 0){

            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int cnt = 0;
            farm = new int[M][N];
            visited = new boolean[M][N];

            for(int i = 0 ; i<K; i++){

                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                farm[x][y] = 1;
            }
            
            for(int i = 0; i<M; i++){
                for(int j = 0; j<N; j++){
                    if(!visited[i][j] && farm[i][j] > 0){
                        dfs(i,j);
                        cnt ++;
                    }
                }
            }

            System.out.println(cnt);

        }

        
    }
    
}
