import java.util.Arrays;

/**
 * 정현우 : PGS 43236 징검다리
 * - 37.12 ms
 * - 이분 탐색
 * - 각 바위 사이 거리 배열 생성
 * - 배열을 순회하면서 각 지점 사이 거리가
 * - mid 이상이 되도록 바위 제거
 * - 제거해야 하는 바위가 n 개를 초과하면 mid 감소
 * - n 개 이하로 제거하면 mid 증가
 * - 최솟값 중에 가장 큰 값 = upperBound - 1
 * */
public class PGS43236_징검다리 {
    public int solution(int distance, int[] rocks, int n) {
        int i;
        int len;
        int cnt;
        int mid;
        int curr;
        int left;
        int right;
        int[] dists;

        Arrays.sort(rocks);
        len = rocks.length;
        dists = new int[len + 2]; // 마지막 dist가 mid 이상인지 확인하기 위해 빈 공간 추가
        dists[0] = rocks[0]; // 각 바위 사이 거리 배열
        for (i = 1; i < len; i++) {
            dists[i] = rocks[i] - rocks[i - 1];
        }
        dists[len] = distance - rocks[len - 1];
        len++;
        left = 0;
        right = distance + 1;
        loop:
        while (left < right) {
            mid = left + right >> 1;
            cnt = 0; // 제거하는 바위 개수
            curr = dists[0];
            for (i = 1; i <= len; i++) { // 배열 순회
                if (curr < mid) { // 현재 거리가 mid 미만
                    if (++cnt > n) { // 바위 제거
                        right = mid; // n 개를 초과하면 mid 감소
                        continue loop;
                    }
                    curr += dists[i]; // 거리 추가
                } else { // 현재 거리가 mid 이상
                    curr = dists[i]; // 다음 거리
                }
            }
            left = mid + 1; // n 개 이하로 제거하면 mid 증가
        }
        return right - 1; // upperBound - 1
    }
}
