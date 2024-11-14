import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 7795 먹을 것인가 먹힐 것인가
 * - 304 ms
 * - 투 포인터
 * - A, B 배열 정렬
 * - B 기준으로 A 뒤에서부터 탐색
 * - B 보다 작거나 같은 A 만나면
 * - B 보다 큰 A 개수 추가 후
 * - B 다음 요소로 이동
 * */
public class BOJ7795_먹을것인가먹힐것인가 {
	private static final int MIN = Integer.MIN_VALUE;
	private static final int MAX_NM = 20_001;
	private static final char LINE_BREAK = '\n';

	private static BufferedReader br;

	private static final int solution(int[] a, int[] b) throws IOException {
		int n;
		int m;
		int i;
		int cnt;
		int idxA;
		int idxB;
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 1; i <= n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 1; i <= m; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(a, 1, n + 1); // A 배열 정렬
		Arrays.sort(b, 1, m + 1); // B 배열 정렬
		cnt = 0; // A 가 B 를 먹을 수 있는 쌍의 수
		idxA = n;
		idxB = m;
		while (idxB != 0) { // B 기준으로 A 뒤에서부터 탐색
			if (a[idxA] > b[idxB]) { // A 가 B 보다 크면
				idxA--; // A 다음 요소로 이동
			} else { // B 보다 작거나 같은 A 만나면
				idxB--; // B 다음 요소로 이동
				cnt += n - idxA; // B 보다 큰 A 개수 추가
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		int t;
		int[] a;
		int[] b;
		StringBuilder sb;

		a = new int[MAX_NM];
		b = new int[MAX_NM];
		a[0] = MIN;
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution(a, b)).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
