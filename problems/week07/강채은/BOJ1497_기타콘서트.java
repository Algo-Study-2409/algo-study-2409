import java.util.*;
import java.io.*;

public class BOJ1497_기타콘서트 {
    
    private static int N;
    private static int M;

    private static int [] total;

    private static ArrayList<String> guitar;
    private static boolean [] visited;

    private static void combi(int st, int dep, int sel){
        
        if(dep == sel){
            
            int cnt = 0 ;
            boolean [] y = new boolean[M];
            
            for(int i = 0; i<visited.length; i++){
                if(visited[i]){
                    for(int j = 0; j<M; j++){
                        if(guitar.get(i).charAt(j) == 'Y' && !y[j]) {
                            cnt +=1;
                            y[j] = true;
                        }
                    }
                }
            }
            
            total[dep] = Math.max(cnt, total[dep]);
            return;
        }
        
        for(int i = st; i<N; i++){
            visited[i] = true;
            combi(i+1, dep+1, sel);
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        guitar = new ArrayList<>();

        //기타, 연주 가능 곡 입력
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken(); //기타 이름은 굳이 사용하지 않아도 됨

            String song = st.nextToken();

            guitar.add(song);

        }

        total = new int[N+1];
        for(int i = 1; i<N+1; i++){
            total[i] = 0;
        }

        //기타 선택 개수
        for(int i = 1; i<=guitar.size(); i++){
            visited = new boolean[N];
            combi(0, 0, i);
        }
        
        int result = 0;
        int maxSong = Integer.MIN_VALUE;
        for(int i = 1; i<N+1; i++){
            if(total[i] == 0) continue;
            if(total[i] > maxSong){
                result = i;
                maxSong = total[i];
            }
            if(total[i] == maxSong){
                result = Math.min(i, result);
            }

        }

        System.out.println(result == 0 ? -1 : result);

    }

}
