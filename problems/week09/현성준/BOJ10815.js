// https://www.acmicpc.net/problem/10815

/*
* 1: 상근이가 가지고 있는 숫자 카드의 개수
* 2: 숫자 카드에 적혀 있는 정수
* 3: 정수 M
* 4: 상근이가 가지고 있는 카드인지?
* */

/*
현성준 : BOJ 10815 숫자 카드
 - 이분탐색 활용
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const N = Number(input[0]); // 상근이가 가진 숫자 카드 개수
    const cards = input[1].split(" ").map(Number); // 상근이가 가진 숫자 카드 목록
    const M = Number(input[2]); // 확인해야 할 숫자 개수
    const queries = input[3].split(" ").map(Number); // 확인해야 할 숫자 목록

    // 상근이의 카드 목록 정렬
    cards.sort((a, b) => a - b);

    // 이분 탐색 함수
    const binarySearch = (arr, target) => {
        let low = 0;
        let high = arr.length - 1;

        while (low <= high) {
            const mid = Math.floor((low + high) / 2);

            if (arr[mid] === target) {
                return 1; // 숫자를 찾음
            } else if (arr[mid] < target) {
                low = mid + 1; // 오른쪽 탐색
            } else {
                high = mid - 1; // 왼쪽 탐색
            }
        }

        return 0; // 숫자가 없음
    };

    // 각 query에 대해 이분 탐색 수행
    const results = queries.map((query) => binarySearch(cards, query));

    // 결과 출력
    console.log(results.join(" "));
}
