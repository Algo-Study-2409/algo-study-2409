import java.util.*;

public class PGS_가사검색 {
    
    static Node [] reverseWordArr = new Node[10001];
    static Node [] wordArr = new Node[10001];

    static class Node{
        
        Map<Character,Node> child = new HashMap<>();
        int wordCnt = 0;
        
    }
    
    static void insert(String str , boolean reverse){

        Node node;

        if(reverse) node = reverseWordArr[str.length()];
        else node = wordArr[str.length()];
        
        if(node == null){
            node = new Node();
            if(reverse) reverseWordArr[str.length()] = node;
            else wordArr[str.length()] = node;
        }

        for(char c : str.toCharArray()){

            node.wordCnt++;
            node.child.putIfAbsent(c, new Node());
            node = node.child.get(c);
        }

        node.wordCnt ++ ;

    }
    
    static int search(String str , boolean reverese){

        Node node;

        if(reverese) node = reverseWordArr[str.length()];
        else node = wordArr[str.length()];

        if(node == null) return 0;

        for(char c : str.toCharArray()){

            if(c == '?') return node.wordCnt;
            node = node.child.get(c);
            if(node == null) return 0;

        }
        //System.out.println(node.wordCnt);
        return node.wordCnt;
    }

    public int[] solution(String[] words, String[] queries) {
        
        for(String str : words){
            insert(str,false);
            insert(new StringBuffer(str).reverse().toString(),true);
        }

        int [] result = new int[queries.length];

        for(int i = 0; i<queries.length; i++){
            if(queries[i].charAt(0) == '?') result[i] = search(new StringBuffer(queries[i]).reverse().toString(), true);
            else result[i] = search(queries[i], false);
        }
        
        for(int i : result) System.out.println(i);
        return result;
        
    }

    public static void main(String[] args) {
        
        PRM_가사검색 prm = new PRM_가사검색();
        prm.solution(new String [] {"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[] {"fro??", "????o", "fr???", "fro???", "pro?"});

    }
}