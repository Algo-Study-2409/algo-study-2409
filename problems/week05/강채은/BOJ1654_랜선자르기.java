import java.util.*;
import java.io.*;

public class BOJ1654_랜선자르기 {
    
    private static int K,N; //랜선의 개수, 필요한 랜선의 개수

    private static long [] cut;

    private static void cuting(long min, long max){

        long cnt = 0;

        while(min < max){
            
            cnt = 0;
            
            long mid = (min+max)/2;

            for (int i = 0; i<K; i++) {
                cnt+=(cut[i]/mid);
            }
            
            if(cnt < N){
                max = mid;

            }
            else{
                min = mid + 1;
            }

        }

        System.out.println(min-1);

    }

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken()); //영식이가 가지고 있는 랜선의 개수
        N = Integer.parseInt(st.nextToken()); //필요한 랜선의 총 개수 

        cut = new long[K];
        long max = 0;
        for(int i = 0; i<K; i++){
            cut[i] = Integer.parseInt(br.readLine());
            max = Math.max(cut[i], max);
        }

        cuting(1,max + 1);

    }

}
