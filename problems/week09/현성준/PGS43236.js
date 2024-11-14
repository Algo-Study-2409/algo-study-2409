// https://school.programmers.co.kr/learn/courses/30/lessons/43236

/*
현성준 : PGS 43236 징검다리
 - 바위간 최소 거리를 구해서 각 구간에 제거 가능한 바위 개수 계산
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
const distance = Number(input[0]);
const n = Number(input[input.length - 1]);
const rocks = input.slice(1, input.length - 1).map(Number);
solution(distance, rocks, n);

// console.log(distance); // 25
// console.log(n); // 2
// console.log(rocks); // [ 2, 11, 14, 17, 21, 25 ]

function solution(distance, rocks, n) {
    // 바위 배열 정렬 및 도착지점 추가
    rocks.sort((a, b) => a - b);
    rocks.push(distance); // 도착지점 포함

    let left = 1; // 최소 거리
    let right = distance; // 최대 거리
    let answer = 0;

    // 이분 탐색
    while (left <= right) {
        const mid = Math.floor((left + right) / 2); // 거리의 최솟값 후보
        let prev = 0; // 출발지점
        let removeCount = 0; // 제거한 바위 수

        for (let rock of rocks) {
            if (rock - prev < mid) {
                // 거리가 mid보다 작으면 바위를 제거
                removeCount++;
            } else {
                // 거리가 충분하면 prev를 갱신
                prev = rock;
            }
        }

        if (removeCount > n) {
            // 제거한 바위가 너무 많으면 mid를 줄임
            right = mid - 1;
        } else {
            // 조건을 만족하면 답을 갱신하고 mid를 늘림
            answer = mid;
            left = mid + 1;
        }

        console.log(`현재 mid: ${mid}, 제거한 바위 수: ${removeCount}, left: ${left}, right: ${right}`);
    }

    console.log("최대 거리의 최솟값:", answer);
    return answer;
}
