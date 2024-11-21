import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 정현우 : BOJ 17281 ⚾
 * - 336 ms
 * - 순열, 비트마스킹
 * - 4번 타자(1번 선수)를 제외하고 순열 돌리면서 점수 계산
 * - [ 3루 - 2루 - 1루 - 홈(타자) ] 주자 정보를
 * -   runners 에 비트 4 개로 저장
 * - 안타, 2루타, 3루타, 홈런 마다 홈으로 들어오는 베이스 위치
 * -   AND 배열에 각각 저장
 * - 0 ~ 15 의 비트카운트 정보 SCORES 배열에 저장
 * - 아웃일 경우
 * -   아웃 카운트 증가
 * -   3 아웃이면 다음 이닝, 아웃 카운트와 주자 정보 초기화
 * - 안타, 2루타, 3루타, 홈런을 칠 경우
 * -   SCORES[runners & AND[(결과)]] 만큼 점수 증가
 * -   주자 정보는 runners << (결과)) | 1(다음 타자) 로 변화
 * */
public class BOJ17281_야구 {
    private static final int OUT = 0;
    private static final int SIZE = 9;
    private static final int LINE = SIZE << 1;
    private static final int DIFF = '0';
    private static final int CLEAN_UP = 3;
    private static final int MAX_OUT_CNT = 3;
    private static final int[] AND = {0, 8, 12, 14, 15}; // 안타, 2루타, 3루타, 홈런 마다 홈으로 들어오는 주자 위치
    private static final int[] SCORES = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4}; // 0 ~ 15 비트카운트

    private static int n;
    private static int max;
    private static int[] batters;
    private static int[][] innings;
    private static boolean[] visited;

    private static final int play(int[] batters) {
        int score;
        int inning;
        int batter;
        int outCnt;
        int result;
        int runners;
        int batterIdx;
        int[] results;

        score = 0;
        inning = 0;
        outCnt = 0;
        runners = 1;
        batterIdx = 0; // 타자 순회
        results = innings[0]; // 첫 이닝 정보
        for(batter = batters[0];; batter = batters[batterIdx = (batterIdx + 1) % SIZE]) {
            if ((result = results[batter]) == OUT) { // 아웃
                if (++outCnt == MAX_OUT_CNT) { // 3 아웃
                    if (++inning == n) { // 모든 이닝 종료
                        break;
                    }
                    outCnt = 0; // 아웃 카운트 초기화
                    runners = 1; // 주자 정보 초기화
                    results = innings[inning]; // 다음 이닝 정보
                }
            } else { // 안타, 2루타, 3루타, 홈런
                score += SCORES[runners & AND[result]]; // 점수 증가
                runners = runners << result | 1; // 주자 정보 업데이트
            }
        }
        return score;
    }

    private static final void permu(int depth) { // 순열
        int i;

        if (depth == SIZE) { // 선수 배정 완료
            max = Math.max(max, play(batters)); // 점수 계산
            return;
        }
        if (depth == CLEAN_UP) { // 4번 타자
            permu(depth + 1); // 건너뛰기
            return;
        }
        for (i = 1; i < SIZE; i++) {
            if (visited[i]) { // 이미 배정한 선수
                continue;
            }
            visited[i] = true; // 방문 처리
            batters[depth] = i; // 선수 배정
            permu(depth + 1); // 다음 순번으로 이동
            visited[i] = false; // 원상 복구
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        int j;
        int[] results;
        char[] input;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        input = new char[LINE];
        innings = new int[n][SIZE];
        for (i = 0; i < n; i++) { // 이닝별 결과 입력
            br.read(input, 0, LINE);
            results = innings[i];
            for (j = 0; j < SIZE; j++) {
                results[j] = input[j << 1] - DIFF;
            }
        }
        max = 0;
        batters = new int[SIZE];
        visited = new boolean[SIZE];
        permu(0); // 순열
        System.out.print(max); // 최대 점수 출력
    }
}
