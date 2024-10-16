import java.util.*;
import java.io.*;

public class BOJ3190_뱀 {
	
	private static int n,k,l;
	
	private static int cnt = 0; // 게임 총 시간 
	
	private static int[][] board; //보드

	private static Deque<int []> snake;
	private static Deque<Direction> transfer;

	private static int [] dx = {0,-1,0,1};
	private static int [] dy = {1,0,-1,0};
	
	//방향
	static class Direction{
		int t; //시간
		int d; //방향
		
		public Direction(int t, int d){
			this.t = t;
			this.d = d;
		}	
	}
	
	// L : 왼쪽 방향 회전 
	private static int turnL(int dir) {
	
		if(dir == 3) dir = 0;
		else dir ++;
		
		return dir;
	}
	
	// D : 오른쪽 방향 회전 
	private static int turnD(int dir) {
	
		dir = (dir+3)%4;
		
		return dir;
	}
	
	private static boolean containsMv(int mvX,int mvY) {
		
		for(int [] arr : snake) {
			if(mvX == arr[0] && mvY == arr[1]) return true;
			
		}
		
		return false;
	}
	
	private static void move(int x, int y, int dir) {
		
		while(true) {
			
			cnt++;

			//방향 전환 정보  
			Direction direction = transfer.getFirst();
			
			//뱀 머리 위치 
			int mvX = x + dx[dir]; 
			int mvY = y + dy[dir]; 
			
			int [] mv = {mvX,mvY};

			if(mvX <=0 || mvY<=0 || mvX > n || mvY > n) break;
			if(containsMv(mvX,mvY)) break;
			
			snake.addLast(mv);

			if(board[mvX][mvY] == 1) board[mvX][mvY] = 0;
			else snake.poll();

			if(cnt == direction.t) {
				
				if(direction.d == 'D') dir = turnD(dir);
				else if(direction.d == 'L') dir = turnL(dir);
				
				if(transfer.size() != 1)transfer.poll();
				
			}
			
			//현재 위치를 늘어난 머리 위치로 
			x = mv[0];
			y = mv[1];
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		n = Integer.parseInt(br.readLine()); 
		k = Integer.parseInt(br.readLine()); 
		
		board = new int[n+1][n+1]; 
		
		//사과 위치 
		for(int i = 0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			board[r][l] = 1;
		}
	
		
		l = Integer.parseInt(br.readLine());
		transfer = new ArrayDeque<>();
		
		//방향 전환 정보
		for(int i = 0; i<l; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);

			transfer.add(new Direction(t,d));
		}
		
		snake = new ArrayDeque<>();
		snake.add(new int[] {1,1});
		
		move(1,1,0);
		
		System.out.println(cnt);
	}

}
