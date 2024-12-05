import java.util.*;
import java.io.*;

public class BOJ12054_IPAddressSummarization {
    
    static class Node{

        Node [] child = new Node[2];
        boolean isEnd = false;

    }

    static Node insert(Node root, String subnet){

        String [] arr = subnet.split("/");
        String [] subnetArr = arr[0].split("\\.");

        int prefix = Integer.parseInt(arr[1]);

        int ipBit = (Integer.parseInt(subnetArr[0]) << 24) |
                    (Integer.parseInt(subnetArr[1]) << 16) |
                    (Integer.parseInt(subnetArr[2]) << 8) |
                    Integer.parseInt(subnetArr[3]);


        Node node = root;
        
        for(int i = 31; i>=32-prefix; i--){
            int bit = (ipBit >> i) & 1;
            if(node.child[bit] == null) node.child[bit] = new Node();
            node = node.child[bit]; 
        }

        node.isEnd = true;

        return root;

    }

    static ArrayList<String> search(Node node , int prefix , String subnet , ArrayList<String> result){

        if(node == null) return result;

        if(node.isEnd){
            result.add(print(prefix,subnet));
            return result;
        }

        for(int i = 0; i<2; i++){
            if(node.child[i] != null){
                subnet+=i;
                search(node.child[i], prefix+1, subnet, result);
                subnet = subnet.substring(0, subnet.length()-1);
            }
        }

        return result;

    }

    static String print(int prefix , String subnet){

        int [] ipArr = new int[4];

        subnet +="0".repeat(32-subnet.length());

        for(int i = 0; i<32; i+=8){
            ipArr[i/8] = Integer.parseInt(subnet.substring(i, i+8),2);        
        }

        return ipArr[0] + "." + ipArr[1] + "." + ipArr[2] + "." + ipArr[3] + "/" + prefix;

    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); 

        for(int i = 1; i<=t; i++){

            int n = Integer.parseInt(br.readLine());

            Node root = new Node();

            for(int j = 0 ; j<n; j++){
                root = insert(root,br.readLine());
            }
            
            ArrayList<String> result = new ArrayList<>();
            result = search(root, 0, "" , result);

            sb.append("Case #").append(i).append(":\n");
            for (String subnet : result) {
                sb.append(subnet).append("\n");
            }

        }

        System.out.println(sb.toString());
    }
    
}