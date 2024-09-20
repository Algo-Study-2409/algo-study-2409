//BOJ 실버Ⅲ : 1021 - 회전하는 큐

import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        LinkedList<Integer> deque = new LinkedList<>();
        int [] want = new int[M];

        //deque에 숫자 삽입
        for(int i = 1; i<=N; i++) deque.add(i);
        
        //뽑아내려고 하는 수의 위치 삽입
        for(int i = 0; i<M; i++){
            want[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;

        for(int num : want){
            
            int index = deque.indexOf(num); //뽑아 낼 수의 위치 
            int mid = deque.size()/2; //뽑아 낼 수의 절대적 위치 선정을 위한 중간값
            int element = 0; 

            //뽑아내려고 하는 원소의 위치가 중간 원소의 위치보다 앞에 있거나 같으면
            if(index <= mid){
                for(int f = 0; f<index; f++){
                    element = deque.pollFirst();
                    deque.add(element);
                    cnt++;

                }
            }
            //뽑아내려고 하는 원소의 위치가 중간 원소의 위치보다 뒤에 있을 경우
            else{
                for(int b = deque.size(); b > index; b--){
                    element = deque.pollLast();
                    deque.addFirst(element);
                    cnt++;
                }
            }

            //맨 앞 원소 제거
            deque.removeFirst();
        }

        System.out.println(cnt);

    }

}
