import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 21939 문제 추천 시스템 Version 1
 * - 284 ms
 * - 오프라인 쿼리 + Lazy 세그먼트 트리
 * - 값 = (L << 17) | P
 * - recommend 시점 별 최소, 최대 값 구하기
 * - 각 시점을 리프노드로 하는 최소, 최대 값 세그먼트 트리 구성
 * - 문제가 살아있는 시간 범위에 최소, 최대 값 업데이트
 * - 범위 업데이트 Lazy 적용
 * - 마지막에 모든 노드에 대해 propagate
 * - 각 리프노드 별로 출력해야 할 최대 또는 최소 값에
 * - (1 << 17) - 1 과 AND 연산하여 P만 출력
 * */
public class BOJ21939_문제추천시스템Version1 {
	private static final int INF = Integer.MAX_VALUE >> 1;
	private static final int MAX_P = 100_001;
	private static final int SHIFT = 17;
	private static final int MOD = (1 << SHIFT) - 1;
	private static final int RECOMMEND = 9;
	private static final int ADD = 3;
	private static final int SOLVED = 6;
	private static final char MAX = '1';
	private static final char LINE_BREAK = '\n';

	private static int[] maxLazy;
	private static int[] minLazy;
	private static char[] times;
	private static StringBuilder sb;

	private static final class Range {
		int left; // 범위 시작
		int right; // 범위 마지막
		int val; // (L << 17) | P
		Range next; // Linked List에서의 next

		Range(int left, int val) {
			this.left = left;
			this.right = INF;
			this.val = val;
		}

		Range(int left, int val, Range next) {
			this.left = left;
			this.right = INF;
			this.val = val;
			this.next = next;
		}
	}

	private static final void update(int node, int start, int end, int left, int right, int val) {
		int mid;

		if (right < start || end < left) { // 업데이트 범위를 벗어나는 노드
			return;
		}
		if (left <= start && end <= right) { // 업데이트 범위에 완전히 포함되는 노드
			maxLazy[node] = Math.max(maxLazy[node], val); // 최대값 업데이트
			minLazy[node] = Math.min(minLazy[node], val); // 최소값 업데이트
			return; // Lazy : 하위 노드는 아직 업데이트 하지 않음
		}
		mid = start + end >> 1;
		update(node << 1, start, mid, left, right, val);
		update(node << 1 | 1, mid + 1, end, left, right, val);
	}

	private static final void prop(int node, int start, int end) {
		int mid;

		if (start == end) { // 각 리프노드 별로 출력해야 할 최대 또는 최소 값에
			sb.append((times[start] == MAX ? maxLazy[node] : minLazy[node]) & MOD).append(LINE_BREAK);
			return; // (1 << 17) - 1 과 AND 연산하여 P만 출력
		}
		maxLazy[node << 1] = Math.max(maxLazy[node], maxLazy[node << 1]); // propagate
		maxLazy[node << 1 | 1] = Math.max(maxLazy[node], maxLazy[node << 1 | 1]); // propagate
		minLazy[node << 1] = Math.min(minLazy[node], minLazy[node << 1]); // propagate
		minLazy[node << 1 | 1] = Math.min(minLazy[node], minLazy[node << 1 | 1]); // propagate
		mid = start + end >> 1;
		prop(node << 1, start, mid);
		prop(node << 1 | 1, mid + 1, end);
	}

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int p;
		int i;
		int time;
		int size;
		Range range;
		Range[] ranges;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		ranges = new Range[MAX_P];
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine(), " ", false);
			p = Integer.parseInt(st.nextToken()); // 문제 P의 범위를 [현재 시간, INF]로 설정
			ranges[p] = new Range(0, (Integer.parseInt(st.nextToken()) << SHIFT) | p);
		} // 값 = (L << 17) | P
		m = Integer.parseInt(br.readLine());
		time = 1; // 시계
		times = new char[m + 1]; // 각 시점 별로 recommend할 값이 최대인지 최소인지 저장
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			switch (st.nextToken().length()) {
				case RECOMMEND: // recommend할 값이 최대인지 최소인지
					times[time++] = st.nextToken().charAt(0); // 현재 시각 ++
					break;
				case ADD:
					p = Integer.parseInt(st.nextToken()); // 문제 P의 범위에 [현재 시각, INF] 추가
					ranges[p] = new Range(time, (Integer.parseInt(st.nextToken()) << SHIFT) | p, ranges[p]);
					break; // 값 = (L << 17) | P
				case SOLVED:
					p = Integer.parseInt(st.nextToken()); // 문제 번호 P
					if (ranges[p].left < time) { // 생성된 시간이 현재 시각보다 이전
						ranges[p].right = time - 1; // 범위 끝을 현재 시각 - 1 로 설정
					} else { // 생성 후 recommend 전에 삭제되는 문제
						ranges[p] = ranges[p].next; // 범위 삭제
					}
					break;
			}
		}
		size = time << 2;
		maxLazy = new int[size];
		minLazy = new int[size];
		for (i = 0; i < size; i++) { // 최소값 세그트리 INF 초기화
			minLazy[i] = INF;
		}
		time--; // 마지막 recommend 시각
		for (i = 1; i < MAX_P; i++) { // 각 범위에 대해 세그먼트 트리 업데이트
			for (range = ranges[i]; range != null; range = range.next) {
				update(1, 1, time, range.left, range.right, range.val);
			}
		}
		sb = new StringBuilder();
		prop(1, 1, time); // 모든 노드에 대해 propagate
		System.out.print(sb.toString());
	}
}
