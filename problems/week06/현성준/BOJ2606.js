// https://www.acmicpc.net/problem/2606

/*
* 현성준 : BOJ 2606 바이러스
* - 컴퓨터들 인접리스트로 만들기
* - BFS 사용하여 탐색
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const numComputers = parseInt(input[0]);  // 컴퓨터 수
    const numConnections = parseInt(input[1]);  // 네트워크 상에서 직접 연결된 쌍의 수

    const graph = Array.from({ length: numComputers + 1 }, () => []);  // 인접 리스트 생성
    const visited = Array(numComputers + 1).fill(false);  // 방문 체크 배열

    // 네트워크 연결 정보 저장 (무향 그래프)
    for (let i = 2; i < 2 + numConnections; i++) {
        const [a, b] = input[i].split(' ').map(Number);
        graph[a].push(b);
        graph[b].push(a);
    }

    // BFS 탐색 함수 정의
    function bfs(start) {
        const queue = [start];
        visited[start] = true;
        let infectedCount = 0;

        while (queue.length > 0) {
            const current = queue.shift();

            for (const neighbor of graph[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.push(neighbor);
                    infectedCount++;
                }
            }
        }

        return infectedCount;
    }

    // 1번 컴퓨터에서 BFS 탐색 시작
    const result = bfs(1);

    // 결과 출력
    console.log(result);
}
