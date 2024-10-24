import java.util.*;

public class PGS_여행경로 {
    
     private static Map<String,ArrayList<String>> airTickets;
    private static Map<String,boolean [] > visited;

    private static String [] result ;
    
    private static boolean dfs(String st, int dep){
        
        result[dep] = st;
        
        if(dep == result.length-1)return true;   
        if(airTickets.get(st) == null) return false;   
        
        for(int i = 0; i<airTickets.get(st).size(); i++){
            if(!visited.get(st)[i]){
                visited.get(st)[i] = true;
                if(dfs(airTickets.get(st).get(i),dep+1)) return true;
                visited.get(st)[i] = false;
            }
        }

        return false;
    }
    
    public static String[] solution(String[][] tickets) {
        
        result = new String[tickets.length+1];
        airTickets = new HashMap<>();
        visited = new HashMap<>();

        //a공항 -> b공항 정보 입력 
        for(String [] ticket : tickets){
            airTickets.computeIfAbsent(ticket[0],v -> new ArrayList<>()).add(ticket[1]);
        }
        
        //방문할 수 있는 도시 내림차순 정렬 
        for(String key : airTickets.keySet()){
            Collections.sort(airTickets.get(key));
            visited.computeIfAbsent(key,v -> new boolean [airTickets.get(key).size()]);
        }
        
        
        dfs("ICN",0);
        return result;
        
    }
}