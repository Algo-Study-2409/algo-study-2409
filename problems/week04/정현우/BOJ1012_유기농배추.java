import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1012 유기농 배추
 * - 80 ms
 * - DFS
 * - map 1 차원 변환
 * - 배추 위치 map에 true로 표시
 * - true인 지점들에서 출발
 * - 4방 탐색 DFS 타면서 false로 표시
 * - DFS 출발 할 때마다 카운트
 * */
public class BOJ1012_유기농배추 {
	private static final int COL = 52;
	private static final int MAX_K = 2_500;
	private static final int[] d = {-COL, 1, COL, -1};
	private static final char LINE_BREAK = '\n';

	private static int[] positions;
	private static boolean[] map;
	private static BufferedReader br;

	private static final void dfs(int pos) {
		int i;
		int npos;

		map[pos] = false; // DFS 타면서 true인 지점들 false로 표시
		for (i = 0; i < 4; i++) { // 4방 탐색
			npos = pos + d[i];
			if (map[npos]) {
				dfs(npos);
			}
		}
	}

	private static final int solution() throws IOException {
		int k;
		int i;
		int cnt;
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ", false);
		st.nextToken();
		st.nextToken();
		k = Integer.parseInt(st.nextToken());
		for (i = 0; i < k; i++) { // map 1 차원 변환
			st = new StringTokenizer(br.readLine(), " ", false);
			map[positions[i] = (Integer.parseInt(st.nextToken()) + 1) * COL + Integer.parseInt(st.nextToken()) + 1] = true;
		} // 배추 위치 map에 true로 표시
		cnt = 0;
		while (k-- > 0) {
			if (map[positions[k]]) { // true인 지점들에서 출발
				dfs(positions[k]); // DFS
				cnt++; // DFS 출발 할 때마다 카운트
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		positions = new int[MAX_K];
		map = new boolean[COL * COL];
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution()).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
