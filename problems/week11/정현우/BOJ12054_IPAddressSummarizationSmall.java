import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 12054 IP Address Summarization (Small)
 * - 76 ms
 * - 트라이, DFS
 * - IP 주소 32 비트 정수로 전환
 * - 앞에서부터 P 개의 비트만 트라이에 삽입
 * - P 개 이후 끝 표시
 * - 0, 1 두 자식 노드가 모두 끝이면 현재 노드도 끝 표시
 * - 트라이에 들어있는 비트들 주소로 변환하여 출력
 * - 하나의 트라이에서 테스트케이스 별로 노드 관리
 * */
public class BOJ12054_IPAddressSummarizationSmall {
    private static final int START = 1 << 31;
    private static final int MOD = (1 << 8) - 1;
    private static final char DOT = '.';
    private static final char SLASH = '/';
    private static final char LINE_BREAK = '\n';
    private static final char[] PREFIX = "Case #".toCharArray();
    private static final char[] SUFFIX = {':', LINE_BREAK};
    private static final String DELIM = "./";

    private static final class Trie {
        private int time;
        private int output;
        private Trie zero;
        private Trie one;

        final void insert(int addr, int bit, int end) {
            this.time = testCase; // 노드 시간 표시
            if (bit == end) {
                output = testCase; // P 개 이후 끝 표시
                return;
            }
            if ((addr & bit) == 0) { // 비트 0
                if (zero == null) {
                    zero = new Trie();
                }
                zero.insert(addr, bit >>> 1, end); // 다음 비트
            } else { // 비트 1
                if (one == null) {
                    one = new Trie();
                }
                one.insert(addr, bit >>> 1, end); // 다음 비트
            }
            if (zero != null && one != null && zero.output == testCase && one.output == testCase) {
                output = testCase; // 0, 1 두 자식 노드가 모두 끝이면 현재 노드도 끝 표시
            }
        }

        final void print(int num, int depth) {
            if (output == testCase) { // 끝 노드
                num <<= 32 - depth; // 32 비트 마저 0 으로 채우기
                sb.append((num >>> 24) & MOD).append(DOT)
                        .append((num >>> 16) & MOD).append(DOT)
                        .append((num >>> 8) & MOD).append(DOT)
                        .append(num & MOD).append(SLASH)
                        .append(depth).append(LINE_BREAK); // 32 비트 정수를 주소로 변환
                return;
            }
            depth++;
            if (zero != null && zero.time == testCase) {
                zero.print(num << 1, depth); // 0 추가
            }
            if (one != null && one.time == testCase) {
                one.print(num << 1 | 1, depth); // 1 추가
            }
        }
    }

    private static int testCase;
    private static Trie trie;
    private static StringBuilder sb;
    private static BufferedReader br;

    private static final void solution() throws IOException {
        int n;
        int p;
        int addr;
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            addr = Integer.parseInt(st.nextToken()) << 24
                    | Integer.parseInt(st.nextToken()) << 16
                    | Integer.parseInt(st.nextToken()) << 8
                    | Integer.parseInt(st.nextToken()); // IP 주소 32 비트 정수로 전환
            p = Integer.parseInt(st.nextToken());
            trie.insert(addr, START, START >>> p); // 트라이에 삽입
        }
        trie.print(0, 0); // 트라이 출력
    }

    public static void main(String[] args) throws IOException {
        int t;

        trie = new Trie();
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for (testCase = 1; testCase <= t; testCase++) {
            sb.append(PREFIX).append(testCase).append(SUFFIX);
            solution();
        }
        System.out.print(sb.toString());
    }
}
