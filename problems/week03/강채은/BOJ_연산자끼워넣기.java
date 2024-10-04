//BOJ 실버 1 : 14888 - 연산자 끼워넣기 
import java.util.*;
import java.io.*;

public class BOJ_연산자끼워넣기{

    static int n; //수의 개수

    static int [] number ; //숫자 배열
    static int [] operator = new int[4]; //연산자 배열

    static int min = Integer.MAX_VALUE; //연산의 최솟값
    static int max = Integer.MIN_VALUE; //연산의 최댓값

    private static void addOperator(int num, int dep){
        
        if(dep == n){
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for(int i = 0; i<4; i++){

            if(operator[i] == 0) continue;

            //사용한 연산자는 -1 
            operator[i]--;

            switch (i) {
                // + 연산
                case 0: {
                    addOperator(num+number[dep], dep+1); 
                    break;
                }
                // - 연산
                case 1:{
                    addOperator(num-number[dep], dep+1); 
                    break;
                } 
                // * 연산
                case 2: {
                    addOperator(num*number[dep], dep+1); 
                    break;
                }
                // / 연산
                case 3: {
                    addOperator(num/number[dep], dep+1); 
                    break;
                }
            }
            
            //연산자 개수 복구 
            operator[i]++;

        }
    }

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        number = new int[n];
        
        //숫자 입력받기
        for(int i = 0; i<n; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());

        //연산자 개수 입력받기
        for(int i = 0; i<4; i++){
            operator[i] = Integer.parseInt(st.nextToken());
        }

        addOperator(number[0], 1);

        System.out.println(max);
        System.out.println(min);
       
    }
}