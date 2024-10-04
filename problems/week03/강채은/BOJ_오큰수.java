//BOJ 골드 4 : 17298 - 오큰수 
import java.util.*;
import java.io.*;

public class BOJ_오큰수 {
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); //수의 개수
        int [] number = new int[n]; //배열 선언
        
        //수 입력받기 
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        
        Stack<Integer> stack = new Stack<>();
  
        //배열을 하나씩 순회하면서 오큰수 찾기
        for(int i = 0; i<n; i++){
            
            while(!stack.isEmpty()){
                //stack에 값이 존재하고 stack에서 꺼낸 index에 들어있는 값이 현재 값보다 작으면 오큰수를 찾은 거임
                if(number[stack.peek()] < number[i]){
                    number[stack.pop()] = number[i];
                }
                else{
                    break;
                }
            }

            //현재 index도 오큰수를 찾기 위해 stack에 넣어주기 
            stack.add(i);
        }
        
        while(!stack.isEmpty()){
            number[stack.pop()] = -1;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i : number){
            sb.append(i).append(" ");
        }

        System.out.println(sb);

    }
}
