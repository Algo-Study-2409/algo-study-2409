import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 16234 인구 이동
 * - 220 ms
 * - DFS
 * - map 1 차원 변환
 * - 방문 하지 않은 노드에서 4방 탐색 DFS 출발
 * - 국경 열어야 하는 곳 순서대로 배열에 저장
 * - 한 번의 DFS에서 방문한 지역 수 큐에 저장
 * - 방문한 지역이 하나면 저장하지 않음
 * - 큐에서 숫자를 poll
 * - 해당 숫자만큼 배열에서 좌표 꺼내기
 * - 모인 좌표들 인구 이동 수행
 */
public class BOJ16234_인구이동 {
	private static int n;
	private static int l;
	private static int r;
	private static int col;
	private static int thr;
	private static int idx;
	private static int cnt;
	private static int[] d;
	private static int[] map;
	private static int[] positions;
	private static boolean[] visited;
	private static ArrayDeque<Integer> q;

	private static final void dfs(int pos) {
		int i;
		int npos;
		int diff;

		positions[idx++] = pos; // 국경 열어야 하는 곳 순서대로 배열에 저장
		visited[pos] = true; // 방문 처리
		cnt++; // 한 번의 DFS에서 방문한 지역 수 카운트
		for (i = 0; i < 4; i++) { // 4방 탐색
			npos = pos + d[i];
			if (visited[npos]) {
				continue;
			}
			diff = Math.abs(map[pos] - map[npos]);
			if (l <= diff && diff <= r) { // 열어야 하는 국경
				dfs(npos);
			}
		}
	}

	private static final boolean open() {
		int i;
		int j;

		idx = 0;
		for (i = col; i < thr; i += col) {
			for (j = 1; j <= n; j++) {
				if (!visited[i + j]) { // 방문 하지 않은 노드에서
					cnt = 0; // 한 번의 DFS에서 방문한 지역 수
					dfs(i + j); // DFS 출발
					if (cnt == 1) { // 방문한 지역이 하나면 저장하지 않음
						visited[i + j] = false; // 방문 처리 복구
						idx--; // 배열 인덱스 복구
					} else {
						q.addLast(cnt); // 한 번의 DFS에서 방문한 지역 수 큐에 저장
					}
				}
			}
		}
		return !q.isEmpty(); // 큐가 비어있으면 멈춤
	}

	private static final void move() {
		int i;
		int sum;
		int start;
		int end;

		end = 0;
		while (!q.isEmpty()) {
			start = end;
			end = start + q.pollFirst(); // 큐에서 숫자 꺼내기
			sum = 0;
			for (i = start; i < end; i++) { // 큐에서 꺼낸 숫자만큼 배열에서 좌표 꺼내기
				sum += map[positions[i]]; // 인구 합
				visited[positions[i]] = false; // 방문 처리 초기화
			}
			sum /= end - start; // 한 지역에 들어가야 할 인구 수
			for (i = start; i < end; i++) { // 해당 좌표들에 대해
				map[positions[i]] = sum; // 인구 설정
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int i;
		int j;
		int size;
		int time;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		col = n + 2;
		thr = (n + 1) * col;
		size = col * col;
		map = new int[size];
		visited = new boolean[size];
		for (i = 1; i <= n; i++) {
			visited[i] = true; // 위쪽 벽
		}
		for (i = col; i < thr; i += col) {
			visited[i] = true; // 왼쪽 벽
			st = new StringTokenizer(br.readLine(), " ", false);
			for (j = 1; j <= n; j++) {
				map[i + j] = Integer.parseInt(st.nextToken()); // map 1 차원 변환
			}
			visited[i + j] = true; // 오른쪽 벽
		}
		System.arraycopy(visited, 1, visited, thr + 1, n); // 아래쪽 벽
		d = new int[] {-col, 1, col, -1}; // 4방 탐색
		positions = new int[n * n];
		q = new ArrayDeque<>();
		for (time = 0; open(); time++) { // 국경 열기 : 큐가 비어있으면 멈춤
			move(); // 인구 이동
		}
		System.out.print(time);
	}
}
