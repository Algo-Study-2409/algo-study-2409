// https://www.acmicpc.net/problem/17281
// 현성준 : BOJ ⚾
/*
경기 규칙:
N이닝 동안 진행하며, 각 이닝에는 타순에 따라 9명의 선수가 차례로 공격합니다.
타자는 안타(1), 2루타(2), 3루타(3), 홈런(4), 아웃(0) 중 하나를 기록합니다.
3아웃이 발생하면 공격이 종료되고 다음 이닝으로 넘어갑니다.
타순은 이닝이 바뀌어도 이어지며, 경기 중에는 변경할 수 없습니다.
제약사항:
1번 선수는 무조건 4번 타자로 고정입니다.
나머지 8명의 선수의 타순을 조합하여 최대 점수를 얻는 타순을 찾아야 합니다.
입력:
N (이닝 수, 2 ≤ N ≤ 50)
각 이닝에서 9명의 선수의 기록 (0~4)
출력:
최대 점수를 출력합니다.
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
    const N = parseInt(input[0]); // 이닝 수
    const innings = input.slice(1).map(line => line.split(" ").map(Number)); // 각 선수 기록

    // 순열 생성 함수
    function getPermutations(array) {
        if (array.length === 1) return [array];
        const result = [];
        for (let i = 0; i < array.length; i++) {
            const rest = array.slice(0, i).concat(array.slice(i + 1));
            const perms = getPermutations(rest);
            perms.forEach(perm => {
                result.push([array[i], ...perm]);
            });
        }
        return result;
    }

    // 가능한 타순 생성 (1번 선수는 무조건 4번 타자)
    function generateLineups() {
        const players = [2, 3, 4, 5, 6, 7, 8, 9]; // 나머지 선수들
        const permutations = getPermutations(players);
        return permutations.map(perm => [...perm.slice(0, 3), 1, ...perm.slice(3)]);
    }

    const lineups = generateLineups();
    let maxScore = 0;

    // 점수 계산 함수
    function calculateScore(lineup) {
        let score = 0;
        let currentBatterIndex = 0;

        for (let i = 0; i < N; i++) {
            const inning = innings[i];
            let outs = 0;
            const bases = [false, false, false]; // 1루, 2루, 3루 상태

            while (outs < 3) {
                const result = inning[lineup[currentBatterIndex] - 1];
                currentBatterIndex = (currentBatterIndex + 1) % 9;

                if (result === 0) {
                    // 아웃
                    outs++;
                } else {
                    // 안타, 2루타, 3루타, 홈런 처리
                    let runs = 0;
                    for (let b = 2; b >= 0; b--) {
                        if (bases[b]) {
                            if (b + result >= 3) {
                                runs++;
                                bases[b] = false;
                            } else {
                                bases[b + result] = true;
                                bases[b] = false;
                            }
                        }
                    }
                    if (result >= 4) {
                        runs++; // 타자 홈런
                    } else {
                        bases[result - 1] = true; // 타자 진루
                    }
                    score += runs;
                }
            }
        }
        return score;
    }

    // 모든 타순에 대해 최대 점수 계산
    for (const lineup of lineups) {
        const score = calculateScore(lineup);
        maxScore = Math.max(maxScore, score);
    }

    console.log(maxScore); // 최대 점수 출력
}

solution(input);
