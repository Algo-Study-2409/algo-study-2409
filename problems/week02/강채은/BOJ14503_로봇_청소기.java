//BOJ 골드Ⅴ : 14503 - 로봇_청소기

import java.util.*;
import java.io.*;

public class BOJ14503_로봇_청소기 {

    static int N, M,dir;
    static int vacuumX,vacuumY;
    
    static int cnt = 1;

    static int [][] floor;
    
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1}; 

    public static void clean(int x, int y, int direction){

        //현재 위치 청소 
        floor[x][y] = 2;

        //주변에 청소 안 한 구역이 있는지 확인
        for(int i = 0; i<4; i++){
            direction = (direction+3)%4; //반시계 방향 (0->3 , 1->0, 2->1, 3->2)

            int posX = x+dx[direction];
            int posY = y+dy[direction];
            
            if(posX > -1 && posX <N && posY >-1 && posY <M && floor[posX][posY] == 0){
                cnt ++;
                clean(posX,posY,direction);

                return;
            }

        }
        
        //주변에 청소 안 한 구역이 없을 경우 
        int back = (direction+2)%4; //후진(0->2, 1->3, 2->0, 3->1)
        int backX = x + dx[back];
        int backY = y + dy[back];

        if(backX > -1 && backX < N && backY >-1 && backY <M && floor[backX][backY] != 1){
            clean(backX, backY, direction);
        }

    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        floor = new int[N][M];

        st = new StringTokenizer(br.readLine());

        //로봇 청소기 좌표 (x,y,방향)
        vacuumX = Integer.parseInt(st.nextToken());
        vacuumY = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                floor[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean(vacuumX,vacuumY,dir);
        System.out.println(cnt);
        

    }
}
