import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 정현우 : BOJ 1406 에디터
 * - 256 ms
 * - Linked List
 * - 원형 양방향 Linked List 구성
 * - NIL 노드에서 출발
 * - L : NIL이 아니면 left로 커서 이동
 * - D : right가 NIL이 아니면 right로 커서 이동
 * - B : NIL이 아니면
 * -   현재 노드의 left와 right 연결
 * -   left로 커서 이동
 * - P $ : $ 노드 생성
 * -   $ 노드와 right 노드 연결
 * -   현재 노드와 $ 노드 연결
 * -   새로운 노드로 커서 이동
 * - NIL의 right 부터 NIL 전까지
 * - right 타고 이동하면서 출력
 * */
public class BOJ1406_에디터 {
	private static final int NIL = 0;
	private static final int MAX_LEN = 600_001;
	private static final int LINE_BREAK = '\n';
	private static final int L = 'L';
	private static final int D = 'D';
	private static final int B = 'B';
	private static final int P = 'P';

	public static void main(String[] args) throws IOException {
		int m;
		int idx;
		int pos;
		int size;
		int[] left;
		int[] right;
		char[] str;
		char[] ans;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		str = new char[MAX_LEN];
		left = new int[MAX_LEN];
		right = new int[MAX_LEN];
		pos = NIL;
		while ((str[++pos] = (char) br.read()) != LINE_BREAK);
		size = --pos;
		for (idx = NIL; idx < pos; idx++) {
			left[idx + 1] = idx;
			right[idx] = idx + 1;
		}
		left[NIL] = pos;
		right[pos] = NIL;
		m = Integer.parseInt(br.readLine());
		while (m-- > 0) {
			switch (br.read()) {
				case L:
					if (pos != NIL) { // NIL이 아니면
						pos = left[pos]; // left로 커서 이동
					}
					break;
				case D:
					if ((pos = right[pos]) == NIL) { // right가 NIL이 아니면
						pos = left[NIL]; // right로 커서 이동
					}
					break;
				case B:
					if (pos != NIL) {
						left[right[pos]] = left[pos]; // 현재 노드의 left와 right 연결
						right[left[pos]] = right[pos];
						pos = left[pos]; // left로 커서 이동
						size--;
					}
					break;
				case P:
					br.read();
					str[++idx] = (char) br.read(); // $ 노드 생성
					left[right[pos]] = idx; // $ 노드와 right 노드 연결
					right[idx] = right[pos];
					left[idx] = pos; // 현재 노드와 $ 노드 연결
					right[pos] = idx;
					pos = idx; // 새로운 노드로 커서 이동
					size++;
					break;
			}
			br.read();
		}
		idx = 0;
		ans = new char[size];
		for (pos = right[NIL]; pos != NIL; pos = right[pos]) {
			ans[idx++] = str[pos]; // NIL의 right 부터 NIL 전까지 right 타고 이동하면서 출력
		}
		System.out.print(ans);
	}
}
