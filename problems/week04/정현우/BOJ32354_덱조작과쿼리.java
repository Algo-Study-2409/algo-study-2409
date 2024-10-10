import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 32354 덱 조작과 쿼리
 * - 508 ms
 * - Linked List
 * - prev를 가르키는 Linked List로 구성
 * - 시간별 마지막 노드 저장
 * - push x : 마지막 노드에 x를 더한 값을 가진 노드 연결
 * - pop : prev로 이동
 * - restore i : 시간 i의 노드 불러오기
 * - print : 마지막 노드에 저장된 값 출력
 * */
public class BOJ32354_덱조작과쿼리 {
	private static final int PUSH = 4;
	private static final int POP = 3;
	private static final int RESTORE = 7;
	private static final int PRINT = 5;
	private static final char LINE_BREAK = '\n';

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int[] idx;
		int[] prev;
		long[] sum;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		idx = new int[n + 1]; // 시간별 마지막 노드의 인덱스
		prev = new int[n + 1]; // 노드가 가르키는 prev
		sum = new long[n + 1]; // 노드에 저장된 값
		sb = new StringBuilder();
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			switch (st.nextToken().length()) {
				case PUSH: // 마지막 노드에 x를 더한 값을 가진 노드 연결
					sum[idx[i] = i] = sum[prev[i] = idx[i - 1]] + Long.parseLong(st.nextToken());
					break;
				case POP: // prev로 이동
					idx[i] = prev[idx[i - 1]];
					break;
				case RESTORE: // 시간 i의 노드 불러오기
					idx[i] = idx[Integer.parseInt(st.nextToken())];
					break;
				case PRINT: // 마지막 노드에 저장된 값 출력
					sb.append(sum[idx[i] = idx[i - 1]]).append(LINE_BREAK);
					break;
			}
		}
		System.out.print(sb.toString());
	}
}
