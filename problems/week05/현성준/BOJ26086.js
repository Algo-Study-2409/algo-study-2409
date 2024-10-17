// https://www.acmicpc.net/problem/26086

/*
* **문제**
* 고유번호 N개를 가지고 있고, 번호는 모두 다르다
* 지환봇 스케줄러는 스택구조 이다
* 명령
* 0p: 고유번호가 p인 업무를 스케줄러에 추가한다 (웬만하면 스케줄러의 맨 앞줄에 추가한다 앞에서부터 처리를 한다)
* 1: 스케줄러에 들어있는 고유번호를 오름차순 정렬, 고유번호 값이 낮은 업무일 수록 스케줄러의 앞에 배치
* 2: 스케줄러에 있는 업무 처리 순서를 뒤집음
*
* **입력**
* 1: 고유번호의 범위 제한 N, 명령 횟수 Q,k
* 2+: 명령에 대한 정보
* */

/*
 * 현성준 : BOJ 26086 어려운 스케줄링
 * - 스택 담을 배열 두기
 * - 입력에 따라 명령처리
 */


const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt"; // 입력 파일 경로 설정
let input = fs.readFileSync(filePath).toString().trim().split("\n"); // 입력을 읽어와 줄바꿈 기준으로 분할
solution(input); // solution 함수 호출

function solution(input) {
    // 입력 데이터 파싱
    const [n, q, k] = input[0].split(" ").map(Number); // n: 업무 고유번호의 범위 제한, q: 명령 횟수, k: 찾고자 하는 업무의 순서
    const commands = input.slice(1).map(line => line.split(" ").map(Number)); // 명령 리스트

    let stack = []; // 스케줄러를 나타내는 배열 (스택)

    // 1. 마지막으로 정렬 명령(1)이 나온 위치를 찾습니다.
    let lastOrder = -1; // 마지막 정렬 명령의 인덱스 초기화
    for (let i = 0; i < commands.length; i++) {
        if (commands[i][0] === 1) { // 명령이 1인 경우
            lastOrder = i; // 인덱스 저장
        }
    }

    // 2. 마지막 정렬 명령 이전까지의 명령을 처리합니다.
    let reverseFlag = false; // 현재 뒤집기 상태를 나타내는 플래그
    for (let i = 0; i < lastOrder; i++) {
        if (commands[i][0] === 2) { // 명령이 2인 경우 (뒤집기)
            reverseFlag = !reverseFlag; // 뒤집기 상태 토글
        } else if (commands[i][0] === 0) { // 명령이 0인 경우 (업무 추가)
            stack.push(commands[i][1]); // 업무를 스택에 추가
        }
        // 예상되는 스택 상태와 reverseFlag 값을 출력해볼 수 있습니다.
        // 예를 들어, i=0일 때, stack=[1], reverseFlag=false
    }

    // 3. 마지막 정렬 명령이 있었다면 스택을 정렬합니다.
    if (lastOrder !== -1) {
        stack.sort((a, b) => a - b); // 오름차순 정렬
        reverseFlag = false; // 정렬 이후에는 뒤집기 상태를 초기화
    }

    // 4. 남은 명령들을 처리합니다.
    for (let i = lastOrder + 1; i < commands.length; i++) {
        if (commands[i][0] === 0) { // 업무 추가 명령
            if (!reverseFlag) {
                stack.unshift(commands[i][1]); // 뒤집기 상태가 아니라면 앞에 추가
            } else {
                stack.push(commands[i][1]); // 뒤집기 상태라면 뒤에 추가
            }
        } else if (commands[i][0] === 2) { // 뒤집기 명령
            reverseFlag = !reverseFlag; // 뒤집기 상태 토글
        }
        // 각 단계에서의 스택 상태와 reverseFlag를 출력해볼 수 있습니다.
    }

    // 5. 최종적으로 k번째 업무의 고유번호를 출력합니다.
    console.log(!reverseFlag ? stack[k - 1] : stack[stack.length - k]);
}
