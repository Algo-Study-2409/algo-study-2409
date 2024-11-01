import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 2407 조합
 * - 68 ms
 * - 조합
 * - nCm = ((n - m) 부터 n 까지의 곱) / m!
 * - nCm = nC(n - m) 이므로
 * - m > (n / 2) 이면 m = n - m
 * */
public class BOJ2407_조합 {
    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        BigInteger res;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        if (m > n >> 1) { // m > (n / 2)
            m = n - m; // nCm = nC(n - m)
        }
        res = BigInteger.ONE;
        for (i = n - m + 1; i <= n; i++) { // (n - m) 부터 n 까지의 곱
            res = res.multiply(BigInteger.valueOf(i));
        }
        for (i = m; i > 1; i--) { // m!
            res = res.divide(BigInteger.valueOf(i));
        }
        System.out.println(res.toString());
    }
}
