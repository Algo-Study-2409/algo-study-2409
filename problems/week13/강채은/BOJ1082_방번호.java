import java.util.*;
import java.io.*;

public class BOJ1082_방번호 {

    static class Room{
        
        int num;
        int price;

        public Room(int num, int price){
            this.num = num;
            this.price = price;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int n = Integer.parseInt(br.readLine());
        
        Room [] rooms = new Room[n];
        Room minP = new Room(0,Integer.MAX_VALUE);
        
        st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i<n; i++){
            rooms[i] = new Room(i,Integer.parseInt(st.nextToken()));
            
            if(rooms[i].price < minP.price ) minP = new Room(i, rooms[i].price);
        }
        
        int m = Integer.parseInt(br.readLine());
        char [] result = new char[m/minP.price];
        m-= (m/minP.price) * minP.price;

        Arrays.fill(result, (char)minP.num);

        int idx = 0;

        for(int i = 0; i<result.length; i++){
            for(int j = n-1 ; j>=0 ; j--){
                if(rooms[j].price <= minP.price + m){
                    result[i] = (char) (j + '0');
                    m += minP.price - rooms[j].price;
                    break;
                }
            }

            if(result[idx] == '0'){
                idx ++;
                m += minP.price;
            }

        }

        if(idx == result.length){
            System.out.println(0);
            return;
        }

        String answer = "";
        for(int i = idx ; i<result.length; i++){
            answer += result[i];
        }

        System.out.println(answer);

    }

}
