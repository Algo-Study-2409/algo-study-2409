import java.util.*;
import java.io.*;

public class BOJ14725_개미굴{

    static class Node{

        HashMap<String,Node> child = new HashMap<>() ;

    }

    static void ant(String dep , Node node){

        ArrayList<String> key = new ArrayList<>(node.child.keySet()); 
        Collections.sort(key);

        for(String str : key){
            System.out.println( dep + str);
            ant(dep+"--", node.child.get(str));
        }
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Node root = new Node();

        for(int i = 0; i<n; i++){

            st = new StringTokenizer(br.readLine());
            Node node = root;

            int k = Integer.parseInt(st.nextToken());

            for(int j = 0; j<k; j++){
                
                String str = st.nextToken();

                if(!node.child.containsKey(str)) node.child.put(str, new Node());
                node = node.child.get(str);
                
            }

        }

        ant("", root);
    }

}

