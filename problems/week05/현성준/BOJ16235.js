// https://www.acmicpc.net/problem/16235
/*
* 현성준 : BOJ 16235 나무 재테크
*
* */

const fs = require("fs");
const input = fs
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split("\n")
    .map((v) => v.split(" ").map(Number));

const [N, M, K] = input.shift();
const food = input.splice(0, N);
const tree = input;
let answer = 0;
const area = Array.from(Array(N), () =>
    Array.from(Array(N), () => {
        return { food: 5, tree: [], seed: 0 };
    })
);

// 초기 나무 추가
tree.forEach((v) => {
    const [x, y, z] = v;
    area[x - 1][y - 1].tree.push(z);
});

// 8방향 탐색
const dx = [0, 1, 0, -1, 1, -1, -1, 1];
const dy = [1, 0, -1, 0, -1, 1, -1, 1];

const simulateYear = () => {
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            let living_tree = [];
            let dead_tree = 0;
            area[i][j].tree.sort((a, b) => a - b);  // 나무 나이순 정렬
            while (area[i][j].tree.length > 0) {
                const tree = area[i][j].tree.shift();
                if (tree <= area[i][j].food) {
                    area[i][j].food -= tree;
                    living_tree.push(tree + 1);
                    if ((tree + 1) % 5 == 0) {
                        for (let d = 0; d < 8; d++) {
                            const nx = i + dx[d];
                            const ny = j + dy[d];
                            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                                area[nx][ny].seed++;
                            }
                        }
                    }
                } else {
                    dead_tree += Math.floor(tree / 2);
                }
            }
            area[i][j].food += food[i][j] + dead_tree;
            area[i][j].tree = living_tree;
        }
    }
};

const plantTree = () => {
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            while (area[i][j].seed > 0) {
                area[i][j].tree.unshift(1);  // 새로운 나무 추가
                area[i][j].seed--;
            }
        }
    }
};

// K년간 시뮬레이션
for (let i = 0; i < K; i++) {
    simulateYear();
    plantTree();
}

// 남은 나무 개수 계산
for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
        answer += area[i][j].tree.length;
    }
}

console.log(answer);
