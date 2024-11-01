import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1497 기타콘서트
 * - 68 ms
 * - 비트마스킹
 * - 기타 : 'Y' 위치에 1, 'N' 위치에 0 비트를 가진 long
 * - 길이 N 인 guitars 배열에 기타들 각각 저장
 * - max : 모든 기타 OR 연산한 결과
 * - max 가 0 이면 -1 출력
 * - 1 부터 (1 << N) - 1 까지 비트마스킹 부분집합
 * - 1 비트 위치의 기타들 OR 연산
 * - 계산 값이 max 이면 가장 적은 기타 수인지 확인하여 갱신
 * */
public class BOJ1497_기타콘서트 {
	private static final int Y = 'Y';
	private static final int SPACE = ' ';
	private static final char[] FAIL = {'-', '1'};

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int j;
		int idx;
		int cnt;
		int min;
		long thr;
		long max;
		long sum;
		long guitar;
		long[] guitars;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		guitars = new long[n];
		max = 0;
		for (i = 0; i < n; i++) {
			while (br.read() != SPACE);
			guitar = 0L;
			for (j = 0; j < m; j++) { // 기타 : 'Y' 위치에 1, 'N' 위치에 0 비트를 가진 long
				guitar <<= 1;
				if (br.read() == Y) {
					guitar |= 1L;
				}
			}
			br.read();
			guitars[i] = guitar; // 길이 N 인 guitars 배열에 기타들 각각 저장
			max |= guitar; // max : 모든 기타 OR 연산한 결과
		}
		if (max == 0L) { // max 가 0 이면
			System.out.print(FAIL); // -1 출력
			return;
		}
		min = n;
		thr = (1 << n) - 1;
		n--; // N 을 마지막 기타 인덱스로 조정
		for (i = 1; i <= thr; i++) { // 1 부터 (1 << N) - 1 까지 비트마스킹 부분집합
			sum = 0L;
			cnt = 0;
			for (j = i, idx = n; j > 0; j >>= 1, idx--) { // 각 비트 확인
				if ((j & 1) == 1) { // 1 비트 위치
					sum |= guitars[idx]; // 기타 OR 연산
					cnt++; // 사용한 기타 개수
				}
			}
			if (sum == max && cnt < min) { // 계산 값이 max 이면 가장 적은 기타 수인지 확인하여 갱신
				min = cnt;
			}
		}
		System.out.print(min);
	}
}
