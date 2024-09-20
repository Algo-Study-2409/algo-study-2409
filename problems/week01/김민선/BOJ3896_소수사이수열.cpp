#include<bits/stdc++.h>
using namespace std;

#define MAX_PRIME 1299709

vector<bool> is_prime(MAX_PRIME + 1, true);

/*
  - 4ms
  - 에라토스테네스의 체 사용
*/

void eratosthenes() {
  is_prime[0] = is_prime[1] = false;

  for (int i = 2; i * i <= MAX_PRIME; i++) {
    if (is_prime[i]) {
      // i의 배수들은 소수 아님
      for (int j = i * i; j <= MAX_PRIME; j += i) {
        is_prime[j] = false;
      }
    }
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int t;
  
  cin >> t;
  eratosthenes();
  int num;
  
  while(t--) {
    cin >> num;
    if (is_prime[num]) {
      cout << 0 << '\n';
    } else {
      int left = num - 1;
      int right = num + 1;
      // num보다 작은 소수 찾기
      while (!is_prime[left]) {
        left--;
      }
      // num보다 큰 소수 찾기
      while (!is_prime[right]) {
        right++;
      }
      cout << right - left << '\n';
    }
  }
}