import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 2636 치즈
 * - 64 ms
 * - BFS
 * - map 1 차원 변환
 * - q: 지금 녹는 치즈들
 * - q2: BFS 큐
 * - (1, 1)에서 첫 번째 BFS 시작
 * - q2에 위치 삽입하면서 BFS
 * - 치즈를 만나면 다음에 녹는 치즈이므로 q에 삽입
 * - q에 저장된 치즈들 각각에서 다음 BFS 실행
 * */
public class BOJ2636_치즈 {
	private static final char CHEESE = '1';
	private static final char WALL = '\0';
	private static final char LINE_BREAK = '\n';

	private static int qSize;
	private static int[] d;
	private static char[] map;
	private static boolean[] visited;
	private static ArrayDeque<Integer> q;
	private static ArrayDeque<Integer> q2;

	private static void bfs(int pos) {
		int i;
		int npos;

		q2.addLast(pos);
		while (!q2.isEmpty()) {
			pos = q2.pollFirst();
			for (i = 0; i < 4; i++) {
				npos = pos + d[i];
				if (visited[npos]) { // 이미 방문한 칸
					continue;
				}
				visited[npos] = true;
				if (map[npos] == WALL) { // 벽
					continue;
				}
				if (map[npos] == CHEESE) { // 치즈를 만나면
					q.addLast(npos); // 다음에 녹는 치즈이므로 q에 삽입
				} else {
					q2.addLast(npos); // q2에 위치 삽입
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int i;
		int row;
		int col;
		int size;
		int time;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = (Integer.parseInt(st.nextToken()) << 1) + 3;
		size = (row + 2) * col;
		map = new char[size]; // map 1 차원 변환
		for (i = 1; i <= row; i++) {
			br.read(map, i * col + 2, col - 4);
			br.read();
		}
		d = new int[] {2, col, -2, -col};
		visited = new boolean[size];
		q = new ArrayDeque<>(); // q: 지금 녹는 치즈들
		q2 = new ArrayDeque<>(); // q2: BFS 큐
		visited[1 * col + 2] = true;
		q.addLast(1 * col + 2); // (1, 1)에서 시작 (처음 녹는 치즈로 가정)
		for (time = -1; !q.isEmpty(); time++) {
			qSize = q.size(); // 지금 녹는 치즈들
			for (i = 0; i < qSize; i++) { // q에 저장된 치즈들 각각에서
				bfs(q.pollFirst()); // 다음 BFS 실행
			}
		}
		sb = new StringBuilder();
		System.out.print(sb.append(time).append(LINE_BREAK).append(time == 0 ? 0 : qSize).toString());
	}
}
