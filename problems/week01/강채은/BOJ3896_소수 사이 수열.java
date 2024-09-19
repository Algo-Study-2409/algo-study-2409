//BOJ 실버Ⅰ : 3896 - 소수 사이 수열

import java.io.*;
import java.util.*;

public class Main {
    
    static int bigOne = 1299709;

    //소수인지 아닌지 판별
    static boolean prime(int num){

        if(num == 2) return true;
        if(num%2 == 0 || num == 1) return false;

        double s = Math.sqrt(num);

        for(int i = 3; i<=s; i++){
            if(num % i == 0) return false;
        }

        return true;

    }

    //현재 수보다 작은 소수 구하기
    static int getSmallPrime(int start){

        for(int i = start; i>=2; i--){
            if(prime(i)) return i;
        }

        return 2;
    }

    //현재 수보다 큰 소수 구하기
    static int getBigPrime(int start){

        for(int i = start; i<=bigOne; i++){
            if(prime(i)) return i;
        }

        return 0;
    }

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int [] number = new int[N];

        //각 숫자 배열에 담기
        for(int i = 0; i<N; i++){

            number[i] = Integer.parseInt(br.readLine());

        }

        for(int i=0; i<N; i++){ 

            //체크하려는 수가 소수이면 0 출력
            if(prime(number[i])) {

                System.out.println(0);
                continue;

            }

            //체크하려는 수가 소수가 아니면 해당 수보다 작은 소수, 큰 소수를 구하고 소수 사이 수열 구하기
            else{
                int small = getSmallPrime(number[i]-1);
                int big = getBigPrime(number[i]+1);
                
                System.out.println(big-small);
            }


        }


    }
}
