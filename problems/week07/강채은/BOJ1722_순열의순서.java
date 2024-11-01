import java.io.*;
import java.util.*;

public class BOJ1722_순열의순서 {
    
    private static int N;
    private static int [] number ; //1~N개의 수 
    private static ArrayList<Integer> arr ; //1~N개의 수 

    private static long factorial(int num){

        if(num == 1 || num == 0) return 1;

        return num * factorial(num-1);

    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        number = new int[N+1];
        arr = new ArrayList<>();

        for(int i = 1; i<=N; i++){
            number[i] = i;
            arr.add(i);
        }

        st = new StringTokenizer(br.readLine());
        int question = Integer.parseInt(st.nextToken());

        switch (question) {
            case 1:
                long num = Long.parseLong(st.nextToken());
                solution1(num);
                break;
            case 2 :
                int [] permu = new int[N+1];
                for(int i = 1; i<=N; i++) permu[i] = Integer.parseInt(st.nextToken());
                solution2(permu);
                break;
            default:
                break;
        }

    }

    private static void solution1(long num) {

        StringBuffer sb  = new StringBuffer(); 

        int pos = 1;
        num--; // 인덱스를 맞추기 위해 1 빼기
    
        while (pos <= N) {
            long fac = factorial(N - pos); //pos번째 수가 특정수로 정해졌다고 할 때 
            int cursor = (int) (num / fac); //pos번째 수 정하기 위함 

            sb.append(arr.get(cursor)).append(" ");
            arr.remove(cursor);
            num %= fac;
            pos++;
        } 
        System.out.println(sb.toString());

    }
    
    private static void solution2(int[] permu) {

        long result = 1;
        for (int pos = 1; pos <= N; pos++) {
            int cursor = arr.indexOf(permu[pos]); 
            result += cursor * factorial(N - pos); 
            arr.remove(cursor);
        }
        System.out.println(result);
    }

}
