// https://www.acmicpc.net/problem/27527
// 현성준 : BOJ 배너걸기
/*
길이가 M인 연속된 구간에서 특정 높이가 9M/10 이상 존재해야함
x는 올림 함수로구현
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const [N, M] = input[0].split(" ").map(Number); // N: 구간 수, M: 연속된 구간의 길이
    const A = input[1].split(" ").map(Number); // 높이 정보 배열

    const threshold = Math.ceil((9 * M) / 10); // 조건을 만족하기 위한 최소 개수
    const countMap = new Map(); // 구간 내 각 높이의 빈도를 기록할 맵

    let maxCount = 0; // 구간 내 최대 빈도수

    // 초기 윈도우 설정
    for (let i = 0; i < M; i++) {
        const value = A[i];
        countMap.set(value, (countMap.get(value) || 0) + 1);
        maxCount = Math.max(maxCount, countMap.get(value)); // 최대 빈도수 갱신
    }

    if (maxCount >= threshold) {
        console.log("YES");
        return;
    }

    // 슬라이딩 윈도우로 탐색
    for (let i = M; i < N; i++) {
        const valueToRemove = A[i - M]; // 윈도우에서 제거되는 값
        const valueToAdd = A[i]; // 윈도우에 추가되는 값

        // 제거
        countMap.set(valueToRemove, countMap.get(valueToRemove) - 1);
        if (countMap.get(valueToRemove) === 0) {
            countMap.delete(valueToRemove);
        }

        // 추가
        countMap.set(valueToAdd, (countMap.get(valueToAdd) || 0) + 1);
        maxCount = Math.max(maxCount, countMap.get(valueToAdd)); // 최대 빈도수 갱신

        // 조건 만족 여부 확인
        if (maxCount >= threshold) {
            console.log("YES");
            return;
        }
    }

    console.log("NO");
}
