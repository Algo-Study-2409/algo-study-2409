import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 19535 ㄷㄷㄷㅈ
 * - 396 ms
 * - 조합
 * - 'ㄷ' : 모든 간선 (u, v) 에 대하여
 * -   간선이 'ㄷ'의 두 번째 간선인 경우의 수 합
 * -     = (u 차수 - 1) * (v 차수 - 1) 합
 * - 'ㅈ' : 모든 정점에 대하여
 * -   정점의 인접 정점 중 3 개를 고르는 경우의 수 합
 * -     = (차수)C3
 * -     = (차수) * (차수 - 1) * (차수 - 2) / 3!
 * */
public class BOJ19535_ㄷㄷㄷㅈ {
    private static final char D = 'D';
    private static final char G = 'G';
    private static final char[] DUDUDUNGA = {D, 'U', D, 'U', D, 'U', 'N', G, 'A'};

    public static void main(String[] args) throws IOException {
        int n;
        int[] degree;
        int[][] edges;
        long du;
        long ga;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        degree = new int[n + 1]; // 차수
        edges = new int[n - 1][2]; // 간선
        for (int[] edge : edges) { // 간선 입력
            st = new StringTokenizer(br.readLine(), " ", false);
            degree[edge[0] = Integer.parseInt(st.nextToken())]++; // 차수
            degree[edge[1] = Integer.parseInt(st.nextToken())]++; // 차수
        }
        du = ga = 0L;
        for (; n > 0; n--) { // 모든 정점에 대하여
            if (degree[n] > 2) { // 정점의 인접 정점 중 3 개를 고르는 경우의 수 합
                ga += (long) (degree[n]) * (degree[n] - 1) * (degree[n] - 2) / 6L; // (차수)C3
            } // (차수) * (차수 - 1) * (차수 - 2) / 3!
            degree[n]--; // 'ㄷ' 계산을 위해 미리 차수 - 1
        }
        for (int[] edge : edges) { // 모든 간선 (u, v) 에 대하여
            du += (long) degree[edge[0]] * degree[edge[1]]; // (u 차수 - 1) * (v 차수 - 1)
        } // 간선이 'ㄷ'의 두 번째 간선인 경우의 수 합
        if (du > 3 * ga) { // 'ㄷ'이 'ㅈ'의 3 배보다 많은 트리
            System.out.print(D); // D-트리
        } else if (du < 3 * ga) { // 'ㄷ'이 'ㅈ'의 3 배보다 적은 트리
            System.out.print(G); // G-트리
        } else { // 'ㄷ'이 'ㅈ'의 정확히 3 배만큼 있는 트리
            System.out.print(DUDUDUNGA); // DUDUDUNGA-트리
        }
    }
}
