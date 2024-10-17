import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 16235 나무 재테크
 * - 288 ms
 * - 시뮬레이션
 * - map 1 차원 변환
 * - 나무 정렬된 Linked List로 관리
 * - 봄
 * -   나무 리스트를 순회하면서
 * -   양분을 먹고 나이를 증가시키거나
 * -   리스트에서 삭제 후 추가할 양분 저장
 * -   번식할 나무 큐에 저장
 * - 여름, 겨울
 * -   각 칸에 죽은 나무와 S2D2 양분 추가
 * - 가을
 * -   큐에 있는 나무들 8방 번식
 * -   나이가 가장 적으므로 리스트 앞에 삽입
 * - 나무 삭제 시 휴지통에 넣어 저장하고
 * - 나무 생성 시 꺼내서 활용
 * */
public class BOJ16235_나무재테크 {
    private static final int WALL = -1;
    private static final int INITIAL_NUTRIENT = 5;
    private static final Tree NIL = new Tree();

    private static final class Tree implements Comparable<Tree> {
        private static ArrayDeque<Tree> bin = new ArrayDeque<>();

        int pos;
        int age;
        Tree next;

        Tree() {
        }

        Tree(int pos, int age) {
            this.pos = pos;
            this.age = age;
        }

        Tree(int pos, Tree next) {
            this.pos = pos;
            this.age = 1;
            this.next = next;
        }

        private Tree setTree(int pos, Tree next) {
            this.pos = pos;
            this.age = 1;
            this.next = next;
            return this;
        }

        static Tree getTree(int pos, Tree next) {
            if (bin.isEmpty()) { // 휴지통이 비어있으면
                return new Tree(pos, next); // 새 객체 생성
            }
            return bin.pollFirst().setTree(pos, next); // 휴지통에서 꺼내 세팅하여 활용
        }

        void remove() {
            bin.addFirst(this); // 나무 삭제 시 휴지통에 넣어 저장
        }

        @Override
        public int compareTo(Tree o) {
            return age - o.age;
        }
    }

    private static int n;
    private static int col;
    private static int thr;
    private static int[] d;
    private static int[] humus;
    private static int[] nutrient;
    private static int[] increment;
    private static Tree trees;
    private static ArrayDeque<Integer> q;

    private static void spring() {
        Tree prev;
        Tree curr;

        prev = trees;
        for (curr = prev.next; curr != null; curr = prev.next) { // 나무 리스트 순회
            if (nutrient[curr.pos] < curr.age) { // 양분이 부족하면
                prev.next = curr.next; // 나무 리스트에서 삭제
                humus[curr.pos] += curr.age >> 1; // 죽은 나무의 양분
                curr.remove(); // 나무 버리기
            } else { // 양분이 충분하면
                nutrient[curr.pos] -= curr.age; // 양분 흡수
                if (++curr.age % INITIAL_NUTRIENT == 0) { // 나이가 5의 배수
                    q.addLast(curr.pos); // 번식할 큐에 저장
                }
                prev = curr;
            }
        }
    }

    private static final void summerWinter() {
        int i;
        int pos;

        for (i = col; i < thr; i += col) {
            for (pos = i + 1; pos <= i + n; pos++) {
                nutrient[pos] += humus[pos] + increment[pos]; // 죽은 나무와 S2D2 양분 추가
                humus[pos] = 0;
            }
        }
    }

    private static final void fall() {
        int i;
        int pos;
        int npos;

        while (!q.isEmpty()) { // 번식 큐
            pos = q.pollFirst();
            for (i = 0; i < 8; i++) { // 8방
                npos = pos + d[i];
                if (nutrient[npos] != WALL) {
                    trees.next = Tree.getTree(npos, trees.next); // 나무 생성
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int m;
        int k;
        int i;
        int j;
        int cnt;
        Tree tree;
        Tree[] initialTrees;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        col = n + 2;
        nutrient = new int[col * col]; // map 1 차원 변환
        increment = new int[col * col];
        thr = (n + 1) * col;
        for (i = 0; i < col; i++) {
            nutrient[i] = WALL; // 위쪽 벽
        }
        for (i = col; i < thr; i += col) {
            nutrient[i] = WALL; // 왼쪽 벽
            st = new StringTokenizer(br.readLine(), " ", false);
            for (j = 1; j <= n; j++) {
                nutrient[i + j] = INITIAL_NUTRIENT; // 초기 양분
                increment[i + j] = Integer.parseInt(st.nextToken()); // S2D2 양분
            }
            nutrient[i + n + 1] = WALL; // 오른쪽 벽
        }
        System.arraycopy(nutrient, 0, nutrient, thr, col); // 아래쪽 벽
        initialTrees = new Tree[m];
        for (i = 0; i < m; i++) { // 초기 나무들 입력
            st = new StringTokenizer(br.readLine(), " ", false);
            initialTrees[i] = new Tree(Integer.parseInt(st.nextToken()) * col + Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(initialTrees); // 나이 오름차순 정렬
        for (i = 1; i < m; i++) { // Linked List로 만듦
            initialTrees[i - 1].next = initialTrees[i];
        }
        (trees = NIL).next = initialTrees[0]; // 첫 노드 NIL 설정
        humus = new int[col * col]; // 죽은 나무의 양분
        d = new int[] {-col, -col + 1, 1, col + 1, col, col - 1, -1, -col - 1}; // 8방 탐색
        q = new ArrayDeque<>(); // 번식할 나무들
        while (k-- > 0) {
            spring(); // 봄
            summerWinter(); // 여름, 겨울
            fall(); // 가을
        }
        cnt = 0;
        for (tree = trees.next; tree != null; tree = tree.next) {
            cnt++; // 나무 리스트 크기 카운트
        }
        System.out.print(cnt);
    }
}