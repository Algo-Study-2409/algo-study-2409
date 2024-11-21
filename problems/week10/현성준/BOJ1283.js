// https://www.acmicpc.net/problem/1283
// 현성준 : BOJ 단축키 지정
/*
단축키를 의미하는 대표 알파벳
1. 하나의 옵션에 왼->오 첫글자가 이미 단축키로 지정된지 보고 없으면 지정
2. 모든 단어의 첫글자가 이미 지정되어 있다면 다음것을 하나봄
3. 대문자만 구분하다 없으면 소문자도 개의치않아야 한다
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);


function solution(input) {
    const N = parseInt(input[0]); // 옵션 개수
    const options = input.slice(1); // 옵션 문자열 배열
    const usedKeys = new Set(); // 이미 사용된 단축키를 저장

    const result = options.map(option => {
        const words = option.split(" "); // 옵션의 단어로 분리
        let found = false; // 단축키가 지정되었는지 여부
        let newOption = words.map(word => word); // 결과 옵션 초기화

        // 1. 각 단어의 첫 글자를 우선 확인
        for (let i = 0; i < words.length; i++) {
            const char = words[i][0].toLowerCase(); // 대소문자 무시
            if (!usedKeys.has(char)) {
                // 단축키로 지정되지 않은 경우
                usedKeys.add(char); // 단축키 등록
                newOption[i] = `[${words[i][0]}]` + words[i].slice(1); // 단축키로 설정
                found = true;
                break;
            }
        }

        // 2. 첫 글자로 지정되지 않았다면, 각 단어의 다른 알파벳 확인
        if (!found) {
            for (let i = 0; i < words.length; i++) {
                for (let j = 0; j < words[i].length; j++) {
                    const char = words[i][j].toLowerCase(); // 대소문자 무시
                    if (!usedKeys.has(char)) {
                        // 단축키로 지정되지 않은 경우
                        usedKeys.add(char); // 단축키 등록
                        newOption[i] =
                            words[i].slice(0, j) + `[${words[i][j]}]` + words[i].slice(j + 1); // 단축키로 설정
                        found = true;
                        break;
                    }
                }
                if (found) break; // 단축키가 지정되었으면 중단
            }
        }

        // 3. 지정된 단축키를 포함한 새 옵션 문자열 반환
        return newOption.join(" ");
    });

    // 결과 출력
    console.log(result.join("\n"));
}
