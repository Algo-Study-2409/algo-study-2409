import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1260 DFS와 BFS
 * - 120 ms
 * - DFS + BFS
 * - 양방향 간선 입력
 * - 노드 별 Linked List 생성
 * - 정렬을 유지할 수 있는 위치에 간선 삽입
 * - DFS, BFS 방문하면서 출력
 * */
public class BOJ1260_DFS와BFS {
	private static final int MAX_N = 1_000;
	private static final char SPACE = ' ';
	private static final char LINE_BREAK = '\n';
	private static final Edge NIL = new Edge(Integer.MAX_VALUE, null);

	private static final class Edge {
		int to;
		Edge next;

		Edge(int to, Edge next) {
			this.to = to;
			this.next = next;
		}
	}

	private static boolean[] visited;
	private static Edge[] adj;
	private static StringBuilder sb;

	private static final void addEdge(int u, int v) {
		Edge edge;

		for (edge = adj[u]; edge.next.to < v; edge = edge.next); // 정렬을 유지할 수 있는 위치 탐색
		edge.next = new Edge(v, edge.next); // Linked List 중간에 삽입
	}

	private static final void dfs(int curr) { // DFS
		int to;
		Edge edge;

		for (edge = adj[curr].next; edge != NIL; edge = edge.next) { // head, tail 제외하고 탐색
			if (!visited[to = edge.to]) { // 방문 되지 않은 노드
				sb.append(to).append(SPACE); // 방문할 노드 출력
				visited[to] = true; // 방문 처리
				dfs(to); // 방문
			}
		}
	}

	private static final void bfs(int start) {
		int to;
		int curr;
		Edge edge;
		ArrayDeque<Integer> q;

		q = new ArrayDeque<>(MAX_N);
		sb.append(start).append(SPACE); // 시작 노드 출력
		visited[start] = true; // 시작 노드 방문 처리
		q.addLast(start); // 시작 노드 큐에 삽입
		while (!q.isEmpty()) {
			curr = q.pollFirst();
			for (edge = adj[curr].next; edge != NIL; edge = edge.next) { // head, tail 제외하고 탐색
				if (!visited[to = edge.to]) { // 방문 되지 않은 노드
					sb.append(to).append(SPACE); // 방문할 노드 출력
					visited[to] = true; // 방문 처리
					q.addLast(to); // 큐에 삽입
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int v;
		int v1;
		int v2;
		int i;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		adj = new Edge[n + 1];
		for (i = 1; i <= n; i++) { // 노드 별 Linked List 생성
			adj[i] = new Edge(0, NIL); // head.to = 0, tail.to = INF
		}
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ", false);
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			addEdge(v1, v2); // 정방향 간선 삽입
			addEdge(v2, v1); // 역방향 간선 삽입
		}
		sb = new StringBuilder();
		visited = new boolean[n + 1];
		sb.append(v).append(SPACE); // 시작 노드 출력
		visited[v] = true; // 시작 노드 방문 처리
		dfs(v); // DFS
		sb.append(LINE_BREAK);
		visited = new boolean[n + 1]; // 방문 배열 초기화
		bfs(v); // BFS
		System.out.print(sb.toString());
	}
}
