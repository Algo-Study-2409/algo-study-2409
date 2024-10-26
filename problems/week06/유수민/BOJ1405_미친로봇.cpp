#include <bits/stdc++.h>
using namespace std;

int dy[4] = {0, 0, 1, -1};
int dx[4] = {1, -1, 0, 0};

long double n;
long double percent[4];
int vis[30][30];


long double dfs(int y, int x, long double p, int cnt){
    if(cnt == n or p == 0) return p;

    long double sum = 0;

    for(int i=0; i<4; i++){
        if (vis[y + dy[i]][x + dx[i]]) continue; 
        vis[y + dy[i]][x + dx[i]]++; 
        sum += dfs(y + dy[i], x + dx[i], p * percent[i], cnt + 1);
        vis[y + dy[i]][x + dx[i]]--; 
    }
    return sum;

}

int main(){

    cin >> n;
    for(int i=0; i<4; i++){
        cin >> percent[i];
        percent[i] /= 100;
    }

    vis[15][15]++;
    cout.precision(10);
    cout << dfs(15, 15, 1, 0);
    return 0;

}