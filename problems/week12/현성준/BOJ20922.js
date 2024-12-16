// https://www.acmicpc.net/20922

// 같은 숫자가 K개 이하로 포함된 최장 연속 부분 수열 길이 구하기
// 입력
// 1: N 길이 , K 조건
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

solution(input);

function solution(input) {
    const [N, K] = input[0].split(" ").map(Number); // N과 K 입력
    const arr = input[1].split(" ").map(Number); // 수열 입력


    let left = 0;
    let right = 0;
    let maxLength = 0;
    const count = new Map();
    // console.log(count);

    while (right < N) {
        const num = arr[right];
        count.set(num, (count.get(num) || 0) + 1);
        // Map(1) { 3 => 1 }
        // Map(2) { 3 => 1, 2 => 1 }
        // Map(3) { 3 => 1, 2 => 1, 5 => 1 }
        // Map(3) { 3 => 1, 2 => 1, 5 => 2 }
        // Map(4) { 3 => 1, 2 => 1, 5 => 2, 6 => 1 }
        // Map(5) { 3 => 1, 2 => 1, 5 => 2, 6 => 1, 4 => 1 }
        // Map(5) { 3 => 1, 2 => 1, 5 => 2, 6 => 1, 4 => 2 }
        // Map(5) { 3 => 1, 2 => 1, 5 => 3, 6 => 1, 4 => 2 }
        // Map(6) { 3 => 1, 2 => 1, 5 => 3, 6 => 1, 4 => 2, 7 => 1 }
        while (count.get(num) > K) {
            const leftNum = arr[left];
            count.set(leftNum, count.get(leftNum) - 1); // 빈도 감소
            if (count.get(leftNum) === 0) {
                count.delete(leftNum);
            }
            left++;
        }
    maxLength = Math.max(maxLength, right - left + 1);
    right++;
    }
    console.log(maxLength);
}
