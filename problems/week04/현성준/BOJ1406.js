const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = fs.readFileSync(filePath).toString().trim().split('\n');

function solution(input) {
    let leftStack = input[0].split('');  // 초기 입력 문자열을 왼쪽 스택에 저장
    let rightStack = [];  // 커서의 오른쪽을 관리하는 스택
    const M = +input[1];  // 명령의 수

    for (let i = 2; i < 2 + M; i++) {
        const command = input[i];

        if (command[0] === 'L') {
            // 커서를 왼쪽으로 이동
            if (leftStack.length > 0) {
                rightStack.push(leftStack.pop());
            }
        } else if (command[0] === 'D') {
            // 커서를 오른쪽으로 이동
            if (rightStack.length > 0) {
                leftStack.push(rightStack.pop());
            }
        } else if (command[0] === 'B') {
            // 커서 왼쪽 문자 삭제
            if (leftStack.length > 0) {
                leftStack.pop();
            }
        } else if (command[0] === 'P') {
            // 커서 왼쪽에 문자 삽입
            const charToInsert = command.split(' ')[1];
            leftStack.push(charToInsert);
        }
    }

    // 최종 문자열은 leftStack + rightStack.reverse()
    console.log(leftStack.join('') + rightStack.reverse().join(''));
}

solution(input);
