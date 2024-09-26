//BOJ 골드Ⅳ : 17179 - 케이크 자르기
// https://kau-algorithm.tistory.com/1535 도움 

import java.util.*;
import java.io.*;

public class BOJ17179_케이크_자르기 {

    static int N,M,L;
    static int [] peice; 

    private static boolean isPossible(int mid, int Q){
        int cnt = 0;
        int sum = 0;
        for(int i = 0; i<peice.length; i++){
            sum +=peice[i];

            if(sum >= mid){
                cnt +=1;
                sum = 0;
            }
        }
        return cnt >= Q+1;
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //총 자르는 횟수
        M = Integer.parseInt(st.nextToken()); //자를 수 있는 지점의 개수
        L = Integer.parseInt(st.nextToken()); //롤 케이크의 길이

        peice = new int[M+1];

        //각 지점의 사이의 길이를 넣어줌
        int last = 0;
        for(int i = 0; i<M; i++){
            int point = Integer.parseInt(br.readLine());
            peice[i] = point - last; 
            last = point;
        }

        peice[M] = L-last; //총 길이 - 마지막 자르는 지점

        for(int i = 0; i<N; i++){

            int minPiece = 0;//가장 작은 케이크 조각의 최대 길이
            
            int Q = Integer.parseInt(br.readLine()); //자르는 횟수

            int low = 1;
            int high = L;

            while(low<=high){
                
                int mid = (low+high)/2;

                if(isPossible(mid,Q)){
                    minPiece = Math.max(minPiece,mid);
                    low = mid +1;
                }
                else{
                    high = mid -1;
                }
            }

            System.out.println(minPiece);

        }

    }
    
}
