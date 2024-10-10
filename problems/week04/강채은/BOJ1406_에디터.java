import java.io.*;
import java.util.*;

public class BOJ1406_에디터{

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        LinkedList<Character> editor = new LinkedList<>();
        char [] str = br.readLine().toCharArray();//편집기에 입력되어 있는 문자열 
        for(char c : str) editor.add(c);

        int n = Integer.parseInt(br.readLine()); //실행할 명령어 개수
        int cursor = editor.size(); //커서의 위치 
         
        StringTokenizer st ;
        char cm ;

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            cm = st.nextToken().charAt(0);

            switch (cm) {
                //ch라는 문자를 커서 왼쪽에 추가 
                case 'P':
                    char ch = st.nextToken().charAt(0);
                    editor.add(cursor,ch); 
                    cursor ++;

                    break;

                //커서를 왼쪽으로 한 칸 옮김   
                case 'L' :
                    if(cursor == 0) break;
                    cursor --;
                    break;
                
                //커서를 오른쪽으로 한 칸 옮김 
                case 'D' :
                    if(cursor == editor.size()) break;
                    cursor ++;
                    break;
                
                //커서의 왼쪽 문자 삭제 
                case 'B':
                    if(cursor != 0){
                        cursor--;
                        editor.remove(cursor);
                    } 
                    else break;
                
                default:
                    break;
            }
        }

        for(char c : editor) System.out.print(c);

    }

}