// https://www.acmicpc.net/problem/17609

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const [N, ...strings] = input;

  strings.forEach((str) => {
    console.log(checkPalindrome(str));
  });
}

function checkPalindrome(str) {
  let left = 0;
  let right = str.length - 1;

  while (left < right) {
    if (str[left] === str[right]) {
      // 양 끝 문자가 같으면 다음으로 넘어간다
      left++;
      right--;
    } else {
      // 양 끝 문자가 다를 때, 한 쪽씩 빼고 회문인지 확인
      if (
        isPalindromeRange(str, left + 1, right) ||
        isPalindromeRange(str, left, right - 1)
      ) {
        return 1; // 유사회문
      } else {
        return 2; // 회문도 유사회문도 아님
      }
    }
  }

  return 0; // 완전한 회문
}

// 특정 범위의 문자열이 회문인지 확인하는 함수
function isPalindromeRange(str, left, right) {
  while (left < right) {
    if (str[left] !== str[right]) {
      return false;
    }
    left++;
    right--;
  }
  return true;
}

solution(input);

// 앞뒤가 똑같은 것 회문이라고 부름
// 회문 0 유사회문 1 아닌것 2
// G선생 풀이 요청 예정 