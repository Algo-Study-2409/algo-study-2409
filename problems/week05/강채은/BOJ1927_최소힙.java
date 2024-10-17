import java.util.*;
import java.io.*;

public class BOJ1927_최소힙{
    
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        while(n -- > 0){
            
            int num = Integer.parseInt(br.readLine());

            if(num == 0){
                if(pq.isEmpty()) {
                    System.out.println(0);
                    continue;
                }
              System.out.println(pq.poll());  
              continue;
            } 
            pq.add(num);
            
        }
        
    }
    
}