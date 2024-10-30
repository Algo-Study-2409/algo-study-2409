import java.util.*;
import java.io.*;

public class BOJ19535_ㄷㄷㄷㅈ {
    
    public static void main(String[] args) throws Exception {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Map <Integer, ArrayList<Integer>> map = new HashMap<>();

        StringTokenizer st;
        for(int i = 0; i<N-1; i++){
            
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(u, val -> new ArrayList<>()).add(v);
            map.computeIfAbsent(v, val -> new ArrayList<>()).add(u);

        }
        
        long d = 0;
        long g = 0;
        boolean [] visited = new boolean[N+1];

        for(int key : map.keySet()){

            //ㅈ -> 자식이 3개 이상이면 됨. (3개 이상일 경우엔 nC3)
            int len = map.get(key).size();
            if(len >= 3) g += (len*(len-1)*(len-2))/6;
            
            //ㄷ -> 연결된 두 정점의 자식 수의 곱

            for(int val : map.get(key)){
                if(!visited[val]) d+= (len-1) * (map.get(val).size()-1); 
            }

            visited[key] = true;

        }

        if(d > 3*g) System.out.println("D");
        else if(d < 3*g) System.out.println("G");
        else System.out.println("DUDUDUNGA");

    }

}
