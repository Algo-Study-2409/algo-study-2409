//BOJ 골드Ⅴ : 15686 - 치킨 배달

import java.util.*;
import java.io.*;

public class BOJ15686_치킨_배달 {
    
    static int N,M; 

    static int [][] city ; // 도시 
    
    static ArrayList<int []> chicken = new ArrayList<>(); //치킨집 위치
    static ArrayList<int []> home = new ArrayList<>(); //집 위치
    static boolean [] chickenCheck; //방문한 치킨집
    
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    
    static int total = Integer.MAX_VALUE; //도시의 치킨 거리

    private static void delivery(int st , int cnt){
        
        //M개의 치킨집이 선택되었으면 치킨 거리 계산
        if(cnt == M){
            int sum = 0;

            for(int i =0; i<home.size(); i++){
                int min = Integer.MAX_VALUE;
                for(int j = 0; j<chicken.size(); j++){
                    if(chickenCheck[j]){
                        int distance = Math.abs(home.get(i)[0]-chicken.get(j)[0])+Math.abs(home.get(i)[1] - chicken.get(j)[1]);
                        min = Math.min(min, distance);
                    }
                }
                sum += min;
            }
            total = Math.min(total, sum);
            return;
        }

        //백트래킹으로 선택할 치킨 집 구해주기
        for(int i = st; i<chicken.size(); i++ ){
            chickenCheck[i] = true;
            delivery(i+1, cnt+1);
            chickenCheck[i] = false;
        }
    }

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N+1][N+1];    
        
        //도시 정보 기입
        for(int i = 1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<N+1; j++){
                city[i][j] = Integer.parseInt(st.nextToken());
                
                if(city[i][j] == 1) home.add(new int []{i, j}); //집 위치 
                else if (city[i][j] == 2) chicken.add(new int []{i, j}); //치킨집 위치
                
            }
        }
        
        chickenCheck = new boolean[chicken.size()];
        delivery(0, 0);
        System.out.println(total);


    }

}