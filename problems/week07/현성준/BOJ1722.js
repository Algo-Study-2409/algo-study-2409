// https://www.acmicpc.net/problem/1722

/*
* 1: N
* 2: 첫번째: 소문제번호,
*
1. 팩토리얼 기반 접근법

순열의 순서는 팩토리얼의 성질을 이용하여 계산할 수 있습니다. 예를 들어, N=4일 때는 각 위치에 들어갈 수 있는 값이 4! (즉, 24개)로 나뉘어지며, 첫 번째 자리에 따라 다음 자리의 순서가 결정됩니다. 이와 같은 접근을 위해, 우선 모든 숫자의 팩토리얼을 미리 계산해 둡니다.

2. k번째 순열 찾기 (Subtask 1)

k번째 순열을 찾기 위해 팩토리얼을 이용해 첫 번째 자리에 올 수 있는 숫자를 계산해 나갑니다.
0-based 인덱스로 계산하기 위해 k-1로 시작하여 N-1팩토리얼부터 차례로 나누어 첫 자리를 결정합니다.
그 자리를 방문 처리하고, 남은 숫자에서 다시 다음 자리를 찾아갑니다.
3. 순열의 순서 찾기 (Subtask 2)

입력으로 주어진 순열이 전체 순열 중 몇 번째인지 알아내기 위해 각 자리를 순서대로 확인합니다.
앞에 있는 가능한 숫자 중 방문하지 않은 숫자들을 모두 고려하여 팩토리얼 단위로 누적해 위치를 찾습니다.
*
현성준 : BOJ 1722 순열의 순서
- 팩토리얼을 이용해서 숫자찾기
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const N = parseInt(input[0]);
    const cmd = input[1].split(" ").map(BigInt);  // BigInt로 변경하여 처리
    const factorials = Array(N + 1).fill(1n);
    const visited = Array(N + 1).fill(false);

    // 팩토리얼 미리 계산 (BigInt로 변경)
    for (let i = 1; i <= N; i++) {
        factorials[i] = factorials[i - 1] * BigInt(i);
    }

    if (cmd[0] === 1n) {
        // 소문제 1: k번째 순열을 찾기
        let k = cmd[1] - 1n;  // k를 0-based 인덱스로 조정
        const result = [];

        for (let i = 1; i <= N; i++) {
            const factorial = factorials[N - i];
            const index = k / factorial;  // 현재 위치에서 선택할 숫자 인덱스
            k %= factorial;

            // 아직 선택되지 않은 수 중 index 번째 수를 찾음
            let count = -1;
            for (let j = 1; j <= N; j++) {
                if (!visited[j]) count++;
                if (count === Number(index)) {
                    result.push(j);
                    visited[j] = true;
                    break;
                }
            }
        }

        console.log(result.join(" "));
    } else {
        // 소문제 2: 주어진 순열의 순서를 찾기
        const sequence = cmd.slice(1).map(Number);
        let order = 0n;

        for (let i = 0; i < N; i++) {
            for (let j = 1; j < sequence[i]; j++) {
                if (!visited[j]) order += factorials[N - 1 - i];
            }
            visited[sequence[i]] = true;
        }

        console.log((order + 1n).toString());
    }
}
