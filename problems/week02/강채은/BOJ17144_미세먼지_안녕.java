//BOJ 골드Ⅳ : 17144 - 미세먼지 안녕!

import java.io.*;
import java.util.*;

public class BOJ17144_미세먼지_안녕 {

    private static int [][] board, afterBoard;

    private static int uAirPurifier, dAirPurifier; 

    private static int [] dx = {-1,1,0,0};
    private static int [] dy = {0,0,-1,1};

    private static int r,c,t;

    public static void moveR(int x, int y){

        for(int i = y+1; i<c-1; i++){
            afterBoard[x][i+1] = board[x][i];
        }
        afterBoard[x][y+1] = 0;

    }

    public static void moveU(int x, int y, boolean div){

        int st = div ? 0 : dAirPurifier+1;

        for(int i = x; i>st; i--){
            afterBoard[i-1][y] = board[i][y];
        }
    }

    public static void moveL(int x, int y){

        for(int i = y; i>0; i--){
            afterBoard[x][i-1] = board[x][i];
        }

    }

    public static void moveD(int x, int y , boolean div){

        int end = div ? uAirPurifier-1 : r-1;

        for(int i = x; i<end; i++){
            afterBoard[i+1][y] = board[i][y];
        }
    }

    //공기청정기 가동
    public static void action(){

        //위쪽 공기청정기 (오-위-왼-아)
        moveR(uAirPurifier,0);
        moveU(uAirPurifier, c-1,true);
        moveL(0, c-1);
        moveD(0, 0,true);

        //아래쪽 공기청정기 (오-아-왼-위)
        moveR(dAirPurifier, 0);
        moveD(dAirPurifier, c-1,false); //위쪽 공기청정기랑 아래쩍 공기청정기 x++헤줘야하는 지점 마지노선이 다름 
        moveL(r-1, c-1);
        moveU(r-1, 0,false);
        
    }

    //미세먼지 확산
    public static void spread(int row, int col){

        int value = board[row][col];
        int dif = board[row][col]/5;
        int cnt = 0;
        
        for(int i = 0; i<4; i++){
            
            int x = row+dx[i];
            int y = col+dy[i];

            if(x>=0 && x<r && y>=0 && y<c && board[x][y] != -1){
                afterBoard[x][y] += dif;
                cnt ++;
            }

        }
        
        value -= cnt*dif;
        afterBoard[row][col] += value;

    }

    //
    public static void getClone(){

        for(int i = 0; i<r; i++){
            board[i] = afterBoard[i].clone();
        }

    }

    public static void getResult(){
        int cnt = 0;

        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                if(board[i][j] > 0)cnt += board[i][j];
            }
        }
        
        System.out.println(cnt);
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()); //행
        c = Integer.parseInt(st.nextToken()); //열
        t = Integer.parseInt(st.nextToken()); //시간

        board = new int[r][c];
        afterBoard = new int[r][c];

        //미세먼지, 공기청정기 채워주기
        for(int i = 0; i<r; i++){

            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<c; j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                //공기청정기가 놓인 위치 저장
                if(board[i][j] == -1 && uAirPurifier == 0 ){
                    uAirPurifier = i;
                    dAirPurifier = i+1;

                } 
            }    
        }
        
        for(int time = 0; time < t; time++){
            afterBoard = new int[r][c];
            afterBoard[uAirPurifier][0] = -1;
            afterBoard[uAirPurifier+1][0] = -1;

            for(int i = 0; i<r; i++){
                for(int j = 0; j<c; j++){
                    if(board[i][j]>0) spread(i,j);
                }
            }

            getClone();            
            action();   
            getClone();
             
        }

        getResult();

    }
    
}