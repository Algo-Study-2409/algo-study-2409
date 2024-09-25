#include<bits/stdc++.h>
using namespace std;

/*
  - 40 ms
  - 구현
  - 미세먼지 확산, 공기청정기 작동을 구현
  - 미세먼지 확산: 현재 배열을 기준으로 다음 배열을 만들어야 하므로, cur_arr, next_arr 두 개의 배열을 사용
  - 공기청정기 작동: 위쪽은 반시계 방향, 아래쪽은 시계 방향으로 미세먼지를 이동시킴
*/

vector<vector<int>> *cur_arr;
vector<vector<int>> *next_arr;
int R, C, T;
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};
pair<int, int> cleaner = {-1, -1}; // 공기 청정기 왼쪽 위 좌표


void spread() {
    for(int y = 0; y < R; y++) {
        for(int x = 0; x < C; x++) {
            if((*cur_arr)[y][x] == -1) continue;
            if((*cur_arr)[y][x] < 5) {
                (*next_arr)[y][x] += (*cur_arr)[y][x];
                continue;
            }
            int cnt = 0;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= C || ny < 0 || ny >= R) continue;
                if((*cur_arr)[ny][nx] == -1) continue;
                cnt++;
                (*next_arr)[ny][nx] += (*cur_arr)[y][x] / 5;
            }
            (*next_arr)[y][x] += (*cur_arr)[y][x] - ((*cur_arr)[y][x] / 5) * cnt;
        }
    }
}

void clean() {
  // 위쪽
  // 왼 -> 위 -> 오 -> 아
  for(int y = cleaner.first - 1; y > 0; y--) {
    (*next_arr)[y][0] = (*next_arr)[y - 1][0];
  }

  for(int x = 0; x < C - 1; x++) {
    (*next_arr)[0][x] = (*next_arr)[0][x + 1];
  }

  for(int y = 0; y < cleaner.first; y++) {
    (*next_arr)[y][C - 1] = (*next_arr)[y + 1][C - 1];
  }

  for(int x = C - 1; x > 1; x--) {
    (*next_arr)[cleaner.first][x] = (*next_arr)[cleaner.first][x - 1];
  }
  (*next_arr)[cleaner.first][1] = 0;

  // 아래쪽
  // 왼 -> 아 -> 오 -> 위
  for(int y = cleaner.second + 1; y < R - 1; y++) {
    (*next_arr)[y][0] = (*next_arr)[y + 1][0];
  }

  for(int x = 0; x < C - 1; x++) {
    (*next_arr)[R - 1][x] = (*next_arr)[R - 1][x + 1];
  }

  for(int y = R - 1; y > cleaner.second; y--) {
    (*next_arr)[y][C - 1] = (*next_arr)[y - 1][C - 1];
  }

  for(int x = C - 1; x > 1; x--) {
    (*next_arr)[cleaner.second][x] = (*next_arr)[cleaner.second][x - 1];
  }
  (*next_arr)[cleaner.second][1] = 0;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  
  cin >> R >> C >> T;
  cur_arr = new vector<vector<int>>(R, vector<int>(C, 0));
  next_arr = new vector<vector<int>>(R, vector<int>(C, 0));

  for(int y = 0; y < R; y++) {
    for(int x = 0; x < C; x++) {
      cin >> (*cur_arr)[y][x];
      if((*cur_arr)[y][x] == -1) {
        if(cleaner.first == -1) {
          cleaner = {y, y + 1};
        }
      }
    }
  }

  (*next_arr)[cleaner.first][0] = -1;
  (*next_arr)[cleaner.second][0] = -1;

  while(T--) {
    spread();
    clean();
    delete cur_arr;
    cur_arr = next_arr;
    next_arr = new vector<vector<int>>(R, vector<int>(C, 0));
    (*next_arr)[cleaner.first][0] = -1;
    (*next_arr)[cleaner.second][0] = -1;
  }

  int ans = 0;
  for(int y = 0; y < R; y++) {
    for(int x = 0; x < C; x++) {
      if((*cur_arr)[y][x] <= 0) continue;
      ans += (*cur_arr)[y][x];
    }
  }

  cout << ans << "\n";
}