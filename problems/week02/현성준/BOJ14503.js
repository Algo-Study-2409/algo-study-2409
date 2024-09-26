// https://www.acmicpc.net/problem/14503

/*
*문제*
서쪽 칸 0,0
동쪽칸 N-1,M-1

*입력*
첫째줄 : 방의크기 N M
둘째줄 : 로봇청소기가 있는 칸의 좌표 r,c 청소기가 바라보느 방향 d (0북, 1동, 2남, 3서,)
셋째줄 : 맵 
*/

/*
현성준 : BOJ 14503 로봇 청소기 
- 104ms
*/
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
solution(input);
function solution(input) {
  // 1. 첫 번째 줄: 방의 크기 N과 M
  const [N, M] = input[0].split(" ").map(Number);

  // 2. 두 번째 줄: 로봇 청소기의 초기 위치와 방향
  let [r, c, d] = input[1].split(" ").map(Number);

  // 3. 세 번째 줄부터: 방의 상태 (벽, 빈 칸)
  const room = [];
  for (let i = 2; i < 2 + N; i++) {
    room.push(input[i].split(" ").map(Number));
  }

  // 4. 방향에 따른 좌표 변화 (북, 동, 남, 서)
  const directions = [
    [-1, 0], // 북쪽 (d = 0)
    [0, 1], // 동쪽 (d = 1)
    [1, 0], // 남쪽 (d = 2)
    [0, -1], // 서쪽 (d = 3)
  ];

  // 5. 청소된 칸의 개수를 세는 변수
  let cleanedCount = 0;

  // 6. 현재 칸 청소하는 함수
  const cleanRoom = () => {
    if (room[r][c] === 0) {
      // 아직 청소되지 않은 칸이면
      room[r][c] = 2; // 청소 완료 (2로 표시)
      cleanedCount++; // 청소한 칸의 개수를 증가
    }
  };

  // 7. 시작할 때 현재 칸을 청소
  cleanRoom();

  // 8. 반시계 방향으로 90도 회전하는 함수
  const turnLeft = () => {
    d = (d + 3) % 4; // 반시계 방향 회전 (0 -> 3 -> 2 -> 1 -> 0 순으로)
    // 음수를 방지하기위해 +3을 넣어줌
  };

  // 9. 후진 처리 함수
  const goBack = () => {
    const backDirection = (d + 2) % 4; // 현재 방향의 반대 방향으로 후진
    const [backDx, backDy] = directions[backDirection]; // 후진 방향으로 이동할 좌표 계산
    const backX = r + backDx;
    const backY = c + backDy;

    // 후진할 수 있으면 후진
    if (
      backX >= 0 &&
      backX < N &&
      backY >= 0 &&
      backY < M &&
      room[backX][backY] !== 1
    ) {
      r = backX;
      c = backY;
      return true; // 후진 성공
    }
    return false; // 후진 불가능
  };

  // 10. 반복적으로 청소 로직을 수행하는 부분
  while (true) {
    let foundNewPlace = false;

    // 11. 4방향 탐색 (왼쪽으로 돌면서 청소할 수 있는 빈 칸 찾기)
    for (let i = 0; i < 4; i++) {
      turnLeft(); // 왼쪽으로 회전
      const [dx, dy] = directions[d]; // 현재 회전한 방향에 따른 좌표 변화
      const nx = r + dx;
      const ny = c + dy;

      // 12. 그 방향에 청소하지 않은 빈 칸이 있으면 이동하여 청소
      if (nx >= 0 && nx < N && ny >= 0 && ny < M && room[nx][ny] === 0) {
        r = nx; // 새로운 위치로 이동
        c = ny;
        cleanRoom(); // 이동한 곳 청소
        foundNewPlace = true; // 새로운 청소할 곳을 찾음
        break; // 청소할 곳을 찾았으면 다시 4방향 탐색 시작
      }
    }

    // 13. 4방향 모두 청소할 곳이 없는 경우 후진 시도
    if (!foundNewPlace) {
      if (!goBack()) {
        // 후진할 수 없으면 작동 멈춤
        break;
      }
    }
  }

  // 14. 청소한 칸의 개수를 출력
  console.log(cleanedCount);
}
