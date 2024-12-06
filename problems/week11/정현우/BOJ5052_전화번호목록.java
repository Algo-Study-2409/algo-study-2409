import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 정현우 : BOJ 5052 전화번호 목록
 * - 264 ms
 * - 트라이
 * - 전화번호를 트라이에 삽입하면서 확인
 * - 이미 존재하는 번호의 끝에 도달하면 일관성 없음
 * - 삽입하는 전화전호의 끝이 다른 번호에 포함되면 일관성 없음
 * - 하나의 트라이에서 테스트케이스 별로 노드 관리
 * */
public class BOJ5052_전화번호목록 {
    private static final int DIFF = '0';
    private static final int DIGITS = 10;
    private static final char[] YES = {'Y', 'E', 'S', '\n'};
    private static final char[] NO = {'N', 'O', '\n'};

    private static int testCase;
    private static Trie trie;
    private static BufferedReader br;

    private static final class Trie {
        int time;
        int output;
        Trie[] next;

        Trie() {
            next = new Trie[DIGITS];
        }

        boolean contains(char[] str) {
            int i;
            int idx;
            int len;
            Trie curr;

            len = str.length;
            curr = this;
            for (i = 0; i < len; i++) {
                if (curr.output == testCase) { // 이미 존재하는 번호의 끝에 도달
                    return true;
                }
                curr.time = testCase; // 노드 시간 표시
                idx = str[i] - DIFF;
                if (curr.next[idx] == null) {
                    curr.next[idx] = new Trie();
                }
                curr = curr.next[idx];
            }
            if (curr.time == testCase) { // 삽입하는 전화전호의 끝이 다른 번호에 포함
                return true;
            }
            curr.time = testCase; // 노드 시간 표시
            curr.output = testCase; // 전화번호 끝 표시
            return false;
        }
    }

    private static final boolean solution() throws IOException {
        int n;

        n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            if (trie.contains(br.readLine().toCharArray())) { // 포함 확인
                while (n-- > 0) { // 나머지 입력 건너뛰기
                    br.readLine();
                }
                return false; // 일관성 없음
            }
        }
        return true; // 일관성 있음
    }

    public static void main(String[] args) throws IOException {
        int t;
        StringBuilder sb;

        trie = new Trie();
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for (testCase = 1; testCase <= t; testCase++) {
            sb.append(solution() ? YES : NO);
        }
        System.out.print(sb.toString());
    }
}
