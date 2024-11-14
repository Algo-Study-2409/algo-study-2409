// https://www.acmicpc.net/problem/1477
/*
현성준 : BOJ 1477 휴게소 세우기
 - 휴게소간의 거리를 조정하면서 필요한 휴게소 개수를 계산
 - 휴게소가 M개 이하면 mid-1 초과하면 mid+1
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
    const [N, M, L] = input[0].split(" ").map(Number);
    const restStops = N > 0 ? input[1].split(" ").map(Number) : [];
    restStops.push(0); // 고속도로 시작점 추가
    restStops.push(L); // 고속도로 끝점 추가
    restStops.sort((a, b) => a - b); // 정렬

    // 이분 탐색 설정
    let left = 1;
    let right = L;
    let answer = 0;

    while (left <= right) {
        const mid = Math.floor((left + right) / 2); // 구간 최대 길이 후보
        let neededStops = 0;

        for (let i = 1; i < restStops.length; i++) {
            const gap = restStops[i] - restStops[i - 1];
            if (gap > mid) {
                neededStops += Math.floor((gap - 1) / mid); // 필요한 휴게소 수
            }
        }

        if (neededStops > M) {
            // 필요한 휴게소가 많으면 길이 증가
            left = mid + 1;
        } else {
            // 필요한 휴게소가 충분하면 길이 감소
            answer = mid;
            right = mid - 1;
        }
    }

    console.log(answer);
}

solution(input);
