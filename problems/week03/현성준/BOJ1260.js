// https://www.acmicpc.net/problem/1260
// https://velog.io/@ywc8851/백준-1260-DFS와-BFS-javascript
// https://velog.io/@sean2337/Algorithm-DFS와-BFS의-쉬운-개념-JavaScript-구현-방법

/*
* 현성준 : BOJ 1260 DFS와 BFS
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const [N, M, V] = input[0].split(' ').map(Number);  // N: 정점 개수, M: 간선 개수, V: 시작 정점
    const graph = Array.from({ length: N + 1 }, () => []);  // 인접 리스트로 그래프 초기화

    // 간선 정보 입력 받아서 그래프 생성
    for (let i = 1; i <= M; i++) {
        const [a, b] = input[i].split(' ').map(Number);
        graph[a].push(b);
        graph[b].push(a);
    }

    // 그래프 정점 번호 작은 순서대로 방문하기 위해 정렬
    for (let i = 1; i <= N; i++) {
        graph[i].sort((a, b) => a - b);
    }

    // DFS, BFS 결과를 저장할 배열
    const dfsResult = [];
    const bfsResult = [];

    // 방문 기록을 위한 배열
    const visitedDFS = Array(N + 1).fill(false);
    const visitedBFS = Array(N + 1).fill(false);

    // DFS 탐색
    function dfs(v) {
        visitedDFS[v] = true;
        dfsResult.push(v);

        for (const next of graph[v]) {
            if (!visitedDFS[next]) {
                dfs(next);
            }
        }
    }

    // BFS 탐색
    function bfs(v) {
        const queue = [v];
        visitedBFS[v] = true;

        while (queue.length > 0) {
            const node = queue.shift();
            bfsResult.push(node);

            for (const next of graph[node]) {
                if (!visitedBFS[next]) {
                    visitedBFS[next] = true;
                    queue.push(next);
                }
            }
        }
    }

    // DFS와 BFS 수행
    dfs(V);
    bfs(V);

    // 결과 출력
    console.log(dfsResult.join(' '));
    console.log(bfsResult.join(' '));
}