import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 정현우 : BOJ 1283 단축키 지정
 * - 64 ms
 * - 문자열
 * - 첫 글자가 지정이 안 되어 있으면 단축키 지정
 * - 공백 뒤 글자가 지정이 안 되어 있으면 단축키 지정
 * - 공백이 아닌 글자가 지정이 안 되어 있으면 단축키 지정
 * - 해당 사항 없으면 그대로 출력
 * */
public class BOJ1283_단축키지정 {
    private static final int SIZE = 57;
    private static final int ALPHA = 26;
    private static final char LOWER = 'a';
    private static final char UPPER = 'A';
    private static final char OPEN = '[';
    private static final char CLOSE = ']';
    private static final char SPACE = ' ';
    private static final char LINE_BREAK = '\n';

    private static int len;
    private static char[] str;
    private static char[] result;
    private static boolean[] visited;
    private static StringBuilder sb;

    private static final boolean isVisited(char ch) {
        if (ch < LOWER) {
            ch -= UPPER;
        } else {
            ch -= LOWER;
        }
        if (visited[ch]) {
            return true;
        } else {
            visited[ch] = true;
            return false;
        }
    }

    private static final void prefix(int idx) {
        System.arraycopy(str, 0, result, 0, idx);
        result[idx] = OPEN;
        result[idx + 1] = str[idx];
        result[idx + 2] = CLOSE;
        System.arraycopy(str, idx + 1, result, idx + 3, len - 1 - idx);
        result[len + 2] = LINE_BREAK;
        sb.append(result, 0, len + 3);
    }

    public static void main(String[] args) throws IOException {
        int n;
        int i;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[ALPHA];
        result = new char[SIZE];
        sb = new StringBuilder();
        loop:
        while (n-- > 0) {
            str = br.readLine().toCharArray();
            len = str.length;
            if (!isVisited(str[0])) { // 첫 글자가 지정이 안 되어 있으면
                prefix(0); // 단축키 지정
                continue;
            }
            for (i = 1; i < len; i++) { // 공백 뒤 글자가 지정이 안 되어 있으면
                if (str[i] == SPACE && !isVisited(str[i + 1])) {
                    prefix(i + 1); // 단축키 지정
                    continue loop;
                }
            }
            for (i = 0; i < len; i++) { // 공백이 아닌 글자가 지정이 안 되어 있으면
                if (str[i] != SPACE && !isVisited(str[i])) {
                    prefix(i); // 단축키 지정
                    continue loop;
                }
            }
            sb.append(str).append(LINE_BREAK); // 해당 사항 없으면 그대로 출력
        }
        System.out.print(sb.toString());
    }
}
