import java.util.*;

public class PGS_징검다리 {
    
    static int [] arr;
    static int result;

    static void binarySearch(int st, int en, int n){

        if(st > en) return;

        int mid = (st+en)/2;
        
        if(removeR(mid,n) > n){
            binarySearch(st, mid-1, n);
        }
        else{
            binarySearch(mid+1, en, n);
            result = Math.max(result,mid);
        }

    }

    private static int removeR(int dist, int n){
        
        int cnt = 0;
        int curr = 0;
        
        for(int i = 1; i<arr.length; i++){
            if(arr[i] - arr[curr] < dist) cnt++;
            else curr = i;
        }

        return cnt;
    }
    
    public int solution(int distance, int[] rocks, int n) {
        
        arr = new int[rocks.length + 2];
        for(int i = 0; i<rocks.length; i++) arr[i] = rocks[i];
        
        arr[arr.length-2] = 0;
        arr[arr.length-1] = distance;
        
        Arrays.sort(arr); //이분 탐색을 위해 정렬
        
        binarySearch(1,distance,n);
        
        return result;

    }
}