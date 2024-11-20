import java.util.*;
import java.io.*;

public class BOJ17281_야구 {

	private static int N;
    private static int result = Integer.MIN_VALUE;

	static boolean[] visited;
	static int[] players;
    
	static int[][] score;
	
	// 타순 정하기
	private static void dfs(int str) {
		
		if(str == 10) {
			game();
			return;
		}
		
		for(int i=1;i<=9;i++) {
			if(!visited[i]) {
				visited[i] = true;
				players[i] = str;
				dfs(str+1);
				visited[i] = false;
			}
		}
	}
	
	// 게임 진행
	private static void game() {

		int now = 1;
		int cnt = 0;
		
		for(int i=0;i<N;i++) {
			
			// 아웃,1루,2루,3루,홈
			int[] pos = {0,0,0,0,0};
			
			while(pos[0]<3) {
				move(pos,score[i][players[now]]);

				if(now == 9) now =1 ;
			    else now++;
			}
			
			cnt += pos[4];
		}
		
        result = Math.max(result, cnt);

	}
	
	// 모든 주자들 이동
	private static void move(int[] pos, int n) {
		
		for(int i=0;i<n;i++) {
			pos[4]+=pos[3];
			pos[3]=pos[2];
			pos[2]=pos[1];
			pos[1]=0;
		}
		
		pos[n]++;
	}
	

    public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		score = new int[N][10];

        visited = new boolean[10];
        players = new int[10];

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=9;j++) {
				score[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		// 1번 선수는 4번 타자
		players[4] = 1;
		visited[4]=true;
		
		dfs(2);

		System.out.println(result);

	}

}