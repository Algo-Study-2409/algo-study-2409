import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1654 랜선 자르기
 * - 104 ms
 * - 이분 탐색
 * - 만들 랜선의 길이를 정하고
 * - K 개의 케이블로 N 개 이상 만들 수 있는지 판단
 * - 최대 길이 = upperBound - 1
 * - right 초기값은 가장 긴 초기 랜선 길이 + 1
 * - left + right가 int 범위를 넘을 수 있으므로
 * - unsigned right shift 사용
 * */
public class BOJ1654_랜선자르기 {
    private static final int upperBound(int k, int n, int[] cables, int right) {
        int i;
        int cnt;
        int mid;
        int left;

        left = 1;
        loop:
        while (left != right) {
            mid = left + right >>> 1; // left + right가 int 범위를 넘을 수 있으므로 >>>
            cnt = 0; // 만들어진 랜선 개수
            for (i = 0; i < k; i++) {
                if ((cnt += cables[i] / mid) >= n) { // N 개 이상 만듦
                    left = mid + 1; // left 증가
                    continue loop;
                }
            }
            right = mid; // N 개 미만, right 감소
        }
        return left; // upperBound
    }

    public static void main(String[] args) throws IOException {
        int k;
        int n;
        int i;
        int max;
        int[] cables;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        cables = new int[k];
        max = 0;
        for (i = 0; i < k; i++) { // K 개의 랜선
            cables[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, cables[i]); // 가장 긴 초기 랜선 길이
        }
        System.out.print(upperBound(k, n, cables, max + 1) - 1); // upperBound - 1
    }
}
