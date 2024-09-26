//BOJ 실버Ⅱ : 1182 - 부분수열의 합
// https://stdio-han.tistory.com/96 도움

import java.util.*;
import java.io.*;

public class BOJ1182_부분수열의_합 {

    static int n,s,result;
    static int [] num;

    private static void dfs(int dep, int sum){
        if(dep == n){
            if(sum == s) {
                result++;
            }
            return;  
        }

        dfs(dep+1, sum+num[dep]); //현재 수를 선택했을 때
        dfs(dep+1, sum); //현재 수를 선택하지 않았을 때 

    }
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        result = s == 0 ? -1:0;

        num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(0,0);

        System.out.println(result);
        
    }

}
