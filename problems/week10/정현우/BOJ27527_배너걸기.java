import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 27527 배너 걸기
 * - 288 ms
 * - 큐
 * - 각 숫자별 현재 큐에 존재하는 개수 카운트
 * - 큐에 새로운 숫자를 넣고 처음 숫자를 빼면서
 * - 큐 크기 M 으로 유지
 * - 특정 숫자가 ⌈9 * m / 10⌉ 개에 도달하면
 * - 배너 걸기 가능
 * */
public class BOJ27527_배너걸기 {
    private static final int SIZE = 1_000_001;
    private static final char[] YES = {'Y', 'E', 'S'};
    private static final char[] NO = {'N', 'O'};

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int num;
        int thr;
        int[] cnt;
        ArrayDeque<Integer> q;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        thr = (9 * (m + 1)) / 10; // ⌈9 * m / 10⌉
        q = new ArrayDeque<>(m);
        cnt = new int[SIZE]; // 각 숫자별 현재 큐에 존재하는 개수 카운트
        st = new StringTokenizer(br.readLine(), " ", false);
        for (i = 1; i < m; i++) { // 첫 M - 1 개 숫자 삽입
            num = Integer.parseInt(st.nextToken());
            if (++cnt[num] == thr) { // 특정 숫자가 ⌈9 * m / 10⌉ 개에 도달하면
                System.out.print(YES); // 배너 걸기 가능
                return;
            }
            q.addLast(num);
        }
        for (--i; i < n; i++) {
            num = Integer.parseInt(st.nextToken());
            if (++cnt[num] == thr) { // 특정 숫자가 ⌈9 * m / 10⌉ 개에 도달하면
                System.out.print(YES); // 배너 걸기 가능
                return;
            }
            q.addLast(num); // 큐에 삽입하면 큐 크기 M
            cnt[q.pollFirst()]--; // 처음 숫자를 빼기
        }
        System.out.print(NO); // 배너 걸기 불가능
    }
}
