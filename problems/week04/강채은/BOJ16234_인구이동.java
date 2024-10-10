import java.util.*;
import java.io.*;

public class BOJ16234_인구이동 {

   private static int [][] country;
   
   private static boolean [][] visited;
   private static Deque<Pos> open;
   private static boolean check;
   
   private static int cnt, person;
   private static int n,l,r; 
   
   private static int [] dx = {-1,1,0,0};
   private static int [] dy = {0,0,-1,1};
   
   static class Pos{
      int x;
      int y;
      
      public Pos(int x, int y) {
         this.x = x;
         this.y = y;
      }
   }
   
   private static void dfs(Pos pos) {
      
      visited[pos.x][pos.y] = true;
      
      for(int i = 0; i<4; i++) {
         
         int mvX = pos.x + dx[i];
         int mvY = pos.y + dy[i];
         
         if(mvX>=0 && mvY >=0 && mvX<n && mvY<n && !visited[mvX][mvY]) {
            
            int minus = Math.abs(country[mvX][mvY]-country[pos.x][pos.y]);
            
            //연합국가 확인 
            if(minus >= l && minus <=r) {
               check = true;
               cnt ++;
               person += country[mvX][mvY];
               open.add(new Pos(mvX,mvY));
               dfs(new Pos(mvX,mvY));
            }
            
         }
      }
      
   }
   
   public static void main(String[] args) throws Exception {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      n = Integer.parseInt(st.nextToken());
      l = Integer.parseInt(st.nextToken());
      r = Integer.parseInt(st.nextToken());
      
      country = new int[n][n];
      
      for(int i = 0; i<n; i++) {
         st = new StringTokenizer(br.readLine());
         for(int j = 0 ;j<n; j++) {
            country[i][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      int mv = 0;
      boolean total = true;
      
      while(total) {
         
         visited = new boolean[n][n];
         
         total = false;
         
         for (int i = 0; i<n; i++) {
            for(int j = 0 ; j<n; j++) {
               if(!visited[i][j]) {
                  
                  check = false;
                  open = new ArrayDeque<>();
                  cnt = 0;
                  person = 0; 
                  
                  dfs(new Pos(i,j));
                  
                  total = total | check ;
                  
                  if(check) {
                     
                     person += country[i][j];
                     int mvP = person/(cnt+1);
                     open.add(new Pos(i,j));
                     
                     for(Pos pos : open) {
                        country[pos.x][pos.y] = mvP;
                     }
                     
                  }
                  
               }
            }
         }
         
         if(!total) break;
         
         mv ++;
         
      }
      
      System.out.println(mv);
      

   }

}
