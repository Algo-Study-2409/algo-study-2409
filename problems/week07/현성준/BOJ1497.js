// https://www.acmicpc.net/problem/1497

/*
*
이진 수로 곡을 표현하기:
각 기타가 연주할 수 있는 곡을 Y와 N으로 표시합니다. 이때 Y는 연주할 수 있는 곡이고 N은 연주할 수 없는 곡입니다.
예를 들어 곡이 5개이고 특정 기타가 YYYNN로 표시되었다면, 이 기타가 연주할 수 있는 곡 상태를 이진수(11100)로 나타낼 수 있습니다.
조합을 통한 최소 기타 찾기:
모든 기타의 조합을 만들어 가능한 모든 곡을 커버하는 최소한의 기타 세트를 찾습니다.
예를 들어, 곡이 5개라면 11111이라는 이진수가 만들어지면 모든 곡을 연주할 수 있다는 뜻입니다.
최소 기타 수 계산:
모든 기타 조합을 확인하면서, 각 조합이 모든 곡을 커버하는지 체크합니다.
모든 곡을 커버하는 조합이 여러 개라면 그중에서 기타 수가 가장 적은 조합을 찾습니다.

현성준 : BOJ 1497 기타콘서트
- 조합으로 최소 기타 찾기

* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const [N, M] = input[0].split(" ").map(Number); // N: 기타 수, M: 곡 수
    const guitars = input.slice(1).map(line => {
        const [name, playable] = line.split(" ");
        return playable.split("").map(song => song === 'Y' ? 1 : 0);
    });

    let minGuitars = N + 1; // 최소 기타 수 비교를 위한 초기 큰 값 설정
    let maxSongs = 0; // 연주 가능한 최대 곡 수

    // 모든 기타 조합을 비트마스크로 처리
    for (let i = 1; i < (1 << N); i++) {
        let songCoverage = Array(M).fill(0);
        let guitarCount = 0;

        for (let j = 0; j < N; j++) {  // 기타 개별 확인
            if (i & (1 << j)) { // 해당 기타가 조합에 포함되는지 확인
                guitarCount++;
                for (let k = 0; k < M; k++) {
                    songCoverage[k] |= guitars[j][k];
                }
            }
        }

        // 각 곡의 연주 가능 여부 계산
        let playableSongs = songCoverage.reduce((acc, song) => acc + song, 0);

        // 최대 곡 수를 달성하고 최소 기타로 구성하는 조합 찾기
        if (playableSongs > maxSongs || (playableSongs === maxSongs && guitarCount < minGuitars)) {
            maxSongs = playableSongs;
            minGuitars = guitarCount;
        }
    }

    console.log(maxSongs === 0 ? -1 : minGuitars);
}
