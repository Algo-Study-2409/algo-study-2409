//BOJ 골드Ⅳ : 2636 - 치즈
// https://easybrother0103.tistory.com/89 참고

import java.io.*;
import java.util.*;

public class Main5 {
    
    static int row; //행
    static int col; //열

    static boolean [][] visited;
    static int [] dx = {0,0,-1,1};
    static int [] dy = {-1,1,0,0};

    static int[][] cheeze, afterCheeze; //초기 치즈 위치와 1시간 후 치즈 위치

    static int time = 0; //치즈가 모두 녹는 데 걸리는 시간
    static int cheezeCnt = 0; //초기 치즈 개수
    static int decCheeze = 0; //1시간 마다 녹는 치즈 개수

    static void bfs(){

        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        visited = new boolean[row][col];
        visited[0][0] = true;

        decCheeze = 0;
        
        while(!queue.isEmpty()){

            int [] pos = queue.poll();

            for(int i = 0; i<4; i++){
                int x = pos[0] + dx[i];
                int y = pos[1] + dy[i];

                //공기의 앞,뒤,오른쪽,왼쪽 탐색
                if(x >= 0 && x<row && y >=0 && y<col && !visited[x][y]){
                    
                    visited[x][y] = true;
                    
                    //공기와 맞닿아 있는 치즈는 녹게 됨
                    if(cheeze[x][y] == 1){
                        afterCheeze[x][y] = 0;
                        decCheeze ++;
                    }
                    else{
                        queue.add(new int[]{x,y});
                    }
                    

                }
            }

        }

        //녹은 치즈의 개수 제거
        cheezeCnt-=decCheeze;

    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken()); 
        col = Integer.parseInt(st.nextToken()); 

        cheeze = new int[row][col];
        afterCheeze = cheeze;

        int time = 0; //시간
        cheezeCnt = 0; 
        
        //치즈 놓기
        for(int i = 0; i<row; i++){
            
            st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j<col; j++){
                cheeze[i][j] = Integer.parseInt(st.nextToken());
                if(cheeze[i][j] == 1) cheezeCnt ++; //초기 치즈 개수
            }
            
        }

        //치즈가 모두 없어질 때까지 실행
        while(cheezeCnt !=0){
            
            visited = new boolean[row][col];
            bfs();
            time ++; //탐색 후에는 1시간 증가
            cheeze = afterCheeze; //1시간 후 치즈 상태 변화

        }

        System.out.println(time);
        System.out.println(decCheeze);
         
      
    }
}
