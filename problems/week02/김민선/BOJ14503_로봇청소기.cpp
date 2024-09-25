#include<bits/stdc++.h>
using namespace std;

/*
  - 0ms
  - 구현
  - i) 현재 위치를 청소한다.
  - ii) 현재 방향을 기준으로 반시계 방향으로 탐색하며 청소할 공간을 찾는다.
    - 청소할 공간이 없다면, 후진한다.
    - 후진할 수 없다면, 종료한다.
  - iii) 청소할 공간이 있다면, 해당 방향으로 이동하고 i)로 돌아간다.
*/

int N, M;
int r, c, d;
int dx[] = {0, 1, 0, -1};
int dy[] = {-1, 0, 1, 0};
vector<vector<int>> arr(51, vector<int>(51, 0));
vector<vector<int>> visited(51, vector<int>(51, 0));
int ans = 0;

void dfs(int x, int y, int d) {
  if(arr[y][x] == 0 && visited[y][x] == 0) {
    visited[y][x] = 1;
    ans++;
  }

  for(int i = 0; i < 4; i++) {
    int nd = (d + 3 - i) % 4; // 반시계 방향
    int nx = x + dx[nd];
    int ny = y + dy[nd];

    if(nx < 0 || nx >= M || ny < 0 || ny >= N) {
      continue;
    }

    if(arr[ny][nx] == 0 && visited[ny][nx] == 0) {
      dfs(nx, ny, nd);
      return;
    }
  }
  int nd = (d + 2) % 4; // 후진
  int nx = x + dx[nd];
  int ny = y + dy[nd];
  if(nx < 0 || nx >= M || ny < 0 || ny >= N) {
    return;
  }
  if(arr[ny][nx] == 1) {
    return;
  }
  dfs(nx, ny, d);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  cin >> N >> M;
  cin >> r >> c >> d;

  for(int y = 0; y < N; y++) {
    for(int x = 0; x < M; x++) {
      cin >> arr[y][x];
    }
  }

  dfs(c, r, d);

  cout << ans << "\n";
}