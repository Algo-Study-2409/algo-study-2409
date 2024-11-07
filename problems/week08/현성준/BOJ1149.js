// https://www.acmicpc.net/problem/1149

/*
* 집이 N개 거리는 선분 1번 집부터 N번 집이 순서대로 있음
* 집은 빨 토 파 중 한개의색으로 칠해야함
* 1번집의 색과 2번집의 색이 같지 않아야 한다
* N번집의 색은 N-1번집의 색과 같지 않아야한다
* 그니까 쉽게 생각하면 색을 한번씩은 써야하는거 아닌가
*
현성준 : BOJ 1149 RGB 거리
 - 모든 경우의수를 계산 후 최소값만 골라냄
* */


const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
    const [N, ...homes] = input.map((v) => v.split(" ").map(Number));
    const dp = homes;

    for (let i = 1; i < N; i++) {
        dp[i][0] = dp[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
        dp[i][1] = dp[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
        dp[i][2] = dp[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
    }

    console.log(Math.min(...dp[N - 1]));
}

solution(input);