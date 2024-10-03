//BOJ 실버2 - 1260:DFS와 BFS

import java.util.*;
import java.io.*;

public class BOJ_DFS와BFS{

    static Map<Integer,ArrayList<Integer>> graph ;

    static int n,m,v;
    static boolean[] dfsVisited;

    private static void bfs(int st , boolean [] visited){

        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        deque.add(st);
        visited[st] = true;
        sb.append(st).append(" ");

        while(!deque.isEmpty()){
            
            int key = deque.poll();
            
            
            for(int value : graph.get(key)){

                if(!visited[value]) {
                    sb.append(value).append(" ");
                    deque.add(value);
                }
                visited[value] = true;

            }

        }

        System.out.println(sb.toString());

    }

    private static void dfs(int st){

        if(!dfsVisited[st]) System.out.print(st+" ");
        dfsVisited[st] = true;

        for(int value : graph.get(st)){
            if(!dfsVisited[value]){
                dfs(value);
            }
            dfsVisited[value] = true;
        }

    }
    
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); //정점의 개수
        m = Integer.parseInt(st.nextToken()); //간선의 개수
        v = Integer.parseInt(st.nextToken()); //탐색을 시작할 정점의 번호

        graph = new HashMap<>();
        
        //HashMap 초기화 
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        //key-value 넣어주기 
        for(int i = 0; i<m; i++){

            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
    
        }

        //각 value값 정렬
        for(int key : graph.keySet()){
            Collections.sort(graph.get(key));
        }

        //dfs 실행
        dfsVisited = new boolean[n+1];
        dfs(v);

        System.out.println();

        //bfs 실행 
        bfs(v,new boolean[n+1]);

    }
}