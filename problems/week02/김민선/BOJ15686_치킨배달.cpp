#include<bits/stdc++.h>
using namespace std;

/*
  - 0ms
  - 백트래킹
  - i) 치킨집 중 M개를 선택하는 조합을 구함
    - M개 미만의 경우를 계산할 필요가 없음
    - 출력이 최소값이므로, M개를 선택하는 경우만 고려
  - ii) 각 집에서 선택된 치킨집까지의 거리를 구하고, 최소 거리를 구함
  - iii) 최소 거리의 합을 구하고, 최소값을 갱신
*/

int N, M;
int ans = 987654321;
vector<vector<int>> arr(51, vector<int>(51, 0));
vector<pair<int, int>> chickens;
vector<pair<int, int>> houses;
vector<vector<int>> chiken_distances;
vector<int> selected_chickens;

void dfs(int idx, int cnt) {
  if(cnt == M) {
    int sum = 0;
    for(int i = 0; i < houses.size(); i++) {
      int min_dist = 987654321;
      for(int j = 0; j < chickens.size(); j++) {
        if(selected_chickens[j] == 1) {
          min_dist = min(min_dist, chiken_distances[j][i]);
        }
      }
      sum += min_dist;
    }

    ans = min(ans, sum);
    return;
  }

  for(int i = idx; i < chickens.size(); i++) {
      selected_chickens[i] = 1;
      dfs(i + 1, cnt + 1);
      selected_chickens[i] = 0;
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  cin >> N >> M;

  for(int y = 0; y < N; y++) {
    for(int x = 0; x < N; x++) {
      cin >> arr[y][x];
      if(arr[y][x] == 1) {
        houses.push_back({y, x});
      } else if(arr[y][x] == 2) {
        chickens.push_back({y, x});
      }
    }
  }

  chiken_distances = vector<vector<int>>(chickens.size(), vector<int>(houses.size(), 0));
  selected_chickens = vector<int>(chickens.size(), 0);

  for(int i = 0; i < chickens.size(); i++) {
    for(int j = 0; j < houses.size(); j++) {
      chiken_distances[i][j] = abs(chickens[i].first - houses[j].first) + abs(chickens[i].second - houses[j].second);
    }
  }

  dfs(0, 0);
  
  cout << ans << '\n';
}