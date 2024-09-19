//BOJ 실버Ⅲ : 14501 - 퇴사
import java.io.*;
import java.util.*;

public class Main {

    public static void main (String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //근무일 수

        int [] T = new int[N]; //상담 시간
        int [] P = new int[N]; //상담 금액
        int [] benefit = new int[N+1]; //각 회의까지의 최대이익을 저장하기 위한 배열 

        //일 별 상담 기간,금액 
        for(int i = 0; i<N; i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());

        }


        for(int i = 0; i<N; i++){

            //퇴사 전에 상담이 끝난다면, 상담이 끝나는 날 수당 업데이트 -> 기존 값과 i날의 상담 수당 중 더 많은 이익을 취할 수 있는 값
            if(i+T[i] <= N) benefit[i+T[i]] = Math.max(benefit[i+T[i]], benefit[i]+P[i]);
            benefit[i+1] = Math.max(benefit[i+1],benefit[i]);

        }

        System.out.println(benefit[N]);

    }
}