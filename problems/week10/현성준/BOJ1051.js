// https://www.acmicpc.net/problem/1051

// 현성준 : BOJ 숫자 정사각형
// 1. 입력 처리: N, M, 직사각형 데이터를 읽음.
// 2. 가능한 정사각형 크기 k = min(N, M)부터 시작.
// 3. k를 줄여가며 다음을 반복:
//     3.1 모든 (r, c)를 시작점으로 가능한 정사각형 검사.
// 3.2 정사각형의 네 꼭짓점 확인:
//     - 왼쪽 위: (r, c)
// - 오른쪽 위: (r, c + k - 1)
// - 왼쪽 아래: (r + k - 1, c)
// - 오른쪽 아래: (r + k - 1, c + k - 1)
// 3.3 네 꼭짓점이 같으면 k^2 출력 후 종료.
// 4. 모든 k를 검사해도 만족하는 정사각형이 없으면 1 출력.
//
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const [N, M] = input[0].split(" ").map(Number); // 직사각형 크기
    const rect = input.slice(1).map(line => line.split("").map(Number)); // 직사각형 데이터

    let maxSize = 1; // 최대 정사각형 크기 초기값

    // 가능한 정사각형 크기(k)를 큰 값부터 줄여가며 탐색
    const maxK = Math.min(N, M); // 가능한 최대 정사각형 한 변의 길이
    for (let k = maxK; k >= 1; k--) {
        for (let r = 0; r <= N - k; r++) {
            for (let c = 0; c <= M - k; c++) {
                // 네 꼭짓점이 같은지 확인
                const topLeft = rect[r][c];
                const topRight = rect[r][c + k - 1];
                const bottomLeft = rect[r + k - 1][c];
                const bottomRight = rect[r + k - 1][c + k - 1];

                if (
                    topLeft === topRight &&
                    topRight === bottomLeft &&
                    bottomLeft === bottomRight
                ) {
                    // 가장 큰 정사각형을 찾으면 크기를 갱신하고 종료
                    console.log(k * k);
                    return;
                }
            }
        }
    }

    // 모든 정사각형을 탐색했지만 조건을 만족하지 못하면 최소 크기 출력
    console.log(maxSize);
}
