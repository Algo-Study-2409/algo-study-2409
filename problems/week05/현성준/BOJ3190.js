// https://www.acmicpc.net/problem/3190

/*
* 현성준 : BOJ 3190 뱀
* 
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    // 1. 입력값 파싱
    const n = Number(input[0]);  // 보드 크기 N
    const k = Number(input[1]);  // 사과의 개수 K
    const apples = input.slice(2, 2 + k);  // 사과의 위치 정보
    const infos = input.slice(3 + k).map(it => it.split(' '));  // 방향 변환 정보

    // 2. 보드 생성 및 사과 위치 설정
    const map = Array.from({ length: n }, () => Array.from({ length: n }, () => '.'));

    // 사과 위치를 'A'로 표시
    for (const apple of apples) {
        const [x, y] = apple.split(' ').map(Number);
        map[x - 1][y - 1] = 'A';  // 1-based index를 0-based로 변환
    }

    // 3. 뱀의 시작 위치 설정
    let time = 0;  // 시간
    let direction = 3;  // 초기 방향 (우: 3)
    const snake = [[0, 0]];  // 뱀의 위치를 저장하는 큐 (초기에는 (0, 0))
    map[0][0] = 'S';  // 뱀의 머리 시작 위치

    // 방향 배열 (상: 0, 좌: 1, 하: 2, 우: 3)
    const dx = [-1, 0, 1, 0];
    const dy = [0, -1, 0, 1];

    // 4. 게임 시뮬레이션
    while (true) {
        const [x, y] = snake.at(-1);  // 현재 뱀의 머리 위치
        const nx = x + dx[direction];  // 다음 머리 위치
        const ny = y + dy[direction];

        // 4.1 벽에 부딪히거나 자신의 몸에 부딪히면 게임 종료
        if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] === 'S') {
            console.log(time + 1);  // 게임 종료 시간 출력 (벽 또는 몸에 부딪혔을 때)
            return;
        }

        // 4.2 사과가 있으면 뱀 길이 증가 (꼬리는 움직이지 않음)
        if (map[nx][ny] === 'A') {
            map[nx][ny] = 'S';
            snake.push([nx, ny]);
        } else {
            // 4.3 사과가 없으면 꼬리 이동 (길이는 그대로)
            const [tailX, tailY] = snake.shift();
            map[tailX][tailY] = '.';
            map[nx][ny] = 'S';
            snake.push([nx, ny]);
        }

        time += 1;  // 시간 증가

        // 4.4 방향 전환
        if (infos.length > 0 && Number(infos[0][0]) === time) {
            const turn = infos.shift()[1];
            if (turn === 'D') {
                direction = (direction + 3) % 4;  // 오른쪽 90도 회전
            } else if (turn === 'L') {
                direction = (direction + 1) % 4;  // 왼쪽 90도 회전
            }
        }
    }
}

// 맵 출력 함수 (디버깅용)
function printMap(map, time) {
    console.log(`시간: ${time}`);
    for (let i = 0; i < map.length; i++) {
        console.log(map[i].join(' '));
    }
}
