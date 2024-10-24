// https://www.acmicpc.net/problem/1405

/*
* 4방향중 1개의 칸으로 이동한다
* 로봇이 같은 곳을 한번보다 많이 이동하지 않을떄 이동경로가 단순하다
*
* **입력**
* N, 동, 서, 남, 북
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const [N, E, W, S, N_dir] = input[0].split(" ").map(Number);
    // 확률을 퍼센트로 바꾸기
    const probability = [E / 100, W / 100, S / 100, N_dir / 100];

    // 동서남북 이동 방향 설정
    const dx = [1, -1, 0, 0];
    const dy = [0, 0, -1, 1];

    // 충분히 큰 크기의 방문 배열 (가운데서 시작하기 위해)
    let visited = Array.from({length: 30}, () => Array(30).fill(false));
    let answer = 0;

    // DFS 탐색 함수
    function dfs(x, y, steps, prob) {
        if (steps === N) {
            answer += prob;
            return;
        }
        for (let i = 0; i < 4; i++) {
            let nx = x + dx[i];
            let ny = y + dy[i];
            // console.log(nx, ny);
            if (!visited[nx][ny]) { //이미 방문한곳이 아니라면
                visited[nx][ny] = true;
                dfs(nx, ny, steps + 1, prob * probability[i]);
                visited[nx][ny];
                visited[nx][ny] = false;
            }
        }
    }

    visited[15][15] = true;
    dfs(15, 15, 0, 1);
    console.log(answer.toFixed(9));
}
