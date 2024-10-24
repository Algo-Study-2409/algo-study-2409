// https://www.acmicpc.net/problem/1245

/*
* N*M 격자, 산봉우리가 몇개인가
* 산봉우리는 같은 높이를 가지는 하나의 격자 혹은 인접한 격자
* 인접하다는 X좌표 차이와 Y좌표 차이가 1 이하
* 산봉우리와 인접한 격자는 모두 산봉우리의 높이보다 작아야한다
*
* 현성준 : BOJ 1245 농장 관리
* - 8방향 탐색
* - DFS 현재 칸과 인접한 같은 높이의 칸을 탐색
*
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const [N, M] = input[0].split(" ");
    const grid = input.slice(1).map(line => line.split(' ').map(Number));

    const directions = [[1, 0], [-1, 0], [0, 1], [0, -1], [1, 1], [-1, -1], [1, -1], [-1, 1]];
    const visited = Array.from({length: N}, () => Array(M).fill(false));
    let peakCount = 0;

    // DFS 함수 정의
    function dfs(x, y) {
        let isPeak = true;
        const stack = [[x, y]];
        const sameHeightCells = [[x, y]];
        visited[x][y] = true;
        while (stack.length > 0) {
            const [cx, cy] = stack.pop();
            for (let [dx, dy] of directions) {
                const nx = cx + dx;
                const ny = cy + dy;

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (grid[nx][ny] > grid[x][y]) {
                        isPeak = false;
                    }
                    if (!visited[nx][ny] && grid[nx][ny] === grid[x][y]) {
                        visited[nx][ny] = true;
                        stack.push([nx, ny]);
                        sameHeightCells.push([nx, ny]);
                    }
                }
            }
        }


        return isPeak;

    }

    for (let i = 0; i < N; i++) {
        for (let j = 0; j < M; j++) {
            if (!visited[i][j]) {
                if (dfs(i, j)) {
                    peakCount++;
                }
            }
        }
    }
    console.log(peakCount);
}
