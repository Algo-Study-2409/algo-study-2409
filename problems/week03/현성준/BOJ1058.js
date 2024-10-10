// https://www.acmicpc.net/problem/1058

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);

function solution(input) {
    const N = +input[0];// 사람 수
    const friends = input.slice(1).map(line => line.split(""));
    let maxFriendsCount = 0;

    // 각 사람마다 2-친구 수를 계산
    for (let i = 0; i < N; i++) {
        let twoFriendsSet = new Set(); // 2- 친구를 중복없이 저장

        // 각 사람의 친구 목록 확인
        for (let j = 0; j < N; j++) {
            if (friends[i][j] === 'Y') { // i와 j가 친구이면
                twoFriendsSet.add(j); // j를 2-친구에 추가 함 직접 친구

                // j의 친구들도 확인하여 2-친구로 추가
                for(let k = 0; k < N; k++) {
                    if(friends[j][k] === 'Y' && k !== i) { // k가 j의 친구이고, i 자신이 아니면
                        twoFriendsSet.add(k); // k를 2-친구로 추가
                    }
                }
            }
        }
        // 현재 사람(i)의 2-친구 수 계산 후 최대값 갱신
        maxFriendsCount = Math.max(maxFriendsCount, twoFriendsSet.size);
    }
    // 가장 많은 2-친구의 수 출력
    console.log(maxFriendsCount);
}

// 유명한 사람을 구하는 방법 : 각 사람의 2-친구
// A가 또다른 사람 B의 2-친구 -> 두사람 친구, A와 C
// 한쪽만 팔로워 여도 친구이다  자기자신은 친구가 아니다

// 첫째줄 : 사람의수 N
// 둘째줄 N개의 줄에 각 사람이 친구이면 Y 아니면 N


