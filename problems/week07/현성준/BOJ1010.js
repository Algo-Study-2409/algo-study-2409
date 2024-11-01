// https://www.acmicpc.net/problem/1010

/*
현성준 : BOJ 1010 다리 놓기
- 조합
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function factorial(num) {
    let result = 1n;
    for (let i = 2n; i <= num; i++) {
        result *= i;
    }
    return result;
}

function combination(n, r) {
    if (n < r) return 0n;
    return factorial(n) / (factorial(r) * factorial(n - r));
}

function solution(input) {
    const T = parseInt(input[0]);
    let results = [];

    for (let i = 1; i <= T; i++) {
        const [N, M] = input[i].split(" ").map(BigInt);
        results.push(combination(M, N).toString());
    }

    console.log(results.join("\n"));
}

// solution(input);

console.log(factorial(3));