import java.io.*;
import java.util.*;

public class BOJ16235_나무재태크 {
    
    private static int N,M,K;

    private static int [][] ground; //상도의 땅 
    private static int [][] manure; //양분 정보 
    private static Deque <Tree> treeInfo; //나무 정보 
    
    private static int [] dx = {0,0,-1,1,1,1,-1,-1};
    private static int [] dy = {-1,1,0,0,1,-1,1,-1};

    static class Tree implements Comparable<Tree>{

        int x;
        int y;
        int age;

        public Tree(int x, int y, int age){
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree t){
            return this.age - t.age;
        }
        
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        ground = new int [N+1][N+1];
        manure = new int [N+1][N+1];
        treeInfo = new ArrayDeque<>();

        //양분 정보
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<N+1; j++){
                ground[i][j] = 5; //초기 땅은 모두 5만큼의 양분을 가지고 있음 
                manure[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //나무 정보
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            treeInfo.add(new Tree(x, y, age));

        }

        while(K -- >0){

            Deque<Tree> dead = spring();
            summer(dead);
            autumn();
            winter();

        }
        
        System.out.println(treeInfo.size());

    }

    private static Deque<Tree> spring(){

        Deque <Tree> dead = new ArrayDeque<>();

        for(int i = 0; i<treeInfo.size();){
            Tree tree = treeInfo.poll();
            if(ground[tree.x][tree.y] < tree.age) {
                dead.add(tree);
                continue;
            }
            ground[tree.x][tree.y] -= tree.age;
            treeInfo.add(tree);
            tree.age ++ ;
            i++;
        }

        return dead;
    }

    private static void summer(Deque<Tree> dead){

        for(Tree tree : dead){
            ground[tree.x][tree.y] += tree.age/2; 
        }

    }

    private static void autumn(){
       
        Deque <Tree> prop = new ArrayDeque<>();

        for(Tree tree : treeInfo){
            if(tree.age % 5 == 0) prop.add(tree);
        }

        while(!prop.isEmpty()){

            Tree tree = prop.poll();

            for(int i = 0; i<8; i++){

                int mvX = tree.x + dx[i];
                int mvY = tree.y + dy[i];

                if(mvX <= 0 || mvY <= 0 | mvX > N || mvY > N) continue;

                treeInfo.addFirst(new Tree(mvX, mvY, 1));

            }

        }
    }

    private static void winter(){

        for(int i = 1; i<N+1; i++){
            for(int j = 1; j<N+1; j++){
                ground[i][j] += manure[i][j];
            }
        }

    }
}
