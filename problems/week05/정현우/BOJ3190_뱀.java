import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 3190 뱀
 * - 64 ms
 * - 시뮬레이션
 * - map 1 차원 변환
 * - 시간이 지날 때마다
 * -   뱀의 머리 한 칸씩 이동
 * -   빈 칸이면 꼬리도 한 칸 이동
 * -   벽을 만나면 게임 종료
 * -   뱀의 몸통도 벽으로 취급
 * - 현재 시간이 방향을 변환하는 시간이면
 * -   방향 전환 후 다음 변환 정보 저장
 * */
public class BOJ3190_뱀 {
    private static final int WALL = -1;
    private static final int APPLE = 1;
    private static final int EMPTY = 0;
    private static final int LEFT_TURN = 3;
    private static final int RIGHT_TURN = 1;
    private static final int MAX_TIME = 10_100;
    private static final char LEFT = 'L';

    public static void main(String[] args) throws IOException {
        int n;
        int k;
        int x;
        int l;
        int i;
        int dir;
        int max;
        int cnt;
        int pos;
        int col;
        int end;
        int time;
        int head;
        int tail;
        int turn;
        int[] d;
        int[] map;
        int[] snake;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        col = n + 2;
        end = n + 1;
        max = col * col;
        d = new int[] {-col, 1, col, -1};
        map = new int[max]; // map 1 차원 변환
        for (i = 1; i <= n; i++) {
            map[i] = WALL; // 위쪽 벽
            map[end * col + i] = WALL; // 아래쪽 벽
            map[i * col] = WALL; // 왼쪽 벽
            map[i * col + end] = WALL; // 오른쪽 벽
        }
        for (i = 0; i < k; i++) { // 사과 입력
            st = new StringTokenizer(br.readLine(), " ", false);
            map[Integer.parseInt(st.nextToken()) * col + Integer.parseInt(st.nextToken())] = APPLE;
        }
        dir = 1; // 시작 방향 오른쪽
        l = Integer.parseInt(br.readLine()); // 전체 방향 변환 횟수
        cnt = 1; // 현재까지 변환 횟수 카운트
        st = new StringTokenizer(br.readLine(), " ", false);
        x = Integer.parseInt(st.nextToken()); // 첫 변환 시간
        if (st.nextToken().charAt(0) == LEFT) { // 첫 변환 방향
            turn = LEFT_TURN;
        } else {
            turn = RIGHT_TURN;
        }
        snake = new int[MAX_TIME]; // 뱀 좌표 정보
        head = 0; // 머리 인덱스
        tail = 0; // 꼬리 인덱스
        pos = col + 1; // (1, 1)에서 시작
        snake[head] = pos; // 머리와 꼬리 (1, 1)
        for (time = 1;; time++) { // 시간이 지날 때마다
            pos += d[dir]; // 다음 좌표
            switch (map[pos]) {
                case WALL: // 벽을 만나면
                    System.out.print(time);
                    return; // 게임 종료
                case EMPTY: // 빈칸이면
                    map[snake[tail++]] = EMPTY; // 꼬리 이동
                case APPLE: // 빈칸 혹은 사과
                    map[pos] = WALL; // 뱀의 몸통도 벽으로 취급
                    snake[++head] = pos; // 머리 이동
            }
            if (time == x) { // 현재 시간이 방향을 변환하는 시간이면
                dir = dir + turn & 3; // 방향 변환
                if (cnt++ == l) { // 모든 변환 수행 완료
                    x = MAX_TIME; // 다음 변환 없음
                } else { // 다음 변환 정보 저장
                    st = new StringTokenizer(br.readLine(), " ", false);
                    x = Integer.parseInt(st.nextToken()); // 다음 변환 시간
                    if (st.nextToken().charAt(0) == LEFT) { // 다음 변환 방향
                        turn = LEFT_TURN;
                    } else {
                        turn = RIGHT_TURN;
                    }
                }
            }
        }
    }
}
