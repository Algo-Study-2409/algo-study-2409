import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 17298 오큰수
 * - 636 ms
 * - Deque
 * - N + 1 크기의 Deque 생성
 * - 입력받는 숫자들 tail에 추가
 * - head에 INF 추가
 * - tail에서 한 숫자씩 pollLast
 * - head가 숫자보다 커질 때까지 pollFirst
 * - NGE(현재 숫자의 인덱스) = peekFirst
 * - head에 현재 숫자 삽입
 * - NGE가 INF이면 -1
 * */
public class BOJ17298_오큰수 {
	private static final int INF = Integer.MAX_VALUE;
	private static final char SPACE = ' ';
	private static final char[] FAIL = {'-', '1', ' '};

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int num;
		int[] nge;
		ArrayDeque<Integer> dq;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dq = new ArrayDeque<>(n + 1);
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < n; i++) { // 입력받는 숫자들 tail에 추가
			dq.addLast(Integer.parseInt(st.nextToken()));
		}
		nge = new int[n];
		dq.addFirst(INF); // head에 INF 추가
		while (n-- > 0) {
			num = dq.pollLast(); // tail에서 한 숫자씩 pollLast
			while (num >= dq.peekFirst()) { // head가 숫자보다 커질 때까지 pollFirst
				dq.pollFirst();
			}
			nge[n] = dq.peekFirst(); // NGE(현재 숫자의 인덱스) = peekFirst
			dq.addFirst(num); // head에 현재 숫자 삽입
		}
		sb = new StringBuilder();
		for (int ans : nge) {
			if (ans == INF) { // NGE가 INF이면 -1
				sb.append(FAIL);
			} else {
				sb.append(ans).append(SPACE);
			}
		}
		System.out.print(sb.toString());
	}
}
