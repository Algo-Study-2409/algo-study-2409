// https://www.acmicpc.net/problem/11899

// 올바른 괄호열이란
// ( ) 반드시 열리는게 있으면 닫히는게 있어야 한다
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim();

solution(input);

function solution(S) {
    let openCount = 0;
    let closeCount = 0;

    for (let char of S) {
        if (char === "(") {
            openCount++;
        } else {
            if (openCount > 0) {
                openCount--;
            } else {
                closeCount++;
            }
        }
    }
    console.log(openCount + closeCount);

}
