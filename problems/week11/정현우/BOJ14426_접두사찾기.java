import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 14426 접두사 찾기
 * - 292 ms
 * - 트라이
 * - N 개 문자열 트라이에 삽입
 * - M 개 문자열 트라이에서 탐색
 * - 트라이에 존재하는 개수 출력
 * */
public class BOJ14426_접두사찾기 {
    private static final int DIFF = 'a';
    private static final int ALPHABET_SIZE = 26;

    private static final class Trie {
        Trie[] next;

        Trie() {
            next = new Trie[ALPHABET_SIZE];
        }

        final void insert(char[] word) {
            int idx;
            Trie curr;

            curr = this;
            for (char ch : word) {
                idx = ch - DIFF;
                if (curr.next[idx] == null) {
                    curr.next[idx] = new Trie();
                }
                curr = curr.next[idx];
            }
        }

        final boolean search(char[] word) {
            int idx;
            Trie curr;

            curr = this;
            for (char ch : word) {
                idx = ch - DIFF;
                if (curr.next[idx] == null) { // 트라이에 존재하지 않음
                    return false;
                }
                curr = curr.next[idx];
            }
            return true; // 트라이에 존재
        }
    }

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int cnt;
        Trie trie;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        trie = new Trie();
        while (n-- > 0) { // N 개 문자열 트라이에 삽입
            trie.insert(br.readLine().toCharArray());
        }
        cnt = 0;
        while (m-- > 0) { // M 개 문자열 트라이에서 탐색
            if (trie.search(br.readLine().toCharArray())) {
                cnt++; // 트라이에 존재하는 개수 카운트
            }
        }
        System.out.print(cnt);
    }
}
