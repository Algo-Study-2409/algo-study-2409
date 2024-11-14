import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1477 휴게소 세우기
 * - 68 ms
 * - 이분 탐색
 * - mid 구간 동안 휴게소가 없으면 휴게소 세우기
 * - 휴게소를 세워야 하는데 이미 M 개를 세웠을 경우 mid 증가
 * - M 개 이하의 추가 휴게소로 고속도로 끝까지 도달하면 mid 감소
 * - 휴게소 없는 구간의 최대값의 최소 = lowerBound
 * */
public class BOJ1477_휴게소세우기 {
    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int l;
        int i;
        int mid;
        int cnt;
        int prev;
        int left;
        int right;
        int[] locs;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        locs = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (i = 0; i < n; i++) {
            locs[i] = Integer.parseInt(st.nextToken());
        }
        locs[n] = l;
        n++;
        Arrays.sort(locs);
        right = locs[0];
        for (i = 1; i < n; i++) { // 휴게소 사이 거리 최대값으로 right 설정
            right = Math.max(right, locs[i] - locs[i - 1]);
        }
        left = 0;
        while (left < right) {
            mid = left + right >> 1;
            cnt = m; // 추가로 세울 수 있는 휴게소 개수
            prev = 0;
            for (i = 0;;) {
                if ((prev += mid) >= locs[i]) { // 휴게소 업데이트
                    prev = locs[i];
                    if (++i == n) { // 다음 휴게소
                        right = mid; // 고속도로 끝까지 도달하면 mid 감소
                        break;
                    }
                } else { // mid 구간 동안 휴게소가 없으면
                    if (--cnt < 0) { // 휴게소 세우기
                        left = mid + 1; // 이미 M 개를 세웠을 경우 mid 증가
                        break;
                    }
                }
            }
        }
        System.out.print(right); // lowerBound
    }
}
