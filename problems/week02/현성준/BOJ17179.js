// https://www.acmicpc.net/problem/17179

/*
 * 현성준 : BOJ 17179 케이크 자르기
 */


const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
    // 첫 번째 줄 입력 받기 (N: 테스트 케이스 수, M: 지점 수, L: 케이크 길이)
    const [N, M, L] = input.shift().split(" ").map(Number);

    // 자를 지점 입력 받기 (cut_points)
    let cutPoints = [];
    for (let i = 0; i < M; i++) {
        cutPoints.push(parseInt(input.shift()));
    }
    cutPoints.push(L); // 케이크의 끝도 하나의 자를 지점으로 추가

    // 탐욕적 접근으로 최소 사잇값 이상으로 케이크를 자를 수 있는지 확인
    const canCut = (minLength, k) => {
        let left = 0; // 이전에 자른 지점
        let cnt = 0;  // 자른 횟수

        for (let right of cutPoints) {
            if (right - left >= minLength) {
                left = right;  // 자른 지점을 업데이트
                cnt++;         // 자른 횟수 증가
            }
        }

        // 자를 수 있는 횟수가 k보다 많으면 True
        return cnt > k;
    };

    // N개의 테스트 케이스에 대해 처리
    for (let i = 0; i < N; i++) {
        let k = parseInt(input.shift()); // 이번 테스트 케이스의 k값

        // 이분 탐색의 범위 설정
        let left = 1;
        let right = 4000000;
        let answer = 0;

        // 이분 탐색으로 최소값을 최대화
        while (left <= right) {
            const mid = Math.floor((left + right) / 2);

            if (canCut(mid, k)) {
                answer = Math.max(mid, answer); // 최소값 중 최대값을 갱신
                left = mid + 1; // 더 큰 최소값을 찾기 위해 탐색 범위 조정
            } else {
                right = mid - 1; // 너무 큰 경우 범위를 좁힘
            }
        }
        console.log(answer);
    }
}

solution(input);
