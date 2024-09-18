import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 정현우 : BOJ 3896 소수 사이 수열
 * - 84 ms
 * - 에라토스테네스의 체 + 이분 탐색
 * - primes 배열에 100,000개의 소수 저장
 * - k가 소수일 경우 0 출력
 * - k가 소수가 아닐 경우
 * - primes에서 k를 넘는 최소 소수 위치 이분 탐색
 * - 소수 사이 수열 길이 = (해당 소수) - (한 칸 이전 소수)
 * */
public class BOJ3896_소수사이수열 {
	private static final int FAIL = 0;
	private static final int MAX = 1_299_710;
	private static final int SIZE = 100_000;
	private static final char LINE_BREAK = '\n';

	private static int[] primes;
	private static boolean[] notPrime;
	private static BufferedReader br;

	private static void initPrimes() {
		int i;
		int j;
		int idx;

		notPrime = new boolean[MAX];
		primes = new int[SIZE]; // primes 배열에 100,000개의 소수 저장
		idx = 0;
		for (i = 2; i < MAX; i++) { // 에라토스테네스의 체
			if (notPrime[i]) {
				continue;
			}
			primes[idx++] = i;
			for (j = i << 1; j < MAX; j += i) {
				notPrime[j] = true;
			}
		}
	}

	private static int solution() throws IOException {
		int k;
		int left;
		int right;
		int mid;

		k = Integer.parseInt(br.readLine());
		if (!notPrime[k]) { // k가 소수일 경우
			return FAIL; // 0 출력
		}
		left = 0;
		right = SIZE;
		while (left < right) { // primes에서 k를 넘는 최소 소수 위치 이분 탐색
			mid = left + right >> 1;
			if (primes[mid] < k) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return primes[right] - primes[right - 1]; // 소수 사이 수열 길이 = (해당 소수) - (한 칸 이전 소수)
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		initPrimes();
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution()).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
