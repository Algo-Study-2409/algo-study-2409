import java.util.*;
import java.io.*;

public class BOJ26086_어려운스케줄링{
    
    private static int N,Q,K;
    
    public static void main(String [] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        Deque<Integer> deque = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        int [] cmdList = new int[Q];
        int [] number = new int[Q];

        int lastIdx = 0;
        boolean reverse = false;

        for(int i = 0; i<Q; i++){

            st = new StringTokenizer(br.readLine());

            cmdList[i] = Integer.parseInt(st.nextToken());
            if(cmdList[i] == 0) {
                number[i] = Integer.parseInt(st.nextToken());
            }
            else if(cmdList[i] == 1) lastIdx = i;

        }

        for(int i = 0; i<lastIdx; i++){
            if(cmdList[i] == 0) pq.add(number[i]);
        }

        while(!pq.isEmpty()){
            deque.add(pq.poll());
        }

        //numIdx = deque.size()-1;

        // System.out.println("***");
        // for(int i :deque){
        //     System.out.println(i);
        // }
        
        for(int i = lastIdx+1; i<Q; i++){
            if(cmdList[i] == 2) {
                reverse = !reverse;
            }else if(cmdList[i] == 0){
                if(!reverse)deque.addFirst(number[i]);
                else deque.add(number[i]);
            }
        }

        // System.out.println("==========");
        // System.out.println(lastIdx);
        // System.out.println("&&");
        // for(int i :deque){
        //     System.out.println(i);
        // }
        // System.out.println("==========");
        // System.out.println(reverse);

        for(int i = 1; i<K; i++ ){
            //System.out.printf("순서 : %d\n",i);
            if(reverse) deque.pollLast();
            else deque.poll();
        }

         System.out.println(reverse ? deque.pollLast() : deque.poll());

    }
    
}
