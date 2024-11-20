import java.io.*;
import java.util.*;

public class SFT_순서대로방문하기 {

    private static int N; //격자 크기
    private static int M; //방문해야 하는 칸 수

    private static int result = 0 ;
    
    private static ArrayList<Pos> arr;

    private static int [][] board;
    private static boolean [][] visited;

    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    static class Pos{

        int x;
        int y;

        public Pos(int x, int y){
            this.x=x;
            this.y=y;
        }
        
    }

    private static void dfs(int x, int y, int en){
        
        if(arr.get(en).x == x && arr.get(en).y == y){
            if(en == arr.size()-1) {
                result ++;
                return;
            }
            en ++;
        }

        for(int i = 0; i<4; i++){
            
            int mvX = x + dx[i];
            int mvY = y + dy[i];

            if(mvX <= 0|| mvY <= 0|| mvX > N || mvY > N || visited[mvX][mvY] || board[mvX][mvY] == 1 ) continue;

            visited[mvX][mvY] = true;
            dfs(mvX,mvY,en);
            visited[mvX][mvY] = false;
            
        }

        return ;
        
    }
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        board = new int[N+1][N+1];

        //격자 정보 입력
        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        arr = new ArrayList<>();

        //방문해야 하는 위치 입력
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            arr.add(new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        
        visited = new boolean[N+1][N+1];
        visited [arr.get(0).x][arr.get(0).y] = true;
        int next = 1;

        if(arr.size() <= 1) next = 0;
        
        dfs(arr.get(0).x,arr.get(0).y,next);

        System.out.println(result);
        
    }
}
