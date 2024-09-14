#include <bits/stdc++.h>
using namespace std;

int n;
int t[16];
int p[16];
int MX = 0;

void dfs(int day, int money){
   
    if(day >= n){
        MX = max(MX, money);
        return;
    }

    // 상담 O
    if(day+t[day+1] <= n){
        dfs(day+t[day+1], money+p[day+1]);
    }
    // 상담 X
    dfs(day+1, money);
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n;
    for(int i=1; i<=n; i++){
        cin >> t[i];
        cin >> p[i];
    }

    dfs(0, 0);

    cout << MX;

    return 0;
}