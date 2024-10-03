//BOJ 골드 4 : 13975 - 파일 합치기 3 

import java.io.*;
import java.util.*;

public class BOJ_파일합치기 {
    static Queue<Long> answer = new LinkedList<>();

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); //반복 횟수 
        
        while(T-->0){
            
            PriorityQueue<Long> pq = new PriorityQueue<>(); 
            
            int k = Integer.parseInt(br.readLine()); // 소설을 구성하는 장의 개수 
            StringTokenizer st = new StringTokenizer(br.readLine()); 
            
            for(int i = 0; i<k ; i++) {
                pq.add(Long.parseLong(st.nextToken())); //파일 크기 
            }
            
            long sum = 0 ; //파일의 전체합

            while (pq.size()>1) {
                long a = pq.poll(); //제일 작은 수 
                long b = pq.poll(); //두번째로 작은 수
    
                sum += a+b;
                pq.add(a+b);
                
            }

            System.out.println(sum);
        }

    }
}
