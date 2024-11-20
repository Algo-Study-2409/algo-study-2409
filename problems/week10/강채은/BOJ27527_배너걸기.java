import java.util.*;
import java.io.*;

public class BOJ27527_배너걸기 {

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [] h = new int [N+1];
        int [] number = new int[1000001];
        
        int max = (int) Math.ceil((double)(9*M)/10);

        st = new StringTokenizer(br.readLine());
        
        for(int i = 1; i<=M; i++) {
            
            h[i] = Integer.parseInt(st.nextToken());
            number[h[i]] ++ ;

            if(number[h[i]] >= max) {

                System.out.println("YES");
                return;

            }
            
        }
        
        for(int i = M+1; i<=N; i++){

            h[i] = Integer.parseInt(st.nextToken());

            number[h[i-M]] --;
            number[h[i]] ++ ;

            if(number[h[i]] >= max || number[h[i-M]] >= max){
                System.out.println("YES");
                return;
            }

        }

        System.out.println("NO");
    }
}

/**
 * 시간 초과
 * int num = Integer.MIN_VALUE;

        for(int i = 0; i<N; i++){
            if(i+M > N) break;
            int [] number = new int[1000001];
            for(int j = i ; j<i+M; j++){
                number[h[j]]++;
                num = Math.max(num,number[h[j]]);
            }
            //System.out.println(num);
            if(num >= max) {
                //System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
 */