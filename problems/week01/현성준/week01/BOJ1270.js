// https://www.acmicpc.net/problem/1270

// 군대 번호가 전체의 절반을 초과한다면 군대번호를 적는 룰임
// 메모리초과 벗어날수가 없음 
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  let answer = [];
  let idx = 0;
  const n = parseInt(input[idx++], 10); // 테스트 케이스 수

  for (let i = 0; i < n; i++) {
    const landInfo = input[idx++].split(" ").map(Number); // 각 테스트 케이스의 군대 번호 정보
    const t = landInfo.shift(); // 첫 번째 숫자는 땅의 크기

    // 군대 번호가 음수 또는 매우 큰 값일 수 있으므로 상대적 위치를 저장할 배열 크기를 설정
    let minValue = Math.min(...landInfo);
    let maxValue = Math.max(...landInfo);
    const offset = -minValue;
    const countArray = new Array(maxValue - minValue + 1).fill(0);

    let maxCount = 0; // 가장 많이 등장한 군대 번호의 횟수
    let strongest = 0; // 가장 많이 등장한 군대 번호

    for (let num of landInfo) {
      // 군대 번호 등장 횟수 카운트 (배열을 사용)
      countArray[num + offset]++;

      const count = countArray[num + offset]; // 해당 군대 번호의 등장 횟수

      // 등장 횟수가 현재 최댓값보다 크면 갱신
      if (count > maxCount) {
        maxCount = count;
        strongest = num;
      }
    }

    // 최빈값이 전체 군대의 절반 이상인지 확인
    if (maxCount > t / 2) {
      answer.push(strongest); // 과반수 이상이면 해당 군대 번호 추가
    } else {
      answer.push("SYJKGW"); // 과반수가 아니면 SYJKGW 추가
    }
  }

  console.log(answer.join("\n")); // 결과 출력
}

solution(input);
