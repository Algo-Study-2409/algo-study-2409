import java.util.*;
import java.io.*;

public class BOJ2110_공유기설치{

    private static int N;
    private static int C;

    private static int result;

    private static int [] arr;

    private static void binarySearch(int min, int max){

        if(min > max) return;

        int mid = (min+max)/2;

        if(install(mid) < C){
            binarySearch(min, mid-1);
        }
        else{
            binarySearch(mid+1, max);
            result = Math.max(result,mid);
        }

        return;
    }

    private static int install(int dis){

        int cnt = 1;
        int curr = arr[0];

        for(int i = 1; i<N; i++){
            if(arr[i] - curr >= dis){
                cnt ++;
                curr = arr[i];
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //집의 개수
        C = Integer.parseInt(st.nextToken()); //공유기 개수

        arr = new int [N];

        for(int i =0; i<N; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);


        binarySearch(1, arr[N-1]-arr[0]);

        System.out.println(result);


    }

}