#include <bits/stdc++.h>
using namespace std;

int dp[100001];

int main(){
    int n;
    cin >> n;

    for(int i=1; i<=n; i++){
        dp[i] = i;
    }

    int s = int(sqrt(n));
    
    vector<int> sq;
    for(int i=1; i<=s; i++){
        sq.push_back(i*i);
        dp[i*i] = 1;
    }

    dp[1] = 1;
    dp[2] = 2;

    for(int i=3; i<=n; i++){

        for(int t : sq){

            if(t > i) break;

            dp[i] = min(dp[i], dp[i-t]+dp[t]);
        }
    }

    cout << dp[n];
    

}