import java.util.*;
import java.io.*;

public class BOJ27924_윤이는엄청난것을훔쳐갔습니다 {

    private static int n; //노드 개수 
    
    private static Map<Integer,ArrayList<Integer>> graph;
    private static int [][] dist;

    private static void bfs(int st, int person){

        Deque<Integer> deque = new ArrayDeque<>();

        deque.add(st);
        dist[person][st] = 0;

        while(!deque.isEmpty()){
            
            int now = deque.poll();
            for(int next : graph.get(now)){
                if(dist[person][next] == -1 ){
                    dist[person][next] = dist[person][now] + 1;
                    deque.add(next);
                }
            }

        }

    }
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); //노드 개수 입력받기 

        graph = new HashMap<>();
        dist = new int[3][n+1];
        for(int i = 0; i<3; i++) Arrays.fill(dist[i], -1);

        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, v -> new ArrayList<>()).add(a);

        }
        
        //윤, 달구, 포닉스 위치 입력받기
        st = new StringTokenizer(br.readLine());

        bfs(Integer.parseInt(st.nextToken()),0); //윤
        bfs(Integer.parseInt(st.nextToken()),1); //달구
        bfs(Integer.parseInt(st.nextToken()),2); //포닉스

        for(int i = 1; i<n+1; i++){

            if(graph.get(i).size() != 1) continue;

            if(dist[0][i] < dist[1][i] && dist[0][i] < dist[2][i]) {
                System.out.println("YES");
                return;
            }
        }
        
        System.out.println("NO");
    }
}
