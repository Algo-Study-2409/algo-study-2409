import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1149 RGB거리
 * - 72 ms
 * - DP
 * - 마지막을 빨강으로 칠하는 비용
 * -   = min(직전에 초록을 칠했을 때의 비용,
 * -     직전에 파랑을 칠했을 때의 비용)
 * - 초록, 파랑도 동일한 방법으로 계산
 * - 셋 중 가장 적은 비용 출력
 * */
public class BOJ1149_RGB거리 {
    public static void main(String[] args) throws IOException {
        int n;
        int red;
        int green;
        int blue;
        int prevRed;
        int prevGreen;
        int prevBlue;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (prevRed = 0, prevGreen = 0, prevBlue = 0; n-- > 0; prevRed = red, prevGreen = green, prevBlue = blue) {
            st = new StringTokenizer(br.readLine(), " ", false);
            red = Math.min(prevGreen, prevBlue) + Integer.parseInt(st.nextToken()); // min(직전에 초록 or 파랑을 칠했을 때의 비용)
            green = Math.min(prevBlue, prevRed) + Integer.parseInt(st.nextToken()); // min(직전에 파랑 or 빨강을 칠했을 때의 비용)
            blue = Math.min(prevRed, prevGreen) + Integer.parseInt(st.nextToken()); // min(직전에 빨강 or 초록을 칠했을 때의 비용)
        }
        System.out.print(Math.min(Math.min(prevRed, prevGreen), prevBlue)); // 셋 중 가장 적은 비용 출력
    }
}
