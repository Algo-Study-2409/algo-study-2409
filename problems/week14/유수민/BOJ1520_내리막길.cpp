#include <bits/stdc++.h>
using namespace std;

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

int n, m;
int dp[505][505];
int board[505][505];

int dfs(int i, int j){

    if(i==n && j==m) return 1;

    if(dp[i][j] != -1) return dp[i][j];

    dp[i][j] = 0;
     
    for(int d=0; d<4; d++){
        int nx = i + dx[d];
        int ny = j + dy[d];

        if(nx < 1 || nx > n || ny < 1 || ny > m) continue;

        if(board[nx][ny] < board[i][j]) dp[i][j] += dfs(nx, ny);
    }

    return dp[i][j];
}


int main(){
    cin >> n >> m;

    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            cin >> board[i][j];
        }
    }

    for(int i=1; i<=n; i++){
        fill(dp[i], dp[i]+m+1, -1);
    }

    int ans = dfs(1, 1);

    cout << ans;
}