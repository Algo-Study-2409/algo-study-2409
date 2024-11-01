// https://www.acmicpc.net/problem/2407
// https://velog.io/@devjade/JavaScript로-순열과-조합-알고리즘-구현하기

/*
현성준 : BOJ 2407 조합

* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const [N, M] = input[0].split(" ").map(BigInt);

    function factorial(num) {
        let result = 1n;
        for (let i = 1n; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    const result = factorial(N) / (factorial(M) * factorial(N - M));
    console.log(result.toString());
}
