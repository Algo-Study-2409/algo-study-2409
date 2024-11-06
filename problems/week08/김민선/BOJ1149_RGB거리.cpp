#include<bits/stdc++.h>
using namespace std;

int N;
int dp[1001][3]; // i번 째 집을 빨, 초, 파로 칠했을 때의 최소 비용

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    int r, g, b;
    for (int i = 1; i <= N; i++) {
        cin >> r >> g >> b;

        dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + r;
        dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + g;
        dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + b;
    }

    cout << min({dp[N][0], dp[N][1], dp[N][2]}) << "\n";

    return 0;
}