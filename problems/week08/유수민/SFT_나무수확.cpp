#include<bits/stdc++.h>
using namespace std;

int board[1001][1001];
int dp[1001][1001][2];

int main(int argc, char** argv)
{

    int n;
    cin >> n;

    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cin >> board[i][j];
        }
    }

    dp[1][1][0] = board[1][1];
    dp[1][1][1] = 2 * board[1][1];

    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            if(i==1 && j==1) continue;
            
            if(i > 1){
                dp[i][j][0] = max(dp[i][j][0], dp[i-1][j][0] + board[i][j]);
                dp[i][j][1] = max(dp[i][j][1], max(dp[i-1][j][1] + board[i][j], dp[i-1][j][0] + 2 * board[i][j]));
            }
            if(j > 1){
                dp[i][j][0] = max(dp[i][j][0], dp[i][j-1][0] + board[i][j]);
                dp[i][j][1] = max(dp[i][j][1], max(dp[i][j-1][1] + board[i][j], dp[i][j-1][0] + 2 * board[i][j]));
            }
        }
    }

    cout << max(dp[n][n][0], dp[n][n][1]);

   return 0;
}