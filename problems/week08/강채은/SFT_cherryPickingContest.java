import java.io.*;
import java.util.*;

public class SFT_cherryPickingContest {
    
    private static int n;

    private static Map<Integer,ArrayList<Integer>> graph;
    private static long [] point;
    
    private static int [] subLen;
    private static boolean [] visited;

    private static void subSize(int st){

        subLen[st] = 1;
        visited[st] = true;

        for(int child : graph.get(st)){
            if(!visited[child]){
                subSize(child);
                subLen[st]+=subLen[child];
            }
        }
    }
    
    private static long game(int curr, int prev){

        ArrayList<Long> [] scoreArr = new ArrayList[2];
        for(int i = 0; i<2; i++) scoreArr[i] = new ArrayList<>();

        long score = point[curr];

        for(int child : graph.get(curr)){
            if(child == prev) continue;
            long nextS = game(child,curr);

            scoreArr[subLen[child] % 2].add(nextS);
        }

        for(int i = 0; i<2; i++) Collections.sort(scoreArr[i],Collections.reverseOrder());

        int a = 0;
        int b = 0;

        while(a < scoreArr[0].size()){
            if(scoreArr[0].get(a) < 0) break;
            score -= scoreArr[0].get(a);
            ++a;
        }

        while(b < scoreArr[1].size()){
            score += scoreArr[1].get(b) * ((b % 2 == 0) ? -1 : 1);
            ++ b;
        }

        while(a < scoreArr[0].size()){
            score += scoreArr[0].get(a) * ((b %2 == 0) ? -1 : 1);
            ++ a;
        }

        return score;

    }  

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        graph = new HashMap<>();
        point = new long[n+1];
        
        for(int i = 1; i<n; i++){
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.computeIfAbsent(a, v -> new ArrayList<Integer>()).add(b);
            graph.computeIfAbsent(b, v -> new ArrayList<Integer>()).add(a);
            
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=n; i++){
            point[i] = Long.parseLong(st.nextToken());
        }

        subLen = new int[n+1];
        visited = new boolean[n+1];
        subSize(1);

        long result = game(1, 1);

        if(result > 0 ) System.out.println("Sehun");
        else if(result < 0 ) System.out.println("Cheolmin");
        else System.out.println("Draw");

    }

}
