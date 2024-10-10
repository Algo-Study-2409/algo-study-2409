// https://www.acmicpc.net/problem/1012

/*
* 현성준 : BOJ 1012 유기농 배추
* - dfs 사용하여 농장 순회
* */
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const T = +input.shift();  // 테스트 케이스 수
    const inputArr = input.map((v) => v.split(" ").map(Number));

    let farm = [];
    let visited = [];

    // 상하좌우 이동을 위한 배열
    const row = [0, 0, -1, 1];
    const col = [-1, 1, 0, 0];

    // DFS 함수 정의
    const dfs = (j, k, N, M) => {
        visited[j][k] = true;
        for (let i = 0; i < 4; i++) {
            const newJ = j + col[i];
            const newK = k + row[i];
            if (newJ >= 0 && newJ < N && newK >= 0 && newK < M) {
                if (farm[newJ][newK] === 1 && !visited[newJ][newK]) {
                    dfs(newJ, newK, N, M);
                }
            }
        }
    };

    for (let i = 0; i < T; i++) {
        // 농장 정보와 방문 배열 초기화
        let answer = 0;
        const [M, N, K] = inputArr.shift();  // M: 가로, N: 세로, K: 배추 위치 개수
        farm = Array.from(Array(N), () => new Array(M).fill(0));
        visited = Array.from(Array(N), () => new Array(M).fill(false));

        // 배추가 있는 위치에 표시
        for (let k = 0; k < K; k++) {
            const [x, y] = inputArr.shift();
            farm[y][x] = 1;
        }

        // DFS를 사용하여 농장을 순회
        for (let j = 0; j < N; j++) {
            for (let k = 0; k < M; k++) {
                if (farm[j][k] === 1 && !visited[j][k]) {
                    dfs(j, k, N, M);
                    answer++;
                }
            }
        }
        console.log(answer);  // 정답 출력
    }
}
