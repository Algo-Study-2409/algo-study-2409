const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
[N, ...input] = input;

const [n, m] = N.split(" ").map(Number);
const grid = input.map((line) => line.split(" ").map(Number));
const directions = [
  [1, 0], // 이게 아래라고? 아무리 생각해도 오른쪽인데 -> 행이 증가하는거라고 다시 설명해줌 그럼 납득이 됨
  [-1, 0],
  [0, 1],
  [0, -1],
];

function isInBounds(x, y) {
  return x >= 0 && x < n && y >= 0 && y < m; //범위를 정하는 것인가봄
}

function bfs() {
  // 외부 공기 노드를 탐색하기 위한 큐
  const queue = [[0, 0]];
  const visited = Array.from({ length: n }, () => Array(m).fill(false)); // 2차원 배열을 만듬
  visited[0][0] = true;

  while (queue.length) {
    const [x, y] = queue.shift();
    // 4방향 탐색
    for (const [dx, dy] of directions) {
      const nx = x + dx;
      const ny = y + dy;

      // 범위를 벗어나지 않고, 방문하지 않았으면
      if (isInBounds(nx, ny) && !visited[nx][ny]) {
        if (grid[nx][ny] === 0) {
          // 공기일 경우 큐에 추가하여 계속 탐색
          queue.push([nx, ny]);
        } else if (grid[nx][ny] === 1) {
          // 치즈일 경우 녹음
          grid[nx][ny] = 2;
        }
        visited[nx][ny] = true;
      }
    }
  }
} // 이게 어떻게 바깥 치즈부분인지 보장을 할 수 있는것?

function meltCheese() {
  let count = 0;
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      if (grid[i][j] === 2) {
        grid[i][j] = 0;
        count++;
      }
    }
  }
  return count;
}
function solution(input) {
  let time = 0;
  let lastCount = 0;

  while (true) {
    bfs();
    const melted = meltCheese();
    if (melted === 0) break;
    lastCount = melted;
    time++;
  }
  console.log(time);
  console.log(lastCount);
}
solution(input);
// 입력
// 가로 세로
// 치즈모양

// G선생님 도움 받음
