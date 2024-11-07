// https://www.acmicpc.net/problem/1660

/*
* 현성준 : BOJ 1660 캡틴 이다솜
* - dp를 활용하여 풀이
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = parseInt(fs.readFileSync(filePath).toString().trim(), 10);

function solution(n) {
    let balls = [];
    let b = 0;
    let i = 1;

    // 일단 사면체(피라미드) 대포알 개수들 준비
    // 이거는 어떤 i층짜리 사면체를 쌓을 때 필요한 대포알 개수를 계산해서 배열에 쭉 집어넣어. 여기서는 balls라는 배열에다가 넣을 거야.
    // 이거 재밌는게, i층까지 대포알 합은 (i * (i + 1)) / 2라는 공식이 적용됨.

    while (b < n) {
        b += (i * (i + 1)) / 2;
        balls.push(b);
        i++;
    }

    // DP 배열 초기화
    // DP는 최솟값 찾기 용으로 사용할 거니까 일단 다 MAX 값으로 채워 넣는다. 이 MAX는 Infinity를 써도 되고, Number.MAX_SAFE_INTEGER 써도 돼.
    // 첫 번째 값인 dp[0]은 당연히 0으로 초기화. 0개의 대포알로는 0개의 사면체 필요하니까.

    const MAX = Number.MAX_SAFE_INTEGER;
    const dp = Array(n + 1).fill(MAX);
    dp[0] = 0;


    // DP 로직 돌리기
    // 이제 1부터 N까지 순회하면서 최소 사면체 개수를 찾는다. dp[i]는 i개의 대포알을 만들 때 필요한 최소 사면체 개수로 채운다.
    // 이때, 주어진 balls 배열에서 각 사면체를 하나씩 사용해보고 i개 대포알을 채울 때 최소 몇 개가 필요한지를 갱신해나가는 거임.
    // 예를 들어, dp[i]를 dp[i - ball] + 1로 갱신하는데, 이때 ball은 하나의 사면체 대포알 개수를 의미함.

    for (let i = 1; i <= n; i++) {
        for (const ball of balls) {
            if (ball > i) break;
            dp[i] = Math.min(dp[i], dp[i - ball] + 1);
        }
    }

    return dp[n];
}

console.log(solution(input));