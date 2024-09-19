// 문제
// 연속한 소수 p와 p+n이 있을 때, 그 사이에 있는 n-1개의 합성수(소수나 1이 아닌 양의 정수)는 길이가 n인 소수 사이 수열라고 부른다.

// 양의 정수 k가 주어졌을 때, k를 포함하는 소수 사이 수열의 길이를 구하는 프로그램을 작성하시오. k를 포함하는 소수 사이 수열이 없을 때는 길이가 0이다.

// 예를 들어, 소수 23과 29의 소수 사이 수열은 {24, 25, 26, 27, 28}이고, 길이는 6이다.

// 입력
// 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 테스트 케이스는 한 줄로 이루어져 있고, 한 줄에 정수 k가 주어진다. 각각의 정수는 1보다 크고, 100000번째 소수(1299709)와 작거나 같다.

// 삽질 기록
// RangeError: Maximum call stack size exceeded => 492170
// const fibo = (n, memo = {}) => {
//   if (n < 1) return 1;
//   if (n in memo) return memo[n]; // 이미 계산된 값이 있다면 반환
//   memo[n] = fibo(n - 1, memo) + fibo(n - 2, memo);
//   return memo[n];
// };
// 소수를 구하려고 시도해봤으나 에러가 났음

// 도저히 안풀려서 G선생 도움받음
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
[N, ...input] = input;
N = Number(N);
input = input.map((v) => +v);
solution(N, input);
function solution(N, input) {
  const MAX = 1299710; // 문제의 최대값+1
  const isPrime = Array(MAX).fill(true); // 소수를 판별하기 위한 배열 결국 한도초과를 피하려면 배열을 써야하는구나
  isPrime[0] = isPrime[1] = false; // 0과 1은 소수가 아니라고 함

  // 에라토스테네스의 채 라는게 있는가봄
  for (let i = 2; i * i < MAX; i++) {
    if (isPrime[i]) {
      for (let j = i * i; j < MAX; j += i) {
        // 이부분 왜 i*i 인지 이해가 안감
        isPrime[j] = false;
      }
    }
  }
  // 소수 리스트 생성
  const primes = [];
  for (let i = 2; i < MAX; i++) {
    if (isPrime[i]) primes.push(i);
  }

  input.forEach((k) => {
    if (isPrime[k]) {
      console.log(0);
      return;
    }
    // 소수가 아닐 떄,k를 포함하는 소수 사이 수열 찾기
    let lower = 0;
    let upper = 0;

    // 소수 목록중 k보다 작은 가장 큰 소수를 찾기
    for (let i = 0; i < primes.length; i++) {
      if (primes[i] > k) {
        lower = primes[i - 1]; //k보다 작은 소수 -> ??
        upper = primes[i]; // k보다 큰 소수 -> ??
        break;
      }
    }
    const sequenceLength = upper - lower; // 소수 사이 수열의 길이
    console.log(sequenceLength);
    return sequenceLength;
  });
}
