#include<bits/stdc++.h>
using namespace std;

/*
    - 32ms
    - 주어진 대포알수로 만들 수 있는 최소 사면체의 수
    - 사면체는 정삼각형의 합으로 만들 수 있음
    - i번째 사면체는 i-1번째 사면체 + i번째 정삼각형의 합으로 만들 수 있음
    - dp[i] i개가 주어질 때 최소 사면체의 수
    - dp[i] = min(dp[i], dp [i - arr[j]] + 1)
        - i - arr[j]개가 주어질 때 최소 사면체의 수 + 1
*/

#define ll long long
#define INF 987654321

int N;
vector<ll> arr; // i번째 사면체
vector<ll> dp(300001, INF); // i개가 주어질 때 최소 사면체의 수

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    arr.push_back(1);

    int tri = 1; // i번 째 정삼각형
    int idx = 2;

    while (arr.back() + tri + idx <= N) {
        tri += idx;
        arr.push_back(arr.back() + tri);
        idx++;
    }

    dp[0] = 0;

    for (int i = 0; i < arr.size(); i++) {
        dp[arr[i]] = 1; // i번째 사면체는 1개로 만들 수 있음

        for (int j = arr[i]; j <= N; j++) {
            dp[j] = min(dp[j], dp[j - arr[i]] + 1);
        }
    }

    cout << dp[N] << "\n";
}

