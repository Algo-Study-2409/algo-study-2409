import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 2617 구슬 찾기
 * - 112 ms
 * - 플로이드-워셜
 * - u보다 가벼운 v들
 * - lighter[u][v]에 표시
 * - u보다 무거운 v들
 * - heavier[u][v]에 표시
 * - u > k이고 k > v이면 u > v
 * - u < k이고 k < v이면 u < v
 * - lighter 또는 heavier에 표시된 개수가
 * - N / 2를 초과하면 중간이 될 수 없는 구슬
 * */
public class BOJ2617_구슬찾기 {
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int u;
		int v;
		int k;
		int mid;
		int ans;
		int lighterCnt;
		int heavierCnt;
		boolean[][] lighter;
		boolean[][] heavier;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		lighter = new boolean[n + 1][n + 1]; // u보다 가벼운 v들
		heavier = new boolean[n + 1][n + 1]; // u보다 무거운 v들
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			lighter[u][v] = true;
			heavier[v][u] = true;
		}
		for (k = 1; k <= n; k++) { // 플로이드-워셜
			for (u = 1; u <= n; u++) {
				for (v = 1; v <= n; v++) {
					if (lighter[u][k] && lighter[k][v]) { // u > k이고 k > v이면
						lighter[u][v] = true; // u > v
					}
					if (heavier[u][k] && heavier[k][v]) { // u < k이고 k < v이면
						heavier[u][v] = true; // u < v
					}
				}
			}
		}
		ans = 0;
		mid = n >> 1;
		loop:
		for (u = 1; u <= n; u++) {
			lighterCnt = 0; // lighter에 표시된 개수
			heavierCnt = 0; // heavier에 표시된 개수
			for (v = 1; v <= n; v++) {
				if (lighter[u][v] && ++lighterCnt > mid) { // N / 2를 초과하면
					ans++; // 중간이 될 수 없는 구슬
					continue loop;
				}
				if (heavier[u][v] && ++heavierCnt > mid) { // N / 2를 초과하면
					ans++; // 중간이 될 수 없는 구슬
					continue loop;
				}
			}
		}
		System.out.print(ans);
	}
}
