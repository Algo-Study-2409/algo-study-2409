import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 2110 공유기 설치
 * - 232 ms
 * - 이분 탐색
 * - 첫 집에는 공유기 항상 설치
 * - 집 순회하면서 이전 공유기까지의 거리가
 * - mid 이상이면 공유기 설치
 * - C 개를 모두 설치하면 mid 증가
 * - C 개를 모두 설치하지 못하면 mid 감소
 * - 인접한 공유기 사이 최대 거리 = upperBound - 1
 * */
public class BOJ2110_공유기설치 {
    public static void main(String[] args) throws IOException {
        int n;
        int c;
        int i;
        int cnt;
        int mid;
        int prev;
        int left;
        int right;
        int[] locs;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken()) - 1; // 첫 집에는 공유기 항상 설치
        locs = new int[n];
        for (i = 0; i < n; i++) {
            locs[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(locs);
        left = 1;
        right = locs[n - 1] - locs[0] + 1;
        loop:
        while (left < right) {
            mid = left + right >> 1;
            cnt = c; // 설치할 공유기 개수
            prev = locs[0]; // 첫 집에는 공유기 항상 설치
            for (i = 1; i < n; i++) { // 집 순회하면서
                if (locs[i] - prev >= mid) { // 이전 공유기까지의 거리가 mid 이상
                    if (--cnt == 0) { // 공유기 설치
                        left = mid + 1; // C 개를 모두 설치하면 mid 증가
                        continue loop;
                    }
                    prev = locs[i]; // 공유기 설치
                }
            }
            right = mid; // C 개를 모두 설치하지 못하면 mid 감소
        }
        System.out.print(right - 1); // upperBound - 1
    }
}
