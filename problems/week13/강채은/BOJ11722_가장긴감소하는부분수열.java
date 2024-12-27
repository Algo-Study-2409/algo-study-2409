import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11722_가장긴감소하는부분수열 {

    public static void main(String[] args) throws Exception {
            
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int [] number = new int[n];
        int [] dp = new int[n];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i<n; i++){

            number[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;

        }

        int max = Integer.MIN_VALUE;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<i; j++){
                if(number[j] > number[i]) dp[i] = Math.max(dp[i], dp[j]+1);
            }
            max = Math.max(dp[i],max);
        }

        System.out.println(max);

    }

}