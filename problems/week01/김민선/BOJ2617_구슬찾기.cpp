#include<bits/stdc++.h>
using namespace std;

map<int, set<int>> uppers;
map<int, set<int>> lowers;
int n, m;

/*
  - 4ms
  - 각 구슬에 대해 상위 구슬, 하위 구슬 탐색
  - 상위 구슬 혹은 하위 구슬의 개수가 n/2 이상이면 무게가 절대로 중간이 될 수 없음
*/
int main() {
  cin >> n >> m;
  for (int i = 0; i < m; i++) {
    int a, b;
    cin >> a >> b;
    uppers[b].insert(a);
    lowers[a].insert(b);
  }

  int ret = 0; // 무게가 절대로 중간이될 수 없는 구슬의 개수
  for (int i = 1; i <= n; i++) {
    int upper = 0;
    int lower = 0;
    queue<int> q;
    q.push(i);
    vector<bool> visited(n + 1, false);
    visited[i] = true;
    while (!q.empty()) {
      int cur = q.front();
      q.pop();
      for (int next : uppers[cur]) {
        if (!visited[next]) {
          visited[next] = true;
          q.push(next);
          upper++;
        }
      }
    }

    q.push(i);
    fill(visited.begin(), visited.end(), false);
    visited[i] = true;
    while (!q.empty()) {
      int cur = q.front();
      q.pop();
      for (int next : lowers[cur]) {
        if (!visited[next]) {
          visited[next] = true;
          q.push(next);
          lower++;
        }
      }
    }

    if (upper >= (n + 1) / 2 || lower >= (n + 1) / 2) {
      ret++;
    }
  }

  cout << ret;
}