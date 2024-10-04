import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 13975 파일 합치기 3
 * - 868 ms
 * - 그리디 + 세그먼트 트리
 * - K 개 이상의 리프 노드를 갖는
 * - 최소값 Bottom-Up 세그트리 구성
 * - 값 = (파일 크기) << 21 | (리프 인덱스)
 * - 최소값 삭제 (파일 크기 INF로 대체)
 * - cost = 기존 최소값과 새로운 최소값의 파일 크기 합
 * - 새로운 최소값 파일 크기 cost로 대체
 * - cost 합 출력
 * */
public class BOJ13975_파일합치기3 {
	private static final int MAX_SIZE = 2_097_152;
	private static final int SHIFT = 21;
	private static final long MOD = (1 << SHIFT) - 1;
	private static final long INF = 10_000_000_001L;
	private static final long INIT = INF << SHIFT;
	private static final char LINE_BREAK = '\n';

	private static int leaf;
	private static long[] tree;
	private static BufferedReader br;

	private static final void init(int k) throws IOException {
		int i;
		int thr;
		int size;
		StringTokenizer st;

		for (leaf = 1; leaf < k; leaf <<= 1); // 리프 노드 시작 인덱스
		size = leaf << 1; // 트리 크기
		st = new StringTokenizer(br.readLine());
		thr = leaf + k;
		for (i = leaf; i < thr; i++) { // K 개 리프에 (파일 크기) << 21 | (리프 인덱스) 입력
			tree[i] = Long.parseLong(st.nextToken()) << SHIFT | i;
		}
		for (; i < size; i++) { // 나머지 리프 INF << 21 으로 초기화
			tree[i] = INIT;
		}
		for (i = leaf - 1; i > 0; i--) { // 최소값 세그먼트 트리 구성
			tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
		}
	}

	private static final void alter(long val) {
		int i;

		i = (int) (tree[1] & MOD); // 최소값의 리프 인덱스
		tree[i] = val << SHIFT | i; // (대체할 파일 크기) << 21 | (리프 인덱스)
		for (i >>= 1; i > 0; i >>= 1) { // 리프에서 루트까지 최소값 업데이트
			tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
		}
	}

	private static final long solution() throws IOException {
		int k;
		long sum;
		long cost;

		k = Integer.parseInt(br.readLine());
		init(k); // 세그트리 초기화
		sum = 0; // cost 합
		while (--k > 0) { // k - 1 회 합치면 최종 파일 완성
			cost = tree[1] >> SHIFT; // 최소값의 파일 크기
			alter(INF); // 최소값 삭제 (파일 크기 INF로 대체)
			sum += cost += tree[1] >> SHIFT; // 기존 최소값의 파일 크기 + 새로운 최소값의 파일 크기
			alter(cost); // 새로운 최소값 파일 크기 cost로 대체
		}
		return sum; // cost 합 반환
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		tree = new long[MAX_SIZE];
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution()).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
