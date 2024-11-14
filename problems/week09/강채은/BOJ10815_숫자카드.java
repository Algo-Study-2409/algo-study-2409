import java.util.*;
import java.io.*;

public class BOJ10815_숫자카드 {

    static int n;
    static int [] card;

    static int m;

    static String binarySearch(int str, int en, int num) {
       
        if (str > en) return "0";
        int mid = (en + str) / 2;
    
        if (card[mid] > num) {
            return binarySearch(str, mid - 1, num); 
        } else if (card[mid] < num) {
            return binarySearch(mid + 1, en, num); 
        } else if (card[mid] == num) {
            return "1";
        }
    
        return "0";
    }

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); //상근이가 가지고 있는 카드 개수
        card = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++) card[i] = Integer.parseInt(st.nextToken());
        
        Arrays.sort(card);

        m = Integer.parseInt(br.readLine()); //상근이가 가지고 있는지 체크해야 할 카드 개수

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<m; i++) {
            sb.append(binarySearch(0,n-1, Integer.parseInt(st.nextToken()))).append(" ");
        }
        System.out.println(sb.toString());
    }

}

/**
 * 1초가 걸릴 때 입력의 최대 크기
 * O(N) 1억
 * O(N^2) 약 1만
 * O(N^3) 약 500
 * O(2^n) 약 20
 * O(N!) 10
 */