// https://www.acmicpc.net/problem/1325

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

solution(input);

function solution(input) {
    const [N, M] = input[0].split(" ").map(Number); // N: 컴퓨터의 수, M: 관계의 수
    const graph = Array.from({ length: N + 1 }, () => []); // 인접 리스트 초기화
    let answer = [];
    let max = 0; // 해킹 가능한 컴퓨터의 최대 수

    // 인접 리스트 구성
    for (let i = 1; i <= M; i++) {
        const [a, b] = input[i].split(" ").map(Number);
        graph[b].push(a); // B가 A를 해킹할 수 있으므로, 역방향으로 저장
    }

    // DFS 함수
    const DFS = (start) => {
        const stack = [start];
        const visited = Array(N + 1).fill(false); // 방문 배열 초기화
        let count = 0; // 해킹 가능한 컴퓨터 수 카운트

        while (stack.length) {
            const cur = stack.pop();
            if (!visited[cur]) {
                visited[cur] = true; // 현재 노드 방문 처리
                count++; // 해킹 가능한 컴퓨터 수 증가
                for (const next of graph[cur]) {
                    if (!visited[next]) {
                        stack.push(next); // 다음 노드 탐색
                    }
                }
            }
        }

        return count; // 현재 컴퓨터로부터 해킹 가능한 컴퓨터 수 반환
    };

    // 모든 컴퓨터에 대해 DFS 수행
    for (let i = 1; i <= N; i++) {
        const result = DFS(i); // i번 컴퓨터에서 해킹 가능한 컴퓨터 수 계산

        if (result > max) {
            max = result; // 최대 해킹 가능한 컴퓨터 수 갱신
            answer = [i]; // 새로운 최대값이 나왔으므로 초기화 후 저장
        } else if (result === max) {
            answer.push(i); // 최대 해킹 수와 같은 컴퓨터를 추가
        }
    }

    console.log(answer.join(" "));
}
