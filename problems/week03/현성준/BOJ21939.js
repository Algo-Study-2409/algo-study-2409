// https://www.acmicpc.net/problem/21939

/*
* 현성준 : BOJ 21939 문제 추천 시스템 Version 1
* */
/*
* 문제번호, 난이도
* ** recommend **
* x = 1 가장 어려운 문제의 번호
* x = -1 가장 쉬운 문제의 번호
* 가장 쉬운 문제가 여러개라면 문제 번호가 작은것이 우선
* 추천 문제 리스트에 문제가 1개 이상 있을때만 주어짐 (if)
* ** add **
* 난이도가 L, 문제 번호 P 를 추가한다
* 추천 문제 리스트에 없는 P만 입력으로 주어짐
* 이전 추천 문제 리스트의 문제 번호가 다른 난이도로 다시 입력 가능
* ** solved **
* 추천 문제 리스트에서 문제번호 P를 제거한다
* 추천 문제 리스트에서 문제번호가 1개이상 있을때만 주어진다 (if)
*
* **입력**
* 첫째줄 : 문재의 개수 N
* 둘째줄부터 : N+1줄까지 문제번호 P, 난이도 L
* N+1줄부터 : N+2줄 입력될 명령문의 개수 M
* N+2 둘째줄 부터 : 명령문
*
*
* 문제와 난이도를 담을 배열 1
* 명령어 처리를 담을 배열 1
* 우선순위를 가진 함수 1
* solved 는 제거
* recommend 가장 어렵고 쉬운 문제난이도 저장 이 값을 출력하면 된다
*
* 인덱스 마지막 가져오는 방법 [length -1]
* */
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs").readFileSync(filePath).toString().split("\n");

const n = parseInt(input[0]);
const problem = Array.from({ length: 100010 }, () => new Set());
const score = new Array(100010).fill(0);
let low = 110,
    high = 0;

for (let i = 1; i <= n; i++) {
    const [p, l] = input[i].split(" ").map(Number);
    problem[l].add(p);
    score[p] = l;
    low = Math.min(low, l);
    high = Math.max(high, l);
}

const m = parseInt(input[n + 1]);
let answer = "";
for (let i = n + 2; i <= n + m + 1; i++) {
    const command = input[i].split(" ")[0];
    if (command === "recommend") {
        const x = Number(input[i].split(" ")[1]);
        if (x === -1) {
            let mn = 100010;
            for (const p of problem[low]) {
                mn = Math.min(mn, p);
            }
            answer += mn + "\n";
        } else {
            let mx = 0;
            for (const p of problem[high]) {
                mx = Math.max(mx, p);
            }
            answer += mx + "\n";
        }
    } else if (command === "add") {
        const [p, l] = input[i].split(" ").slice(1).map(Number);
        problem[l].add(p);
        score[p] = l;
        low = Math.min(low, l);
        high = Math.max(high, l);
    } else {
        const p = Number(input[i].split(" ")[1]);
        const l = score[p];
        problem[l].delete(p);
        if (problem[l].size === 0) {
            if (l === low) {
                while (low <= high && problem[low].size === 0) {
                    low++;
                }
            }
            if (l === high) {
                while (low <= high && problem[high].size === 0) {
                    high--;
                }
            }
        }
    }
}

console.log(answer);
