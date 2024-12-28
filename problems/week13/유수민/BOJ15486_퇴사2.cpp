#include<bits/stdc++.h>
using namespace std;

int dp[1500001];

int main(){
    int n;
    cin >> n;

    int day, point;
    for(int today=1; today<=n; today++){
        cin >> day >> point;

        dp[today+day] = max(dp[today+day], dp[today]+point);
        dp[today+1] = max(dp[today], dp[today+1]);

    }

    cout << dp[n+1];

}