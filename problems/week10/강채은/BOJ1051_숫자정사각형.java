import java.util.*;
import java.io.*;


public class BOJ1051_숫자정사각형 {

    private static int N;
    private static int M;

    private static int result = Integer.MIN_VALUE;

    private static int [][] square;

    public static void main(String[] args) throws Exception {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        square = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                square[i][j] = str.charAt(j) - '0';
            }
        }

        int max = Math.max(N, M);

        for(int i = 0; i<N; i++){
           for(int j = 0; j<M; j++){
                for(int k = max; k>=1; k--){

                    if(k <= result) break;
                    
                    int mvX = i + (k-1);
                    int mvY = j + (k-1);
        
                    if(mvX < i || mvY < j || mvX >= N || mvY >= M) continue;
                    if(square[i][j] == square[mvX][j] && square[i][j] == square[i][mvY] && square[i][j] == square[mvX][mvY]) {
                        result = Math.max(result,k);
                        break;
                    }
        
                }
           }
        }

        System.out.println(result*result);

    }

}