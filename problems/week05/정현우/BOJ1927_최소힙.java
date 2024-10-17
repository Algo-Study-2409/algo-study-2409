import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 정현우 : BOJ 1927 최소 힙
 * - 208 ms
 * - 세그먼트 트리
 * - 값 = x << 18 | (리프 인덱스)
 * - 최소값 Bottom-Up 세그트리 구성
 * - 모든 노드 INF로 초기화
 * - x가 0이면
 * -   최소 값 >> 18 출력
 * -   최소값 삭제 (INF로 대체)
 * -   최소 값이 INF일 경우 0 출력
 * - x가 자연수면 세그트리에 값 추가
 * */
public class BOJ1927_최소힙 {
    private static final int SHIFT = 18;
    private static final long MOD = (1 << SHIFT) - 1;
    private static final long INF = Long.MAX_VALUE;
    private static final char LINE_BREAK = '\n';
    private static final char[] EMPTY = {'0', LINE_BREAK};

    private static int idx;
    private static long[] tree;

    private static final void init(int n) {
        int size;

        for (idx = 1; idx < n; idx <<= 1); // 시작 리프 인덱스
        tree = new long[size = idx << 1]; // 세그먼트 트리
        while (size-- > 1) { // 모든 노드
            tree[size] = INF; // INF로 초기화
        }
    }

    private static final void add(long val) {
        int i;

        i = idx++; // 새로운 리프 인덱스
        tree[i] = val << SHIFT | i; // 값 = x << 18 | (리프 인덱스)
        for (i >>= 1; i > 0; i >>= 1) { // 리프부터 루트까지
            tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]); // 최소값 계산
        }
    }

    private static final void remove() {
        int i;

        i = (int) (tree[1] & MOD); // 리프 인덱스 = 값 % (1 << 18)
        tree[i] = INF; // 최소값 삭제 (INF로 대체)
        for (i >>= 1; i > 0; i >>= 1) { // 리프부터 루트까지
            tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]); // 최소값 계산
        }
    }

    public static void main(String[] args) throws IOException {
        int n;
        int x;
        StringBuilder sb;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        init(n); // 세그먼트 트리 초기화
        sb = new StringBuilder();
        while (n-- > 0) {
            x = Integer.parseInt(br.readLine());
            if (x == 0) { // x가 0이면
                if (tree[1] == INF) { // 최소 값이 INF일 경우
                    sb.append(EMPTY); // 0 출력
                } else {
                    sb.append((int) (tree[1] >> SHIFT)).append(LINE_BREAK); // 최소 값 >> 18 출력
                    remove(); // 최소값 삭제 (INF로 대체)
                }
            } else { // x가 자연수면
                add(x); // 세그트리에 값 추가
            }
        }
        System.out.print(sb.toString());
    }
}
