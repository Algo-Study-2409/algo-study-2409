import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 정현우 : BOJ 1660 캡틴 이다솜
 * - 116 ms
 * - DP
 * - 길이별 대포알 개수 배열 생성
 * - 길이 x 의 사면체 대포알 개수
 * -   = x * (x + 1) * (x + 2) / 6
 * - 길이 x + 1 의 사면체 대포알 개수
 * -   = (x + 1) * (x + 2) * (x + 3) / 6
 * -   = (길이 x 의 사면체 대포알 개수) * (x + 3) / x
 * - dp[i] : i 개의 대포알로 만들 수 있는 사면체의 최소 개수
 * - dp[i] = min(dp[i - (사면체 대포알 개수)] + 1)
 * */
class BOJ1660_캡틴이다솜 {
    private static int n;
    private static int[] arr;

    private static void initArr(int depth, int val) {
        if (val > n) { // 최대 대포알 초과
            arr = new int[depth + 1]; // 배열 생성
        } else { // 길이 x + 1 의 사면체 대포알 개수
            initArr(depth + 1, val * (depth + 3) / depth);
        }
        arr[depth] = val; // 길이 x 의 사면체 대포알 개수
    }

    public static void main(String[] args) throws IOException {
        int i;
        int j;
        int start;
        int[] dp;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        initArr(1, 1); // 길이별 대포알 개수 배열 생성
        dp = new int[n + 1];
        start = 0;
        for (i = 1; i <= n; i++) {
            if (i == arr[start + 1]) { // 다음 사면체를 만들수 있는 개수 도달
                start++; // i 개 이하를 사용하는 가장 큰 사면체
            }
            dp[i] = i; // 크기 1 의 사면체만으로 구성
            for (j = start; j > 0; j--) { // i 개 이하를 사용하는 사면체들
                dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
            } // min(dp[i - (사면체 대포알 개수)] + 1)
        }
        System.out.print(dp[n]); // N 개의 대포알로 만들 수 있는 사면체의 최소 개수
    }
}
