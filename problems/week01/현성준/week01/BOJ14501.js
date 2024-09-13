const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
// https://kau-algorithm.tistory.com/800 풀이를 참고함
let input = fs.readFileSync(filePath).toString().trim().split("\n");
[n, ...input] = input;
N = +n;
TP = input.map((v) => v.split(" ").map((v) => +v));
console.log(solution(TP, N));
function solution(input, N) {
  const DP = new Array(N).fill(0);
  for (let i = 0; i < N; i++) {
    const [T, P] = input[i];
    if (i + T > N) continue;
    DP[i] = DP[i] + P;
    console.log(DP[i], i);

    for (let j = i + T; j < N; j++) {
      DP[j] = Math.max(DP[j], DP[i]);
    }
  }
  return Math.max(...DP);
}
// 아직 이해못하겠음

// N+1일 되는날 퇴사하기 위해 남은 N일 동안 최대한 많은 상담을 한다
// 최대한 많은 상담 서로다른 사람
// 건당 시간 Ti , 건당 금액 Pi

// N+1일이 넘기지 않기
// T 날짜 고려하기
// P 최대 값 찾기

// 음 너무 어렵게 생각하나
// 마지막 테스트 케이스만 안되서 이것저것 수정하다 코드 날림 ㅠㅠ

// FIXME 삽질기록
// const { log } = require("console");
// const fs = require("fs");
// const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
// let input = fs.readFileSync(filePath).toString().trim().split("\n");
// [N, ...input] = input;
// TP = input.map((v) => v.split(" ").map((v) => +v));
// console.log(solution(TP, N));
// function solution(input, N) {
//   N = +N + 1;
//   let result = [];
//   for (let j = 0; j < input.length; j++) {
//     let values = [];
//     T = 0;
//     sum = [];
//     for (let i = 0; i < input.length; i++) {
//       if (T > 1) {
//         T -= 1;
//         continue;
//       }
//       T = input[i][0];
//       sum.push(T);
//       isN = sum.reduce((acc, cur) => acc + cur, 0);
//       if (isN >= N) continue;
//       values.push(input[i][1]);
//     }
//     result.push(values.reduce((acc, cur) => acc + cur, 0));
//   }
//   return result;
// }
