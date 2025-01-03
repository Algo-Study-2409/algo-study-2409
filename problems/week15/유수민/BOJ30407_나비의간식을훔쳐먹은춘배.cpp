#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int, int>

int n, h, d, k;
int attacks[20];

pair<pii, int> dp[20][3];

int main(){


    cin >> n;
    cin >> h >> d >> k; // 체력, 거리, 이동거리
    for(int i=1; i<=n; i++){
        cin >> attacks[i];
    }

    for(int i=1; i<=n; i++){
        for(int j=0; j<3; j++){
            dp[i][j] = {{INT_MIN, 0}, -1};
        }
    }
    
    int tmp = attacks[1] - d < 0 ? 0 : attacks[1] - d;
    dp[1][0] = {{h - tmp / 2, d}, 0};
    if(dp[1][0].X.X < 0) dp[1][0].X.X = 0;

    tmp = attacks[1] - (d + k) < 0 ? 0 : attacks[1] - (d + k);
    dp[1][1] = {{h - tmp, d+k}, 0};
    if(dp[1][1].X.X < 0) dp[1][1].X.X = 0;

    tmp = (attacks[1] - d) < 0 ? 0 : (attacks[1] - d);
    dp[1][2] = {{h - tmp, d}, 1};
    if(dp[1][2].X.X < 0) dp[1][2].X.X = 0;

    for(int i=2; i<=n; i++){

        for(int t=0; t<3; t++){ // 이전 스킬

            if(dp[i-1][t].Y == -1) continue; // 불가능

            if(dp[i-1][t].Y == 1){
                if(dp[i-1][t].X.X >  dp[i][0].X.X){
                    dp[i][0] = {{dp[i-1][t].X.X, dp[i-1][t].X.Y}, -2};
                }
                if(dp[i-1][t].X.X >  dp[i][1].X.X){
                    dp[i][1] = {{dp[i-1][t].X.X, dp[i-1][t].X.Y + k}, -2};
                }
                dp[i][2] = {{0, 0}, -1}; // 불가능
            }
            else{
                // 웅크리기
                int how = (attacks[i] - dp[i-1][t].X.Y) < 0 ? 0 : (attacks[i] - dp[i-1][t].X.Y);
                int tt = dp[i-1][t].X.X - how / 2;
                if(tt > dp[i][0].X.X){
                    dp[i][0] = {{tt, dp[i-1][t].X.Y}, dp[i-1][t].Y};
                }

                // 네발로 걷기
                how = (attacks[i] - (dp[i-1][t].X.Y + k)) < 0 ? 0 : (attacks[i] - (dp[i-1][t].X.Y + k));
                tt = dp[i-1][t].X.X - how;
                if(tt > dp[i][1].X.X){
                    dp[i][1] = {{tt, dp[i-1][t].X.Y + k}, dp[i-1][t].Y};
                }

                // 깜짝 놀라게 하기
                if(dp[i-1][t].Y == -2) {
                    
                    continue;
                }
                how = (attacks[i] - k) < 0 ? 0 : (attacks[i] - k);
                tt = dp[i-1][t].X.X - how;
                if(tt > dp[i][2].X.X){
                    dp[i][2] = {{tt, dp[i-1][t].X.Y}, 1};
                }
            }
            if(dp[i][0].X.X < 0) dp[i][0].X.X = 0;
            if(dp[i][1].X.X < 0) dp[i][1].X.X = 0;
            if(dp[i][2].X.X < 0) dp[i][2].X.X = 0;

        }
    }

    int ans = 0;
    for(int j=0; j<3; j++){
        ans = max(dp[n][j].X.X, ans);
    }
    if(ans == 0) ans = -1;
    
    cout << ans;
    

}