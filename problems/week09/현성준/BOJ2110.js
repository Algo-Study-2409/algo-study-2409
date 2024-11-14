// https://www.acmicpc.net/problem/2110

/*
현성준 : BOJ 2110 공유기 설치
 - 이분 탐색으로 공유기간 최소거리를 조정하고 설치가능한 개수 계산
 - 설치 가능한 공유기 조건을 만족하면 mid+ 부족하면 mid-
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
    const [N, C] = input[0].split(" ").map(Number); // 집의 개수와 공유기의 개수
    const houses = input.slice(1).map(Number); // 집의 좌표
    houses.sort((a, b) => a - b); // 좌표 정렬

    let left = 1; // 최소 거리
    let right = houses[N - 1] - houses[0]; // 최대 거리
    let answer = 0;

    while (left <= right) {
        const mid = Math.floor((left + right) / 2); // 공유기 간 최소 거리 후보
        let installed = 1; // 첫 번째 집에 설치
        let prev = houses[0]; // 마지막으로 설치한 집의 위치

        for (let i = 1; i < N; i++) {
            if (houses[i] - prev >= mid) {
                installed++; // 공유기 설치
                prev = houses[i]; // 현재 위치를 마지막 설치 위치로 갱신
            }
        }

        if (installed >= C) {
            // 설치 가능한 공유기가 C개 이상이면 거리 늘리기
            answer = mid;
            left = mid + 1;
        } else {
            // 설치 가능한 공유기가 부족하면 거리 줄이기
            right = mid - 1;
        }
    }

    console.log(answer);
}

solution(input);
