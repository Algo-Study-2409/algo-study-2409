import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 17179 케이크 자르기
 * - 92 ms
 * - 이분 탐색
 * - Q + 1 조각을 만들 때 가장 작은 조각 길이 최대값
 * - = Q + 1 조각을 만들 수 있는
 * -   조각 길이 최소 한계값의 upperBound - 1
 * - 특정 길이가 최소 한계값일 때
 * - Q + 1 조각을 만들 수 있는지 확인하면서 이분 탐색
 * - Q가 오름차순이므로 이전 upperBound를 right로 사용
 * */
public class BOJ17179_케이크자르기 {
    private static final char LINE_BREAK = '\n';

    private static int m;
    private static int q;
    private static int[] arr;

    private static boolean validate(int thr) {
        int i;
        int cnt;
        int curr;

        cnt = 0; // 조각 수
        curr = 0; // 현재 조각 길이
        for (i = 0; i <= m; i++) {
            curr += arr[i]; // 현재 조각 길이 누적
            if (curr >= thr) { // 현재 조각 >= 조각 길이 최소 한계값
                if (++cnt > q) { // 조각 생성, Q + 1 조각이면
                    return true; // Q + 1 조각 생성 가능
                }
                curr = 0; // 자르기
            }
        }
        return false; // Q + 1 조각 생성 불가
    }

    private static int upperBound(int right) {
        int mid;
        int left;

        left = 0;
        while (left < right) {
            mid = left + right >> 1;
            if (validate(mid)) { // mid가 조각 길이 최소 한계값일 때 Q + 1 조각 생성 가능
                left = mid + 1;
            } else { // mid가 조각 길이 최소 한계값일 때 Q + 1 조각 생성 불가
                right = mid;
            }
        }
        return left; // upperBound
    }

    public static void main(String[] args) throws IOException {
        int n;
        int l;
        int i;
        int prev;
        int prefix;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        arr = new int[m + 1];
        prefix = 0;
        for (i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - prefix; // 지점과 지점 사이 길이
            prefix += arr[i]; // 누적합
        }
        arr[m] = l - prefix; // 마지막 지점과 케이크 끝 사이 길이
        sb = new StringBuilder();
        prev = l + 1; // 초기 right값 = 케이크 길이 + 1
        while (n-- > 0) {
            q = Integer.parseInt(br.readLine()); // Q 값
            prev = upperBound(prev); // Q가 오름차순이므로 이전 upperBound를 right로 사용
            sb.append(prev - 1).append(LINE_BREAK); // upperBound - 1
        }
        System.out.print(sb);
    }
}
