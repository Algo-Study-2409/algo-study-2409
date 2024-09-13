const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
[fi, se] = input;
[N, M] = fi.split(" ").map((v) => +v);
arr = se.split(" ").map((v) => +v);
console.log(solution(N, M, arr));

function solution(N, M, arr) {
  const deque = [...Array(N)].map((_, i) => i + 1);
  let totalOperations = 0;
  arr.forEach((target) => {
    let targetIndex = deque.indexOf(target);
    let leftRotations = targetIndex; // 왼쪽으로 회전
    let rightRotations = deque.length - targetIndex; // 오른쪽으로 회전

    // 최적의 방향으로 회전
    if (leftRotations <= rightRotations) {
      // 왼쪽으로 회전
      totalOperations += leftRotations;
      for (let i = 0; i < leftRotations; i++) {
        deque.push(deque.shift());
      }
    } else {
      // 오른쪽으로 회전
      totalOperations += rightRotations;
      for (let i = 0; i < rightRotations; i++) {
        deque.unshift(deque.pop());
      }
    }
    deque.shift(); // 목표숫자제거
  });
  return totalOperations;
}

// 큐의 크기 N, 뽑아내려고 하는 수의 개수 M
// 음 한글부터 뭔말인지 모르겠다
// 사실 예제도 이해가 안감
// 계속 읽어서 이해함 그러나 진짜 모르겠음 ㅠㅠ
// GPT 사용함 -> 어려운데..? 내일 다시 볼 예정 
