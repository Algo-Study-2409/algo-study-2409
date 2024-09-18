//BOJ 골드Ⅳ : 2617 - 구슬 찾기
import java.io.*;
import java.util.*;

public class Main {
    
	static int N,M;

	static ArrayList<Integer>[] num ; //각 구슬보다 가벼운 구슬을 담을 리스트 선언
	static int [][] result ; //결과를 담을 배열
    static boolean []visited; //방문 여부 확인


	//dfs를 통해 각 구슬보다 가벼운, 무거운 구슬을 찾아줌
	static void dfs(int start, int end){

		visited[start] = true;

		for(int element : num[start]){
			if(!visited[element]){
				result[end][0]++;
				result[element][1]++;
				dfs(element, end);
			}
		}
	}

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

		int cnt = 0; //중간 구슬이 될 수 없는 구슬의 개수
        int mid = N/2; 

		num = new ArrayList[N];
		result = new int[N][2];

		//각 요소를 ArrayList로 초기화
		for(int i = 0; i<N; i++){
			num[i] = new ArrayList<>();
		}
		
        for(int i = 0 ; i<M; i++){
			st = new StringTokenizer(br.readLine());
			
            int hNum = Integer.parseInt(st.nextToken())-1;
            int lNum = Integer.parseInt(st.nextToken())-1;
			
			num[hNum].add(lNum);
			
        }
		
		for(int i =0; i<N; i++){
			visited = new boolean[N];
			dfs(i,i);
		}
        
		//자신보다 가볍거나 무거운 구슬을 (총 구슬의 수/2)=mid보다 큰 개수만큼 가지고 있다면 중간 구슬이 될 수 없음
		for(int i = 0; i<N; i++){
			if(result[i][0] > mid || result[i][1] > mid) cnt++;
		}
        System.out.println(cnt);

    }

}
