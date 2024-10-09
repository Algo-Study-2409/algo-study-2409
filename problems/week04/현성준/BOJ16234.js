// https://www.acmicpc.net/problem/16234

/*
* 현성준 : BOJ 16234 인구 이동
* - 모든 국경이 닫혔는지 확인
* - bfs를 통해 국경이 열리는 도시 찾기
* - 전체 보드를 순회하며 인구 이동 그룹 탐색
* */
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const [N, L, R] = input.shift().split(" ").map(Number);
    const board = input.map((row) => row.split(" ").map(Number));

    const dy = [-1, 1, 0, 0];
    const dx = [0, 0, -1, 1];

    const getIsVisited = () => {
        return Array.from({length: N}, () =>
            Array.from({length: N}, () => false),
        );
    };
// 모든 국경이 닫혔는지 확인
    const isEnd = (board, N, L, R) => {
        for (let i = 0; i < N; i++) {
            for (let j = 0; j < N; j++) {
                const currentVal = board[i][j];
                for (let k = 0; k < 4; k++) {
                    const newY = i + dy[k];
                    const newX = j + dx[k];
                    if (newY >= 0 && newX >= 0 && newY < N && newX < N) {
                        const newVal = board[newY][newX];
                        const absVal = Math.abs(currentVal - newVal);
                        if (absVal >= L && absVal <= R) return false;
                    }
                }
            }
        }
        return true;
    };
// bfs를 통해 국경이 열리는 도시 찾기
    const getPosArr = (board, visitArr, Y, X, N, L, R) => {
        const posArr = [[Y, X]];
        const queue = [[Y, X]];

        while (queue.length) {
            const [currentY, currentX] = queue.shift();

            for (let i = 0; i < 4; i++) {
                const newY = currentY + dy[i];
                const newX = currentX + dx[i];
                if (
                    newY >= 0 &&
                    newX >= 0 &&
                    newY < N &&
                    newX < N &&
                    !visitArr[newY][newX]
                ) {
                    const newVal = Math.abs(board[currentY][currentX] - board[newY][newX]);
                    if (newVal >= L && newVal <= R) {
                        visitArr[newY][newX] = true;
                        queue.push([newY, newX]);
                        posArr.push([newY, newX]);
                    }
                }
            }
        }
        return posArr;
    };
// 전체 보드를 순회하며 인구 이동 그룹 탐색
    const boardScan = (board, N, L, R) => {
        const Groups = [];
        const isVisited = getIsVisited();
        for (let i = 0; i < N; i++) {
            for (let j = 0; j < N; j++) {
                if (!isVisited[i][j]) {
                    isVisited[i][j] = true;
                    const posArr = getPosArr(board, isVisited, i, j, N, L, R);
                    if (posArr.length > 1) Groups.push(posArr);
                }
            }
        }
        return Groups;
    };

    const move = (board, Groups) => {
        for (let posArr of Groups) {
            let sum = 0;
            for (let pos of posArr) {
                sum += board[pos[0]][pos[1]];
            }
            const newVal = Math.floor(sum / posArr.length);
            for (let pos of posArr) {
                board[pos[0]][pos[1]] = newVal;
            }
        }
        return board;
    };

    let timer = 0;
    while (true) {
        if (isEnd(board, N, L, R)) break;
        timer++;
        const groups = boardScan(board, N, L, R);
        move(board, groups);
    }
    console.log(timer);
}
