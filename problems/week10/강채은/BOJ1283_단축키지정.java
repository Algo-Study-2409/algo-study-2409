import java.util.*;
import java.io.*;

public class BOJ1283_단축키지정 {
 
    private static boolean [] alpha;

    private static boolean key(String [] result, int chIdx, String splitstr, int order){

        char chr = splitstr.charAt(chIdx);
        int idx = Character.toUpperCase(chr) - 'A';

        if(!alpha[idx]) {
            alpha[idx] = true;

            result[order] = splitstr.substring(0, chIdx) + "[" + chr + "]" + splitstr.substring(chIdx + 1);
            
            System.out.println(String.join(" ",result));
            return true;

        }

        return false;

    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        alpha = new boolean[26];

        for(int i = 0; i<n; i++){

            String str = br.readLine();
            String [] result = str.split(" ");

            boolean check = false;

            // 각 단어의 첫 글자 확인 
            for(int j = 0; j<result.length; j++){
                check = key(result, 0, result[j],j);
                if(check) break;
            }

            //첫글자가 이미 모두 사용되었다면
            for(int j = 0; j<result.length; j++){
                for(int s = 1; s<result[j].length(); s++){
                    if(check) break;
                    check = key(result, s, result[j],j);
                }
            } 
            
            if(!check) System.out.println(String.join(" ",result));

        }

    }

}
