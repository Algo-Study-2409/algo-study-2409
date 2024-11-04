import java.util.*;
import java.io.*;

public class BOJ2876_그래픽스퀴즈 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int n = Integer.parseInt(br.readLine()); //책상 수

        int [] dp = new int[6];
       
        int student = 0;
        int minValue = Integer.MAX_VALUE;
        
        for(int i = 1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            int [] current = new int [6];

            if(a==b) current[a] = dp[a] + 1;
            else{
                current[a] = dp[a] + 1;
                current[b] = dp[b] + 1;
            }
            
            for(int j = 1; j<=5; j++){
                if(student < current[j]) {
                    student = current[j];
                    minValue = j;
                }
                else if (student == current[j] && minValue > j){
                    minValue = j;
                }
            }
            dp = current;
        }

        System.out.printf("%d %d",student,minValue);

    }
}