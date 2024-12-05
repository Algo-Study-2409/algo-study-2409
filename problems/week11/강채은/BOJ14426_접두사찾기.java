import java.util.*;
import java.io.*;

public class BOJ14426_접두사찾기{

    static class Node{

        Node [] child = new Node[26];

    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Node root = new Node(); 

        for(int i = 0; i<n; i++){
            
            String str = br.readLine();
            Node node = root;
            
            for(char c : str.toCharArray()){
                
                int intC = c - 'a';

                if(node.child[intC] == null) node.child[intC] = new Node();
                node = node.child[intC];

            }
        }

        int cnt = 0;

        for(int i = 0; i<m; i++){
            
            String findStr = br.readLine();
            Node findN = root;

            for(int j = 0; j<findStr.length(); j++){
                
                int cInt = findStr.charAt(j) - 'a';
                if(findN.child[cInt] == null) break;

                findN = findN.child[cInt];
                if(j == findStr.length()-1) cnt++;

            }

        }

        System.out.println(cnt);
        
    }

}