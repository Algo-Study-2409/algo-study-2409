import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 14888 연산자 끼워넣기
 * - 68 ms
 * - DFS
 * - 연산자별 개수를 파라미터로 DFS
 * - 개수가 남아있는 연산자 하나씩 적용
 * - 마지막 depth에서 최대, 최소값 갱신
 * */
public class BOJ14888_연산자끼워넣기 {
	private static final int INF = Integer.MAX_VALUE;
	private static final int MIN = Integer.MIN_VALUE;
	private static final char LINE_BREAK = '\n';

	private static int n;
	private static int max;
	private static int min;
	private static int[] arr;

	private static final void dfs(int depth, int num, int add, int sub, int mul, int div) {
		if (depth == n) { // 마지막 depth
			if (num > max) { // 최대값 갱신
				max = num;
			}
			if (num < min) { // 최소값 갱신
				min = num;
			}
			return;
		}
		depth++; // 다음 depth
		if (add != 0) { // 덧셈
			dfs(depth, num + arr[depth], add - 1, sub, mul, div);
		}
		if (sub != 0) { // 뺄셈
			dfs(depth, num - arr[depth], add, sub - 1, mul, div);
		}
		if (mul != 0) { // 곱셈
			dfs(depth, num * arr[depth], add, sub, mul - 1, div);
		}
		if (div != 0) { // 나눗셈
			dfs(depth, num / arr[depth], add, sub, mul, div - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		int i;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ", false);
		arr = new int[n];
		for (i = 0; i < n; i++) { // 숫자 입력
			arr[i] = Integer.parseInt(st.nextToken());
		}
		n--; // 마지막 depth
		max = MIN;
		min = INF;
		st = new StringTokenizer(br.readLine(), " ", false); // 연산자별 개수를 파라미터로 DFS
		dfs(0, arr[0], Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		System.out.print(new StringBuilder().append(max).append(LINE_BREAK).append(min));
	}
}
