const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const [n, m] = input[0].split(" ").map(Number);  // n: 구슬의 개수, m: 관계의 수
  const heavyGraph = Array.from({ length: n + 1 }, () => []);
  const lightGraph = Array.from({ length: n + 1 }, () => []);

  // 관계 설정
  for (let i = 1; i <= m; i++) {
    const [a, b] = input[i].split(" ").map(Number);
    heavyGraph[a].push(b); // a가 b보다 무겁다.
    lightGraph[b].push(a); // b가 a보다 가볍다.
  }

  const half = Math.floor(n / 2);

  // DFS 또는 BFS 탐색으로 특정 구슬에서 도달할 수 있는 구슬 개수를 셈
  function countNodes(graph, start) {
    const visited = Array(n + 1).fill(false);
    let count = 0;
    const stack = [start];
    visited[start] = true;

    while (stack.length) {
      const node = stack.pop();

      for (const next of graph[node]) {
        if (!visited[next]) {
          visited[next] = true;
          stack.push(next);
          count++;
        }
      }
    }

    return count;
  }

  let result = 0;

  // 각 구슬마다 무거운 구슬 개수와 가벼운 구슬 개수를 계산
  for (let i = 1; i <= n; i++) {
    const heavyCount = countNodes(heavyGraph, i);  // i보다 무거운 구슬의 수
    const lightCount = countNodes(lightGraph, i);  // i보다 가벼운 구슬의 수

    // 절반 이상이 무거운 구슬이거나 절반 이상이 가벼운 구슬일 경우
    if (heavyCount > half || lightCount > half) {
      result++;
    }
  }

  console.log(result);
}

solution(input);
// 잘모르겠슴..
// G선생님..
// N 구슬의 개수 M 구슬의 관계 수 

