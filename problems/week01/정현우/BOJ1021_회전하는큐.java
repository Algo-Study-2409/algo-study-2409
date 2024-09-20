import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1021 회전하는 큐
 * - 64 ms
 * - 큐
 * - 큐에 1 ~ N 숫자 삽입
 * - 회전 = 큐 앞에서 숫자를 뽑고 뒤에 삽입
 * - 원하는 숫자가 나올 때까지 회전
 * - 회전 수와 역방향 회전했을 때의 회전 수를 비교
 * - 역방향 회전 수 = (큐 사이즈) - (회전 수)
 * - 더 작은 회전 수 누적
 * */
public class BOJ1021_회전하는큐 {
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int num;
		int idx;
		int cnt;
		int moves;
		BufferedReader br;
		StringTokenizer st;
		ArrayDeque<Integer> q;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		q = new ArrayDeque<>(n);
		for (i = 1; i <= n; i++) { // 큐에 1 ~ N 숫자 삽입
			q.addLast(i);
		}
		cnt = 0;
		st = new StringTokenizer(br.readLine());
		while (m-- > 0) {
			idx = Integer.parseInt(st.nextToken());
			moves = 0;
			while ((num = q.pollFirst()) != idx) { // 원하는 숫자가 나올 때까지 회전
				q.addLast(num);
				moves++;
			}
			if (moves < n - moves) { // 회전 수와 역방향 회전했을 때의 회전 수를 비교
				cnt += moves; // 더 작은 회전 수 누적
			} else {
				cnt += n - moves;
			}
			n--; // 큐 사이즈 감소
		}
		System.out.print(cnt);
	}
}
