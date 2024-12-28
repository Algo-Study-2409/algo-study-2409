#include <bits/stdc++.h>
using namespace std;

int n, m;
vector<int> employee[100001];
long long ans[100001];

void dfs(int num){

    if(employee[num].size() == 0) return;

    for(auto e : employee[num]){
        ans[e] += ans[num];
        dfs(e);
    }
}

int main(){
    cin >> n >> m;

    for(int i=1; i<=n; i++){
        int boss;
        cin >> boss;
        if(i==1) continue;
        employee[boss].push_back(i);
    }

    int num;
    long long w;
    for(int i=1; i<=m; i++){
        cin >> num >> w;
        ans[num] += w;
    }

    dfs(1);

    for(int i=1; i<=n; i++){
        cout << ans[i] << " ";
    }
}
