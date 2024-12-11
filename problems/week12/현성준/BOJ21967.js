// https://www.acmicpc.net/problem/21967

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const N = +input[0]; // 수열 길이
    const A = input[1].split(" ").map(Number); // 수열

    let left = 0;
    let maxLength = 0;
    const count = new Array(11).fill(0); // 1~10 범위의 숫자 빈도 관리
    let maxVal = 0; // 현재 구간의 최대값
    let minVal = 10; // 현재 구간의 최소값

    for (let right = 0; right < N; right++) {
        const num = A[right];
        count[num]++;
        maxVal = Math.max(maxVal, num); // 최대값 갱신
        minVal = Math.min(minVal, num); // 최소값 갱신

        // 최댓값과 최솟값의 차이가 2를 초과하면, left를 이동
        while (maxVal - minVal > 2) {
            const leftNum = A[left];
            count[leftNum]--;
            if (count[leftNum] === 0) {
                // 빈도 배열에서 0이 된 숫자를 기준으로 min/max 갱신
                if (leftNum === minVal) {
                    while (count[minVal] === 0) minVal++;
                }
                if (leftNum === maxVal) {
                    while (count[maxVal] === 0) maxVal--;
                }
            }
            left++; // 왼쪽 포인터 이동
        }

        // 최대 길이 갱신
        maxLength = Math.max(maxLength, right - left + 1);
    }

    console.log(maxLength);
}
