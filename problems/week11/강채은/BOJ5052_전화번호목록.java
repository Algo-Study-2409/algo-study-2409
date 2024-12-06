import java.util.*;
import java.io.*;

public class BOJ5052_전화번호목록 {
    
    static boolean check(int n , String [] number){
        for(int i = 0 ; i<n-1; i++){
            if(number[i+1].startsWith(number[i])) return false;
        }

        return true;
    }
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        
        while(t-- > 0){

            int n = Integer.parseInt(br.readLine());
            String [] number = new String[n];

            for(int i = 0; i<n; i++) number[i] = br.readLine();

            Arrays.sort(number);

            if(check(n,number)) System.out.println("YES");
            else System.out.println("NO");

        }

    }


}
