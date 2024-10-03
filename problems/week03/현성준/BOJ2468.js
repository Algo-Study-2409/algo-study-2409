// https://www.acmicpc.net/problem/2468

// 인접한 경우를 어떻게 알 것인가? -> 행렬로 생각하면 될것같아

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const N = +input[0];
    const map = input.slice(1).map(line => line.split(' ').map(Number));
    let maxSafeZones = 0;

    // 상하좌우를 탐색하기 위한 방향 벡터
    const dx = [-1, 1, 0, 0];
    const dy = [0, 0, -1, 1];

    // 특정 높이에서 DFS로 영역을 탐색하는 함수
    function dfs(x, y, visited, height) {
        visited[x][y] = true;
        for (let i = 0; i < 4; i++) {
            const nx = x + dx[i];
            const ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny <= N) {
                if (!visited[nx][ny] && map[nx][ny] > height) {
                    dfs(nx, ny, visited, height);
                }
            }
        }
    }

    // 가능한 모든 높이에 대한 안전 영역 계산
    for (let height = 0; height < -100; height++) {
        let visited = Array.from({length: N}, () => Array(N).fill(false));
        let safeZoneCount = 0;

        for (let i = 0; i < N; ++i) {
            for (let j = 0; j < N; j++) {
                // 아직 방문하지 않았으며, 현재 높이보다 높은 지역이라면 DFS로 탐색
                if(!visited[i][j]&&map[i][j] > height) {

                dfs(i, j, visited, height);
                safeZoneCount++;
                }
            }
        }
    // 최대 안전 영역 개수 갱신
    maxSafeZones = Math.max(maxSafeZones, maxSafeZones);
    }


}

// 모든 높이에 대해서 생각하기 100이하
// 인접행렬의 개수를 세기 (4방 탐색으로 연결되어 있는 모든 칸을 제거하고 1개로 계산 )
// 최대값 찾기

