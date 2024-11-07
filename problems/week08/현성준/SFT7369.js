// https://softeer.ai/practice/7369

/*
* n*n 격자, 각칸 씨앗 1개
* 씨앗을 키우기 위해 수로가 필요, 인접한 오른쪽 혹은 아래를 선택하여 (n,n)을 끝으로 수로 설치를 마쳐야함
* 수로가 설치한 칸내에 스프링쿨러를 놓을 수 있음 스플링클러 있다면 열매 수확량을 2배로 늘릴수있다
* 수로와 스플링클러
*
현성준 : SFT 7369 나무 수확
 - dp 배열에 최대 수확량 저장
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
    const N = parseInt(input[0]);
    const graph = input.slice(1).map(line => line.split(" ").map(Number));

    // dp 배열 생성 (각 위치에 [최대 수확량, 스프링클러 설치한 최대 수확량] 형태로 저장)
    const dp = Array.from({length: N}, () => Array.from({length: N}, () => [0, 0]));

    // 동적 프로그래밍으로 최대 수확량 계산
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            const up = i === 0 ? 0 : dp[i - 1][j][0];
            const left = j === 0 ? 0 : dp[i][j - 1][0];
            dp[i][j][0] = Math.max(up, left) + graph[i][j];

            const umax = i === 0 ? 0 : Math.max(dp[i - 1][j][1] + graph[i][j], dp[i - 1][j][0] + graph[i][j] * 2);
            const lmax = j === 0 ? 0 : Math.max(dp[i][j - 1][1] + graph[i][j], dp[i][j - 1][0] + graph[i][j] * 2);
            dp[i][j][1] = Math.max(umax, lmax, graph[i][j] * 2);
        }
    }

    console.log(dp[N - 1][N - 1][1]);
}

solution(input);


