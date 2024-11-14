import java.io.*;
import java.util.*;

public class BOJ1477_휴게소세우기 {

    private static int N;  
    private static int M;
    private static int L;

    private static int result = Integer.MAX_VALUE;

    private static int [] arr;

    private static void binarySearch(int srt, int en){

        if(srt > en) return;

        int mid = (srt+en)/2;

        if(install(mid) <= M){
            binarySearch(srt, mid-1);
            result = Math.min(result, mid);
        }
        else{
            binarySearch(mid+1, en);
        }
    }

    private static int install(int dis){

        int cnt = 0;

        for(int i = 0; i<arr.length-1; i++){
            int currDis = arr[i+1] - arr[i] -1;

            cnt += (currDis)/dis;
        }

        return cnt;

    }

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //휴게소의 개수
        M = Integer.parseInt(st.nextToken()); //더 지으려고 하는 휴게소의 개수
        L = Integer.parseInt(st.nextToken()); //고속도로의 길이

        arr = new int[N+2];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<N+1; i++) arr[i] = Integer.parseInt(st.nextToken());
        arr[0] = 0;
        arr[N+1] = L;

        Arrays.sort(arr);

        binarySearch(1,L-1);

        System.out.println(result);

    }
}
