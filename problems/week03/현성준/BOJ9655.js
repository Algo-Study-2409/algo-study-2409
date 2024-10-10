// https://www.acmicpc.net/problem/9655

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim();
solution(+input);

// bottom-up
function solution(n) {
    // dp 배열 선언 (1-based index)
    let dp = Array(n + 1).fill("");

    // 기저 조건 설정
    dp[1] = 'SK'; // 상근이가 이김
    if (n >= 2) dp[2] = 'CY'; // 창영이가 이김
    if (n >= 3) dp[3] = 'SK'; // 상근이가 이김

    // DP 테이블 채우기
    for (let i = 4; i <= n; i++) {
        if (dp[i - 1] === 'CY' || dp[i - 3] === 'CY') {
            // n-1, n-3이 cy라면 당연히 sk가 된다는 뜻이었음
            dp[i] = 'SK';
        } else {
            dp[i] = 'CY';
        }
    }

    // 결과 출력
    console.log(dp[n]);
}
// top-down
function solution(n) {
    // 메모이제이션을 위한 dp 배열 (-1은 아직 계산되지 않은 상태)
    let dp = Array(n + 1).fill(-1);

    // 재귀적으로 승자를 결정하는 함수
    function game(n) {
        // 기저 조건
        if (n === 1) return 'SK'; // 1개 남았으면 상근이가 이김
        if (n === 2) return 'CY'; // 2개 남았으면 창영이가 이김
        if (n === 3) return 'SK'; // 3개 남았으면 상근이가 이김

        // 이미 계산된 값이 있으면 그 값을 반환
        if (dp[n] !== -1) return dp[n];

        // 돌을 1개 또는 3개 가져가는 상황을 고려
        // 상대방이 지는 상황이면 현재 플레이어가 이김
        if (game(n - 1) === 'CY' || game(n - 3) === 'CY') {
            dp[n] = 'SK'; // 상근이가 이김
        } else {
            dp[n] = 'CY'; // 창영이가 이김
        }

        return dp[n]; // 계산된 결과 반환
    }

    // n개의 돌에 대한 결과를 출력
    console.log(game(n));
}