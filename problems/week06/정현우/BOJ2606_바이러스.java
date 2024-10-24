import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 2606 바이러스
 * - 64 ms
 * - Union-Find
 * - 직접 연결된 컴퓨터들 Union
 * - 집합 크기 roots 에 음수로 저장
 * - 1 번 컴퓨터가 속한 집합의 크기 - 1
 * */
public class BOJ2606_바이러스 {
    private static int[] roots;

    private static final int find(int v) {
        if (roots[v] <= 0) { // Find
            return v;
        }
        return roots[v] = find(roots[v]); // Path Compression
    }

    private static final void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {
            return;
        }
        roots[u] += roots[v]; // 집합 크기 합치기
        roots[v] = u; // Union
    }

    public static void main(String[] args) throws IOException {
        int networks;
        int computers;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        computers = Integer.parseInt(br.readLine()) + 1;
        networks = Integer.parseInt(br.readLine());
        roots = new int[computers]; // 양수 : 부모, 음수 : 집합 크기
        while (--computers > 0) {
            roots[computers] = -1; // 초기 집합 크기 설정
        }
        while (networks-- > 0) { // 직접 연결된 컴퓨터들 Union
            st = new StringTokenizer(br.readLine(), " ", false);
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.print(-roots[find(1)] - 1); // 1 번 컴퓨터가 속한 집합의 크기 - 1
    }
}
