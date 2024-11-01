import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ2407_조합 {
    
    private static BigInteger [][] dp ;
    
    private static BigInteger combi(int n , int r){

        if(dp[n][r].compareTo(BigInteger.ZERO) > 0) return dp[n][r];

        if(n == r || r == 0) return BigInteger.ONE;

        return dp[n][r] = combi(n-1, r).add(combi(n-1, r-1));
        
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dp = new BigInteger[101][101];
        for(int i = 0 ; i<101; i++){
            for(int j = 0 ; j<101; j++){
                dp[i][j] = BigInteger.ZERO;
            }
        }
        
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        System.out.println(combi(n, r));

    }

}
