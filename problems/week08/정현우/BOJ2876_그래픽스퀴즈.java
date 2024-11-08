import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 정현우 : BOJ 2876 그래픽스 퀴즈
 * - 120 ms
 * - DP
 * - len : 현재 학생으로 끝나는 연속된 그레이드 길이
 * - 이전 줄에 동일한 그레이드의 학생이 있으면
 * - len = (이전 해당 학생으로 끝나는 연속된 그레이드 길이) + 1
 * - 이전 줄에 동일한 그레이드의 학생이 없으면
 * - len = 1
 * - 최대 길이와 해당 길이에서 최소 그레이드 출력
 * */
public class BOJ2876_그래픽스퀴즈 {
    private static final char SPACE = ' ';

    public static void main(String[] args) throws IOException {
        int n;
        int max;
        int lenLeft;
        int lenRight;
        int prevLenLeft;
        int prevLenRight;
        char grade;
        char left;
        char right;
        char prevLeft;
        char prevRight;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        prevLeft = prevRight = grade = SPACE; // 초기 그레이드 없음
        prevLenLeft = prevLenRight = max = 0; // 초기 길이 없음
        while (n-- > 0) {
            left = (char) br.read(); // 왼쪽 학생 그레이드
            br.read();
            right = (char) br.read(); // 오른쪽 학생 그레이드
            br.read();
            if (left == prevLeft) { // 현재 왼쪽 학생이 이전 왼쪽 학생의 그레이드와 같으면
                lenLeft = prevLenLeft + 1; // (이전 왼쪽 학생으로 끝나는 연속된 그레이드 길이) + 1
            } else if (left == prevRight) { // 이전 오른쪽 학생의 그레이드와 같으면
                lenLeft = prevLenRight + 1; // (이전 오른쪽 학생으로 끝나는 연속된 그레이드 길이) + 1
            } else { // 이전 줄에 동일한 그레이드의 학생이 없으면
                lenLeft = 1;
            }
            if (right == prevRight) { // 현재 오른쪽 학생이 이전 왼쪽 학생의 그레이드와 같으면
                lenRight = prevLenRight + 1; // (이전 왼쪽 학생으로 끝나는 연속된 그레이드 길이) + 1
            } else if (right == prevLeft) { // 이전 오른쪽 학생의 그레이드와 같으면
                lenRight = prevLenLeft + 1; // (이전 오른쪽 학생으로 끝나는 연속된 그레이드 길이) + 1
            } else { // 이전 줄에 동일한 그레이드의 학생이 없으면
                lenRight = 1;
            }
            if (lenLeft > max) { // 왼쪽 길이가 최대 길이 갱신
                grade = left; // 최소 그레이드 업데이트
                max = lenLeft; // 최대 길이 업데이트
            } else if (lenLeft == max) { // 최대 길이와 동일
                if (left < grade) { // 최소 그레이드 업데이트
                    grade = left;
                }
            }
            if (lenRight > max) { // 오른쪽 길이가 최대 길이 갱신
                grade = right; // 최소 그레이드 업데이트
                max = lenRight; // 최대 길이 업데이트
            } else if (lenRight == max) { // 최대 길이와 동일
                if (right < grade) { // 최소 그레이드 업데이트
                    grade = right;
                }
            }
            prevLeft = left; // 다음 줄 수행 전 현재 정보를 이전 정보로 이동
            prevRight = right;
            prevLenLeft = lenLeft;
            prevLenRight = lenRight;
        } // 최대 길이와 해당 길이에서 최소 그레이드 출력
        System.out.print(new StringBuilder().append(max).append(SPACE).append(grade).toString());
    }
}
