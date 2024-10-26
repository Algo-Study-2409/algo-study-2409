#include <bits/stdc++.h>
using namespace std;

string dp[102][102];

int main(){

    int n, m;
    cin >> n >> m;

    for (int i = 1; i <= n; i++){
        for (int j = 0; j <= i; j++){
            if(j == 0 || j == i) {
                dp[i][j] = "1";
                continue;
            }
            int sum = 0; 
            string n1 = dp[i - 1][j];
            string n2 = dp[i - 1][j - 1];

            while(!n1.empty() || !n2.empty() || sum){
                if(!n1.empty()){
                    sum += n1.back() - '0';
                    n1.pop_back();
                }
                if(!n2.empty()){
                    sum += n2.back() - '0';
                    n2.pop_back();
                }

                dp[i][j].push_back(sum%10 + '0');
                sum /= 10;
            }
            reverse(dp[i][j].begin(), dp[i][j].end());
        }
    }

    cout << dp[n][m];




    // 오버 플로우가 나는 코드
    // long long res = 1;
    // int k = min(m, n-m);

    // for(int i=1; i<=k; i++){
    //     res = res * (n+1-i) / i;
    // }
}