import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 26086 어려운 스케줄링
 * - 172 ms
 * - 오프라인 쿼리
 * - 마지막 정렬 전까지는 추가할 고유번호만 표시
 * - 표시된 고유번호들을 작은 것부터 덱에 추가
 * - 마지막 정렬 이후에는 뒤집기에 따라
 * -   현재 스케줄러가 정방향인지 역방향인지 저장
 * - 정방향이면 업무를 덱 앞에, 역방향이면 뒤에 추가
 * - 마지막 방향에 따라 앞 혹은 뒤에서 k번째 출력
 * */
public class BOJ26086_어려운스케줄링 {
    private static final int QUERY0 = '0';
    private static final int QUERY1 = '1';
    private static final int QUERY2 = '2';
    private static final int REVERSE = -1;

    public static void main(String[] args) throws IOException {
        int n;
        int q;
        int k;
        int i;
        int lastSort;
        int[] queries;
        boolean forward;
        boolean[] exists;
        ArrayDeque<Integer> dq;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        lastSort = -1;
        queries = new int[q];
        for (i = 0; i < q; i++) {
            switch (br.read()) {
                case QUERY0:
                    br.read();
                    queries[i] = Integer.parseInt(br.readLine()); // 추가할 고유번호
                    break;
                case QUERY1:
                    lastSort = i; // 마지막 정렬 위치
                    br.read();
                    break;
                case QUERY2:
                    queries[i] = REVERSE; // 뒤집기
                    br.read();
                    break;
            }
        }
        exists = new boolean[n + 1];
        for (i = 0; i < lastSort; i++) { // 마지막 정렬 전까지
            if (queries[i] > 0) {
                exists[queries[i]] = true; // 추가할 고유번호 표시
            }
        }
        dq = new ArrayDeque<>(q);
        if (lastSort > 0) { // 정렬 쿼리 존재
            for (i = 1; i <= n; i++) { // 작은 것부터
                if (exists[i]) { // 표시된 고유번호
                    dq.addLast(i); // 덱에 추가
                }
            }
        }
        forward = true; // 스케줄러 방향 표시
        for (i = lastSort + 1; i < q; i++) { // 마지막 정렬 이후
            if (queries[i] == REVERSE) { // 뒤집기
                forward ^= true; // 방향 전환
            } else if (forward) { // 정방향이면
                dq.addFirst(queries[i]); // 덱의 앞에 추가
            } else { // 역방향이면
                dq.addLast(queries[i]); // 덱의 뒤에 추가
            }
        }
        if (forward) { // 마지막 방향이 정방향이면
            for (; --k > 0; dq.pollFirst()); // 앞에서 k번째 업무 출력
            System.out.print(dq.peekFirst());
        } else { // 마지막 방향이 역방향이면
            for (; --k > 0; dq.pollLast()); // 뒤에서 k번째 업무 출력
            System.out.print(dq.peekLast());
        }
    }
}
