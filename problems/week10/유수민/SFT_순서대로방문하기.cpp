#include<bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define X first
#define Y second

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

int n;
int m;

int board[5][5];
int check[5][5];

vector<pii> v;
int ans = 0;

void calc(pii cur, int idx, bool vis[][5]){

    if(idx == m){
        ans++;
        return;
    }

    for(int i=0; i<4; i++){
        int nx = cur.X + dx[i];
        int ny = cur.Y + dy[i];

        if(nx < 1 || nx > n || ny < 1 || ny > n) continue;
        if(vis[nx][ny] == true) continue;
        if(board[nx][ny] == 1) continue;
        
        if(check[nx][ny] != 0 && check[nx][ny] != idx+1) continue;

        pii newCur = {nx, ny};
        if(check[nx][ny] == 0){
            vis[nx][ny] = true;
            calc(newCur, idx, vis);
            vis[nx][ny] = false;
        }
        else if(check[nx][ny] == idx+1){
            vis[nx][ny] = true;
            calc(newCur, idx+1, vis);
            vis[nx][ny] = false;
        }
    }
}

int main(int argc, char** argv)
{
    
    cin >> n >> m;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cin >> board[i][j];
        }
    }
    int a, b;

    int t = 0;
    for(int i=0; i<m; i++){
        cin >> a >> b;
        v.push_back({a, b});
        check[a][b] = ++t;
    }

    int idx = 1;
    bool vis[5][5];
    pii cur = {v[0].X, v[0].Y};
    
    for(int i=0; i<5; i++) fill(vis[i], vis[i]+5, false);
    
    vis[v[0].X][v[0].Y] = true;
    
    calc(cur, idx, vis);

    cout << ans;

   return 0;
}