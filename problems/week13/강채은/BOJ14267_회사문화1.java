import java.io.*;
import java.util.*;

public class BOJ14267_회사문화1 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken()); //회사 직원 수
        int m = Integer.parseInt(st.nextToken()); //칭찬 횟수

        int [] score = new int[n+1];
        int [] parent = new int[n+1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i<=n; i++){
            parent[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            
            int emp = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            
            score[emp] += cnt;
           
        }

        for(int i = 2; i<=n ; i++){
            score[i] += score[parent[i]];
        }

        for(int i = 1; i<=n; i++){
            sb.append(score[i]).append( " ");
        }

        System.out.println(sb.toString());

    }

}