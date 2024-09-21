#include <bits/stdc++.h>
using namespace std;

int n;
long long s;
vector<long long> v;
int ans;

void dfs(int idx, long long sum){

    if(idx == n){
        if(sum == s) ans++;
        return;
    }

    dfs(idx+1, sum);
    dfs(idx+1, sum+v[idx]);
}

int main(){

    cin >> n >> s;
    long long tmp;
    for(int i=0; i<n; i++){
        cin >> tmp;
        v.push_back(tmp);
    }   

    
    dfs(0, 0);
    if(s==0) ans--;

    cout << ans;
}