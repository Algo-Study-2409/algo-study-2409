// https://www.acmicpc.net/problem/1654

/*
* 현성준 : BOJ 1654 랜선 자르기
* -
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const [K, N] = input.shift().split(' ').map(Number);
    const lines = input.map(Number).sort((a, b) => a - b);
    const target = N;

    let left = 1;  // 랜선 길이는 최소 1
    let right = lines[lines.length - 1];
    let answer = 0;

    while (left <= right) {
        let mid = Math.floor((left + right) / 2);
        let lineNum = lines.reduce((acc, cur) => acc + Math.floor(cur / mid), 0);

        if (lineNum >= target) {
            answer = mid;  // 가능한 최대 길이를 저장
            left = mid + 1;  // 더 긴 랜선을 찾기 위해 left 증가
        } else {
            right = mid - 1;  // 랜선이 부족하면 길이를 줄이기 위해 right 감소
        }
    }

    console.log(answer);
}
