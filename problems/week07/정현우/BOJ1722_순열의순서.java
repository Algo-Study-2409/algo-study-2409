import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1722 순열의 순서
 * - 68 ms
 * - 세그먼트 트리, 펜윅 트리
 * - fact : 현재 숫자 뒤에 올 숫자 개수 팩토리얼
 * - 순열 구하기
 * -   구간 합 세그먼트 트리 1 부터 N 위치 1 로 초기화
 * -   k : 0 기반으로 변환
 * -   남은 수들 중 (k / fact + 1) 번째 수 출력
 * -   출력한 수 위치 0 으로 업데이트
 * -   다음 위치에서 k : fact 로 나눈 나머지
 * - 순위 구하기
 * -   (어떤 숫자 뒤에 해당 수보다 작은 수의 개수 * fact) 의 합
 * -   (숫자 뒤에 해당 수보다 작은 수의 개수)
 * -     = (숫자 - 1) - (숫자 앞에 해당 수보다 작은 수의 개수)
 * -   숫자 앞에 해당 수보다 작은 수의 개수
 * -     : 1 부터 (숫자 - 1) 까지의 누적합 구하기
 * -   펜윅 트리의 숫자 위치에 1 삽입
 * */
public class BOJ1722_순열의순서 {
    private static final int ONE = '1';
    private static final char SPACE = ' ';

    private static final String getPermutation(int n, long rank) {
        int i;
        int idx;
        int leaf;
        int size;
        int[] tree;
        long fact;
        StringBuilder sb;

        sb = new StringBuilder();
        for (fact = i = 1; i < n; fact *= i++); // fact : 현재 숫자 뒤에 올 숫자 개수 팩토리얼
        for (leaf = 1; leaf < n; leaf <<= 1); // 세그먼트 트리 첫 리프 위치
        tree = new int[leaf << 1];
        size = leaf + n; // N 위치 리프
        for (i = leaf; i < size; i++) { // 구간 합 세그먼트 트리 1 부터 N 위치
            tree[i] = 1; // 1 로 초기화
        }
        for (i = leaf - 1; i > 0; i--) { // 구간 합 계산
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
        rank--; // k : 0 기반으로 변환
        while (--n > 0) {
            idx = (int) (rank / fact) + 1;
            for (i = 1; i < leaf;) { // (k / fact + 1) 번째 수 구하기
                i <<= 1;
                if (tree[i] < idx) {
                    idx -= tree[i];
                    i |= 1;
                }
            }
            sb.append(i - leaf + 1).append(SPACE); // 구한 수 출력
            for (; i > 0; i >>= 1) { // 출력한 수 위치 0 으로 업데이트
                tree[i]--;
            }
            rank %= fact; // 다음 위치에서 k : fact 로 나눈 나머지
            fact /= n; // 다음 위치에서의 fact
        }
        for (i = 1; i < leaf;) { // 남은 한 개의 수 구하기
            i <<= 1;
            if (tree[i] == 0) {
                i |= 1;
            }
        }
        sb.append(i - leaf + 1).append(SPACE); // 구한 수 출력
        return sb.toString(); // 순열 반환
    }

    private static final long getRank(int n, StringTokenizer st) {
        int i;
        int cnt;
        int num;
        int size;
        int[] ft;
        long rank;
        long fact;

        rank = 1L;
        for (fact = i = 1; i < n; fact *= i++); // fact : 현재 숫자 뒤에 올 숫자 개수 팩토리얼
        for (size = 1; size < n; size <<= 1); // 펜윅 트리 사이즈 계산
        ft = new int[++size];
        while (--n > 0) {
            num = Integer.parseInt(st.nextToken()); // 현재 숫자
            for (cnt = 0, i = num - 1; i != 0; cnt += ft[i], i -= i & -i); // 숫자 앞에 해당 수보다 작은 수의 개수
            rank += (num - 1 - cnt) * fact; // (어떤 숫자 뒤에 해당 수보다 작은 수의 개수 * fact)
            for (i = num; i < size; ft[i]++, i += i & -i); // 펜윅 트리의 숫자 위치에 1 삽입
            fact /= n; // 다음 위치에서의 fact
        }
        return rank; // 순위 반환
    }

    public static void main(String[] args) throws IOException {
        int n;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        if (br.read() == ONE) { // 순열 구하기
            br.read();
            System.out.print(getPermutation(n, Long.parseLong(br.readLine())));
        } else { // 순위 구하기
            br.read();
            System.out.print(getRank(n, new StringTokenizer(br.readLine())));
        }
    }
}
