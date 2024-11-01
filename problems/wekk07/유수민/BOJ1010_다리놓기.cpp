#include <bits/stdc++.h>
using namespace std;

int n, m;
vector<int> ans;
int res;

long long combination(int n, int k){

    k = min(k, n-k);

    long long tmp = 1;
    for(int i=1; i<=k; i++){
        tmp *= (n-i+1);
        tmp /= i;

    }
    return tmp;
}


// M개 중에 N개를 고르면 되는 문제 
int main(){

    int t;
    cin >> t;
    
    while(t--){
        cin >> n >> m;
        res = combination(m, n);
        ans.push_back(res);
    }

    for(auto e : ans) cout << e << "\n";



    // dp로도 풀 수 있음
    // dp[i][j] = dp[i-1][j-1] + dp[i-1][j]를 이용하면 된다.
    

}

