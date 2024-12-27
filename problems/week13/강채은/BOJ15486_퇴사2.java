import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15486_퇴사2 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int [][] arr = new int[n + 2][2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            arr[i][0] = t;
            arr[i][1] = p;

        }

        int[] dp = new int[n + 2];
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= n+1; i++) {
            
            max = Math.max(dp[i],max);
            int nxt = i + arr[i][0];

            if(nxt > n+1) continue;

            dp[nxt] = Math.max(dp[nxt], max + arr[i][1]);

        }

        System.out.println(dp[n+1]);

    }

}