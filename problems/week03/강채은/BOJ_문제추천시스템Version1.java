//BOJ 골드 4 : 21939 - 문제 추천 시스템 Version 1 
import java.util.*;
import java.io.*;

public class BOJ_문제추천시스템Version1 {
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); //문제 리스트에 있는 문제 개수

        TreeMap<Integer, TreeSet<Integer>> list = new TreeMap<>(); //문제 난이도가 Key인 TreeMap
        Map<Integer,Integer> list2 = new HashMap<>(); //문제 번호가 key인 HashMAp

        StringTokenizer st ;

        //초기 문제
        for (int i = 0 ; i<n; i++){
            
            st = new StringTokenizer(br.readLine());

            int value = Integer.parseInt(st.nextToken()); //문제 번호
            int key = Integer.parseInt(st.nextToken()); //문제 난이도

            list.computeIfAbsent(key , v-> new TreeSet<>()).add(value); //문제 난이도가 key
            list2.put(value,key); //문제 번호가 key
            
        }

        //추가될 문제
        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i<m; i++){

            st = new StringTokenizer(br.readLine());

            String operator = st.nextToken(); //연산자
            int p,l,x;
            
            switch (operator) {
                //문제를 새로 추가하는 경우
                case "add":
                    p = Integer.parseInt(st.nextToken()); // 문제 번호
                    l = Integer.parseInt(st.nextToken()); // 문제 난이도 

                    list.computeIfAbsent(l, v ->  new TreeSet<>()).add(p);
                    list2.put(p,l);

                    break;

                //문제 번호 출력
                case "recommend":
                    x = Integer.parseInt(st.nextToken());

                    int key = 0;

                    if(x == 1){

                        key = list.lastKey();
                        System.out.println(list.get(key).last());

                    }
                    else{
                    
                        key = list.firstKey();
                        System.out.println(list.get(key).first());

                    }
                    break;

                case "solved" :

                    //문제 번호에 해당하는 문제 난이도 구해주기
                    p =Integer.parseInt(st.nextToken());
                    int listKey = list2.get(p);

                    TreeSet<Integer> set = list.get(listKey);
                    set.remove(p); // 해당 문제 번호 삭제

                    //value값이 없으면 key값도 삭제
                    if (set.isEmpty()) {
                        list.remove(listKey);
                    }

                    list2.remove(p); // list2에서도 문제 번호 제거
                
                    break;

                default:
                    break;
            }
        }
    }
}
