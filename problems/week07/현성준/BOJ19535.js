// https://www.acmicpc.net/problem/19535
// https://glanceyes.com/entry/BOJ-백준-19535번-ㄷㄷㄷㅈ
// https://loosie.tistory.com/519

/*
입력 처리 및 그래프 초기화:
트리의 정점 수 N과 각 정점의 연결 정보를 incidenceList 배열에 저장합니다.
모든 간선은 edges 배열에 저장됩니다.
G 개수 계산:
각 정점마다 solveG 함수를 통해 "ㅈ" 모양이 가능한 경우를 조합으로 계산합니다.
combination 함수는 주어진 두 수 n, r로 조합을 계산하는 역할을 합니다.
D 개수 계산:
D 개수는 각 간선마다 "ㄷ" 모양이 가능한 경우를 계산하여 cntD에 더합니다.
출력 조건:
cntD와 cntG의 3배를 비교해 D, G, DUDUDUNGA 중 하나를 출력합니다.

현성준 : BOJ 19535 ㄷㄷㄷㅈ
- ㅈ ㄷ 모양 구해서 합을 구함
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const N = parseInt(input[0]);
    const incidenceList = Array.from({ length: N + 1 }, () => []);
    const edges = [];
    let cntD = 0, cntG = 0;

    for (let i = 1; i < N; i++) {
        const [u, v] = input[i].split(" ").map(Number);
        incidenceList[u].push(v);
        incidenceList[v].push(u);
        edges.push([u, v]);
    } // 여기까진 어느정도 패턴인듯

    function calculateG(vertex) {
        const childCount = incidenceList[vertex].length;
        // 연결된 노드의 수가 3개 이상일 경우에만 처리
        return (childCount >= 3) ? combination(childCount, 3) : 0;
    }

    function combination(n, r) {
        if (n < r) return 0; // n이 r보다 작다면 조합을 계산할 수 없으므로 0을 반환
        let result = 1; // 조합 결과를 저장하는 변수, 초기값은 1

        // r번 반복하며 n! / r! 부분을 계산
        for (let i = 0; i < r; i++) {
            result *= (n - i); // 분자 계산: (n-i) 값을 곱하여 분자 생성
            result /= (i + 1); // 분모 계산: (i+1) 값을 나눠서 r! 값으로 나눔
        }

        return result; // 계산된 조합의 결과 반환
    }

    for (let i = 1; i <= N; i++) {
        cntG += calculateG(i);
    }

    for (const [u, v] of edges) {
        cntD += (incidenceList[u].length - 1) * (incidenceList[v].length - 1); // ㄷ자 구하기
    }

    if (cntD > cntG * 3) {
        console.log("D");
    } else if (cntD < cntG * 3) {
        console.log("G");
    } else {
        console.log("DUDUDUNGA");
    }
}
