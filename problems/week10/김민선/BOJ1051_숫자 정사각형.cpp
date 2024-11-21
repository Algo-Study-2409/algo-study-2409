#include<bits/stdc++.h>
using namespace std;

int N, M;
vector<vector<int>> arr(51, vector<int>(51, 0));

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;

    for (int i = 0; i < N; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < M; j++) {
            arr[i][j] = s[j] - '0';
        }
    }

    int ans = 1;
    int K = min(M,N);

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            for (int k = 1; k < K; k++) {
                if (i + k >= N || j + k >= M) {
                    continue;
                }

                if ((k + 1) * (k + 1) <= ans) {
                    continue;
                }

                if (arr[i][j] == arr[i + k][j] && arr[i][j] == arr[i][j + k] && arr[i][j] == arr[i + k][j + k]) {
                    ans = (k + 1) * (k + 1);
                }
            }
        }
    }

    cout << ans << "\n";
}