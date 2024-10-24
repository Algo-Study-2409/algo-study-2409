// https://www.acmicpc.net/problem/27924

/*
* 달구는 윤이를 잡기위해 출동함
* UDP 마을은 N개의 정점을 가진 트리 구조 트리는 N-1개의 간선이며 정점이 하나 뿐인걸 리프노드라고 부름
* 트리의 각 정점에 1...N 윤이는 정점 a에서 출발, 달구와 포닉스는 각각 b,c에서 출발
* 이동 도중 한정점에 둘이상 존재 가능
* 현성준 : BOJ 27924 윤이는 엄청난 것을 훔쳐갔습니다
* - BFS로 최단거리 구하기
* - 리프노드 확인
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const N = parseInt(input[0]);
    const edges = input.slice(1, N).map(line => line.split(' ').map(Number));
    const [a, b, c] = input[N].split(' ').map(Number);

    const graph = Array.from({ length: N + 1 }, () => []);
    const degree = Array(N + 1).fill(0);
    const dist = [Array(N + 1).fill(0), Array(N + 1).fill(0), Array(N + 1).fill(0)];

    // 트리 구조 인접 리스트로 저장
    for (const [u, v] of edges) {
        graph[u].push(v);
        graph[v].push(u);
        degree[u]++;
        degree[v]++;
    }

    // BFS로 특정 위치에서 각 노드까지의 최단 거리를 계산하는 함수
    function bfs(start, idx) {
        const distance = dist[idx];
        const queue = [start];
        distance[start] = 1;  // 시작점을 1로 설정 (0은 방문 안 한 상태로 구분하기 위함)

        while (queue.length > 0) {
            const current = queue.shift();

            for (const neighbor of graph[current]) {
                if (!distance[neighbor]) {
                    distance[neighbor] = distance[current] + 1;
                    queue.push(neighbor);
                }
            }
        }
    }

    // 윤이, 달구, 포닉스 각각의 위치에서 BFS 실행
    bfs(a, 0);  // 윤이의 위치
    bfs(b, 1);  // 달구의 위치
    bfs(c, 2);  // 포닉스의 위치

    // 리프 노드를 찾아서, 윤이가 경찰보다 먼저 도착할 수 있는지 확인
    let canEscape = false;
    for (let i = 1; i <= N; i++) {
        if (degree[i] === 1) {  // 리프 노드일 경우
            if (dist[0][i] < dist[1][i] && dist[0][i] < dist[2][i]) {
                canEscape = true;
                break;
            }
        }
    }

    console.log(canEscape ? "YES" : "NO");
}
