// https://www.acmicpc.net/problem/7795

/*
* A의 크기가 B의 크기보다 큰 쌍의 개수를 계산
*
현성준 : 7795 먹을 것인가 먹힐 것인가
- A의 원소마다 B에서 이분탐색하여 A보다 작은 원소의 개수 계산
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const T = parseInt(input[0]); // 테스트 케이스 개수
    let index = 1; // 현재 줄의 위치
    const results = [];

    for (let t = 0; t < T; t++) {
        const [N, M] = input[index].split(" ").map(Number); // N, M 읽기
        const A = input[index + 1].split(" ").map(Number); // A 배열
        const B = input[index + 2].split(" ").map(Number); // B 배열

        // 정렬
        A.sort((a, b) => a - b);
        B.sort((a, b) => a - b);

        let count = 0;

        for (let a of A) {
            count += binarySearch(B, a);
        }

        results.push(count);
        index += 3; // 다음 테스트 케이스로 이동
    }

    console.log(results.join("\n"));
}

// B에서 x보다 작은 원소의 개수를 찾는 함수 (이분 탐색)
function binarySearch(arr, x) {
    let left = 0;
    let right = arr.length - 1;
    let result = 0;

    while (left <= right) {
        const mid = Math.floor((left + right) / 2);

        if (arr[mid] < x) {
            result = mid + 1; // mid까지는 조건을 만족
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return result;
}
