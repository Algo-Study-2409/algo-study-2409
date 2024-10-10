// https://www.acmicpc.net/problem/21940

/*
* **문제**
* 도시를 연결 하는 도로는 일방통행만 있다
* 고로 방향마다 시간이 다를 수 있다
* 왕복시간: X로 이동하는 시간 X에서 다시 고향으로 오는 시간의 합
* 도로만 이용 할 수 있다
* 왕복시간중 최대 최소가 되는 도시 X를 택한다
* 이동할 수 있는 도시는 최소 1개는 보장됨
*
* **입력**
* 1: 도시의 개수 N, 도로의 개수 M
* 2: M+1 줄까지 도시 A ~ 도시 B, 이동시간 T
* M+2: 총인원 K
* M+3: 준형이와 친구들이 살고 있는 도시번호 Ci가 공백으로 구분되어 주어짐
*
* 도로정보가 A, B ,T 이렇게 이루어짐 A -> B , T 초가 소요된다
* 마지막줄은 각각 1,2에 살고있다 뭐 이런뜻임
* */


/*
* 현성준 : BOJ 21940 가운데서 만나기
* - 플로이드 워셜로 모든 경로를 계산
* - 각 도시 n에 대해서 모든 친구들이 그 도시에 모였을 때의 왕복 시간 계산
* - 가장 작은 최대 왕복 시간을 가진 도시를 찾음
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const [N, M] = input[0].split(" ").map(Number);  // 첫 번째 줄: 도시의 개수 N과 도로의 개수 M
    const arr = Array.from({length: N + 1}, () => new Array(N + 1).fill(Infinity));  // N x N 배열을 Infinity로 초기화

    // M개의 도로 정보 입력 처리
    for (let m = 1; m <= M; m++) {
        const [a, b, v] = input[m].split(" ").map(Number);
        arr[a][b] = v;  // 도시 a에서 b로 가는 시간 v
    }

    const K = +input[M + 1];  // 친구들의 수 K
    const house = input[M + 2].split(" ").map(Number);  // 친구들이 사는 도시 정보

    // 플로이드-워셜 알고리즘 적용
    for (let g = 1; g <= N; g++) {  // 중간 노드 g를 거쳐가는 모든 경로를 계산
        for (let i = 1; i <= N; i++) {  // 출발 도시 i
            for (let j = 1; j <= N; j++) {  // 도착 도시 j
                if (i === j) {
                    arr[i][j] = 0;  // 자기 자신으로 가는 경로는 0
                    continue;
                }
                // 더 짧은 경로가 있으면 갱신
                if (arr[i][g] + arr[g][j] < arr[i][j]) {
                    arr[i][j] = arr[i][g] + arr[g][j];
                }
            }
        }
    }

    let min = Infinity;  // 최소 왕복 시간을 기록하기 위한 변수
    let answer = [];  // 답이 될 도시들을 저장할 배열

    // 각 도시 n에 대해서 모든 친구들이 그 도시에 모였을 때의 왕복 시간 계산
    for (let n = 1; n <= N; n++) {
        let max = 0;  // 각 도시에서 친구들이 모였을 때의 최대 왕복 시간을 기록

        // 각 친구의 왕복 시간을 계산
        for (let h = 0; h < K; h++) {
            // 친구가 사는 도시 house[h]에서 n번 도시까지 가는 왕복 시간
            const roundTripTime = arr[house[h]][n] + arr[n][house[h]];

            // 그 중에서 가장 큰 왕복 시간을 찾음
            if (max < roundTripTime) {
                max = roundTripTime;
            }
        }

        // 가장 작은 최대 왕복 시간을 가진 도시를 찾음
        if (min > max) {
            min = max;
            answer = [n];  // 새로운 최소값이 생기면 answer를 초기화하고 그 도시 n을 저장
        } else if (min === max) {
            answer.push(n);  // 최소값과 동일한 도시가 나오면 추가
        }
    }

    console.log(answer.sort((a, b) => a - b).join(" "));  // 답을 오름차순으로 출력
}