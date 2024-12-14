import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 14725 개미굴
 * - 92 ms
 * - 트라이, DFS
 * - 각 방을 노드로 각 StringTokenizer 를 트라이에 삽입
 * - TreeMap 으로 정렬 유지
 * - 트라이의 모든 노드를 DFS 출력하면서
 * - depth 만큼 "--" 추가
 * */
public class BOJ14725_개미굴 {
    private static final int MAX_K = 15;
    private static final char HYPHEN = '-';
    private static final char LINE_BREAK = '\n';
    private static final char[][] INDENTS = initIndent();

    private static final class Trie {
        private TreeMap<String, Trie> map;

        Trie() {
            map = new TreeMap<>();
        }

        final void insert(StringTokenizer st) {
            Trie curr;
            Trie next;
            String str;

            curr = this;
            while (st.hasMoreTokens()) { // StringTokenizer 를 트라이에 삽입
                if ((next = curr.map.get(str = st.nextToken())) == null) {
                    curr.map.put(str, next = new Trie());
                }
                curr = next;
            }
        }

        final void print(int level) { // depth 만큼 "--" 추가하여 출력
            for (Entry<String, Trie> entry : map.entrySet()) {
                sb.append(INDENTS[level]).append(entry.getKey()).append(LINE_BREAK);
                entry.getValue().print(level + 1);
            }
        }
    }

    private static StringBuilder sb;

    private static final char[][] initIndent() {
        int i;
        char[][] indents;

        indents = new char[MAX_K + 1][]; // depth 별 indent 생성
        indents[MAX_K] = new char[MAX_K << 1];
        for (i = 0; i < MAX_K; i++) { // 가장 깊은 indent
            indents[MAX_K][i << 1] = HYPHEN;
            indents[MAX_K][i << 1 | 1] = HYPHEN;
        }
        for (i = 0; i < MAX_K; i++) { // 나머지 indent
            indents[i] = new char[i << 1];
            System.arraycopy(indents[MAX_K], 0, indents[i], 0, i << 1);
        }
        return indents;
    }

    public static void main(String[] args) throws IOException {
        int n;
        Trie trie;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        trie = new Trie();
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            st.nextToken(); // 숫자 무시
            trie.insert(st); // 각 StringTokenizer 를 트라이에 삽입
        }
        sb = new StringBuilder();
        trie.print(0); // 트라이의 노드들 DFS 출력
        System.out.print(sb.toString());
    }
}
