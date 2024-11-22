#include <bits/stdc++.h>
using namespace std;

int n, m;
int board[52][52];

int main() {
    cin >> n >> m;

    for (int i = 1; i <= n; i++) {
        string row;
        cin >> row;
        for (int j = 1; j <= m; j++) {
            board[i][j] = row[j - 1] - '0';
        }
    }

    int ans = 1;


    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            for (int len = 0; i + len <= n && j + len <= m; len++) {
                if (board[i][j] == board[i + len][j] &&
                    board[i][j] == board[i][j + len] &&
                    board[i][j] == board[i + len][j + len]) {
                    ans = max(ans, (len + 1) * (len + 1));
                }
            }
        }
    }

    cout << ans;
    return 0;
}
