#include<bits/stdc++.h>
using namespace std;

vector<vector<int>> arr(101, vector<int>(101, 4));
vector<vector<int>> visited(101, vector<int>(101, 0));
int dx[] = {0, 1, 0, -1};
int dy[] = {1, 0, -1, 0};

int w, h;

queue<pair<int, int>> side;
queue<pair<int, int>> q;

/*
  - 0ms
  - BFS
  - 가장자리 치즈 찾아 녹이기
  - 가장자리 (0, 0)부터 BFS로 탐색)
    - 1. 치즈인 경우 side 큐에 넣기
    - 2. 공기인 경우 q 큐에 넣기
*/

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  cin >> h >> w;

  for (int y = 0; y < h; y++) {
    for (int x = 0; x < w; x++) {
      cin >> arr[y][x];
    }
  }
  
  int time = 0;
  int cnt; // 남은 치즈 개수
  while(1) {
    cnt = 0;
    q.push({0, 0});
    visited[0][0] = 1;

    while(!q.empty()) {
      int x, y;
      tie(x, y) = q.front();
      q.pop();
      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx < 0 || nx >= w || ny < 0 || ny >= h) {
          continue;
        }
        if (visited[ny][nx] == 1) {
          continue;
        }
        visited[ny][nx] = 1;
        if (arr[ny][nx] == 1) {
          side.push({nx, ny});
          cnt++;
          continue;
        } else {
          q.push({nx, ny});
        }
      }
    }

    while(!side.empty()) {
      int x, y;
      tie(x, y) = side.front();
      side.pop();
      arr[y][x] = 0;
    }

    time++;

    bool flag = false; // 치즈가 남아있는지 확인

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        visited[y][x] = 0;
        if (arr[y][x] == 1)
          flag = true;
      }
    }

    // 치즈가 다 녹았으면 종료
    if (!flag) {
      break;
    }
  }

  cout << time << '\n' << cnt << '\n';
}

