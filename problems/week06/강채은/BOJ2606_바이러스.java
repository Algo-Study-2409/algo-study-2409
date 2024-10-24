import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2606_바이러스 {

	private static int n; // 컴퓨터 수
	private static int m; // 연결된 컴퓨터 쌍의 수
	private static int cnt; // 감염된 컴퓨터 수 
	
	private static int [][] computer; 
	private static boolean [] visited;
	
	private static void dfs(int st) {
		
		visited[st] = true;
		
		for(int i = 1; i<=n; i++) {
			
			if(i == st) continue;
			
			if(!visited[i] && computer[st][i] == 1) {
					cnt ++;
					dfs(i);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		computer = new int[n+1][n+1];
		visited = new boolean[n+1];
		
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			computer[a][b] = computer[b][a] = 1; //연결된 컴퓨터 1로 처리  
			
		}
		
		cnt = 0 ;
		visited[1] = true;
		
		dfs(1);
		
		System.out.println(cnt);
		
	}

}
