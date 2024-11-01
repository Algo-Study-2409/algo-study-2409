#include <bits/stdc++.h>
using namespace std;

int n;
long long dp[300001];

int main(){
    cin >> n;

    fill(dp, dp+n+1, INT_MAX);

    vector<long long> v;

    int i=1;
    for(int i=1;; i++){
        int num = i * (i + 1) * (i+2) / 6;
		if (num > 300000) break;

        dp[num] = 1;
        v.push_back(num);
    }

    for(int i=0; i<v.size(); i++){
        for(int j=v[i]; j<=n; j++){
            dp[j] = min(dp[j], dp[j-v[i]]+ 1);
        }
    }

    cout << dp[n];

}