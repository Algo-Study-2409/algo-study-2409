import java.util.*;
import java.io.*;

public class BOJ1927_최소힙{
    
    private static PriorityQueue<Integer> heap;

    private static void addNum(int num){

        heap.add(num);

    }

    private static void removeNum(){
        
        if(heap.isEmpty()) System.out.println(0);
        else System.out.println(heap.poll());

    }

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        heap = new PriorityQueue<>();
        
        while(n -- > 0){
            
            int num = Integer.parseInt(br.readLine());

            if(num == 0) removeNum();
            else addNum(num);

        }
        
    }
    
}