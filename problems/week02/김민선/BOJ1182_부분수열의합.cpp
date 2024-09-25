#include<bits/stdc++.h>
using namespace std;

/*
  - 4ms
  - 백트래킹
  - i) 부분수열의 합이 S가 되는 경우의 수를 구함
  - ii) 공집합을 제외하고 출력
*/

int N, S;
int ans = 0;
vector<int> arr(20, 0);

void dfs(int idx, int sum) {
  if(idx == N) {
    if(sum == S)
      ans++;
    return;
  }

  dfs(idx + 1, sum + arr[idx]);
  dfs(idx + 1, sum);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  cin >> N >> S;

  for(int i = 0; i < N; i++) {
    cin >> arr[i];
  }

  dfs(0, 0);

  // 공집합 제외
  if(S == 0) {
    ans--;
  }

  cout << ans << "\n";
}