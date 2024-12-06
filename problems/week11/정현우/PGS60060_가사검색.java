/**
 * 정현우 : PGS 60060 가사 검색
 * - 396.52 ms
 * - 트라이
 * - 단어 길이별 정방향 트라이, 역방향 트라이 생성
 * - 트라이 노드별로 하위 단어 개수 저장
 * - '?' 가 뒤쪽에 올 경우 정방향 트라이,
 * - '?' 가 앞쪽에 올 경우 역방향 트라이에서 탐색
 * */
public class PGS60060_가사검색 {
    private static final int FAIL = 0;
    private static final int DIFF = 'a';
    private static final int MAX_WORD_LEN = 10_001;
    private static final int ALPHABET_SIZE = 26;
    private static final char WILD_CARD = '?';

    private static final class Trie {
        int cnt;
        Trie[] next;

        Trie() {
            next = new Trie[ALPHABET_SIZE];
        }

        void insertForward(char[] word, int len) {
            int i;
            int idx;
            Trie curr;

            curr = this;
            for (i = 0; i < len; i++) { // 정방향 삽입
                curr.cnt++; // 하위 단어 개수 + 1
                idx = word[i] - DIFF;
                if (curr.next[idx] == null) {
                    curr.next[idx] = new Trie();
                }
                curr = curr.next[idx];
            }
            curr.cnt++;
        }

        void insertBackward(char[] word, int len) {
            int i;
            int idx;
            Trie curr;

            curr = this;
            for (i = len - 1; i >= 0; i--) { // 역방향 삽입
                curr.cnt++; // 하위 단어 개수 + 1
                idx = word[i] - DIFF;
                if (curr.next[idx] == null) {
                    curr.next[idx] = new Trie();
                }
                curr = curr.next[idx];
            }
            curr.cnt++;
        }

        int searchForward(char[] word, int len) {
            int i;
            int idx;
            Trie curr;

            curr = this;
            for (i = 0; i < len; i++) { // 정방향 탐색
                if (word[i] == WILD_CARD) { // '?' 도달
                    break;
                }
                idx = word[i] - DIFF;
                if (curr.next[idx] == null) { // 일치하는 단어가 없음
                    return FAIL; // 0 반환
                }
                curr = curr.next[idx];
            }
            return curr.cnt;
        }

        int searchBackward(char[] word, int len) {
            int i;
            int idx;
            Trie curr;

            curr = this;
            for (i = len - 1; i >= 0; i--) { // 역방향 탐색
                if (word[i] == WILD_CARD) { // '?' 도달
                    break;
                }
                idx = word[i] - DIFF;
                if (curr.next[idx] == null) { // 일치하는 단어가 없음
                    return FAIL; // 0 반환
                }
                curr = curr.next[idx];
            }
            return curr.cnt;
        }
    }

    public int[] solution(String[] words, String[] queries) {
        int i;
        int len;
        int size;
        int[] ans;
        char[] arr;
        Trie[] forward;
        Trie[] backward;

        forward = new Trie[MAX_WORD_LEN]; // 단어 길이별 정방향 트라이
        backward = new Trie[MAX_WORD_LEN]; // 단어 길이별 역방향 트라이
        for (i = 1; i < MAX_WORD_LEN; i++) {
            forward[i] = new Trie();
            backward[i] = new Trie();
        }
        for (String word : words) {
            arr = word.toCharArray(); // 단어
            len = arr.length; // 단어 길이
            if (len >= MAX_WORD_LEN) { // 검색 키워드 최대 길이를 초과하는 단어
                continue;
            }
            forward[len].insertForward(arr, len); // 정방향 트라이에 삽입
            backward[len].insertBackward(arr, len); // 역방향 트라이에 삽입
        }
        size = queries.length;
        ans = new int[size];
        for (i = 0; i < size; i++) {
            arr = queries[i].toCharArray(); // 검색 키워드
            len = arr.length; // 검색 키워드 길이
            if (arr[0] == WILD_CARD) { // '?' 가 앞쪽에 올 경우
                ans[i] = backward[len].searchBackward(arr, len); // 역방향 트라이에서 탐색
            } else { // '?' 가 뒤쪽에 올 경우
                ans[i] = forward[len].searchForward(arr, len); // 정방향 트라이에서 탐색
            }
        }
        return ans;
    }
}
