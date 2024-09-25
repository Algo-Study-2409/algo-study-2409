#include <bits/stdc++.h>
using namespace std;

/*
  - 28ms
  - 이분 탐색
  - 각 cuts[i]에 대해 이진 탐색으로 최대 최소 길이를 찾음
    - 최소 길이로 cuts번 자를 수 있는지 확인하는 함수
    - 최소 길이를 찾는 함수(이분 탐색)
*/

int N, M, L;
vector<int> slices(1001);
vector<int> counts(1001);

// 주어진 최소 길이 min_len으로 cuts번 자를 수 있는지 확인하는 함수
bool canCut(int min_len, int cuts) {
  int prev = 0;
  int cnt = 0;

  for (int i = 0; i < M; i++) {
    if(slices[i] - prev >= min_len) {
      cnt++;
      prev = slices[i];
    }
  }
  
  // 마지막 조각도 min_len보다 작아야 함
  if(L - prev < min_len) {
    cnt--;
  } 

  return cnt >= cuts;
}

// 각 counts[i]에 대해 이분 탐색으로 최대 최소 길이를 찾는 함수
int findMaxSmallSize(int cuts) {
  int left = 0; 
  int right = L;
  int result = 0;

  while (left <= right) {
    int mid = (left + right) / 2;
    
    // 최소 mid 길이로 cuts번 자를 수 있는지 확인
    if(canCut(mid, cuts)) {
      result = mid;
      left = mid + 1;
    } else {
      right = mid - 1; 
    }
  }

  return result;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  cin >> N >> M >> L;

  for(int i = 0; i < M; i++) {
    cin >> slices[i];
  }

  for(int i = 0; i < N; i++) {
    cin >> counts[i];
  }

  for(int i = 0; i < N; i++) {
    cout << findMaxSmallSize(counts[i]) << "\n";
  }

  return 0;
}
