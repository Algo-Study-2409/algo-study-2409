import java.util.*;
import java.io.*;

public class BOJ7795_먹을것인가먹힐것인가 {

    static int [] number ;
    static int [] search ;
    
    static int n;
    static int m;

    static int result;

    static void binarySearch(int str, int en, int num) {

        if (str > en) return; 
    
        int mid = (str + en) / 2;
    
        if (search[mid] < num) {
            result += (mid - str + 1);
            binarySearch(mid + 1, en, num);
        } else {
            binarySearch(str, mid - 1, num);
        }
    }
    

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T -- > 0){
            
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            number = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<n; i++) number[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            search = new int[m];
            for(int i = 0; i<m; i++) search[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(search);

            result = 0;

            for(int i = 0; i<n; i++){
                binarySearch(0,m-1,number[i]);
            }
            System.out.println(result);
            

        }
        
    }
}
