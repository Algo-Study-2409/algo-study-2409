#include <bits/stdc++.h>
using namespace std;

int board[101][71];
bool vis[101][71];
bool b = false;
int n, m;

int dx[8] = {0, 1, 0, -1, -1, 1, 1, -1};
int dy[8] = {1, 0, -1, 0, -1, 1, -1, 1};

void bfs(int x, int y){

    for(int t=0; t<8; t++){
        int nx = x + dx[t];
        int ny = y + dy[t];
        if(nx < 1 || nx > n || ny < 1 || ny > m) continue;
        if(board[x][y] < board[nx][ny]) b = false;
        if(vis[nx][ny]) continue;
        if(board[x][y] == board[nx][ny]){
            vis[nx][ny] = true;
            bfs(nx, ny);
        }
    }
}

int main(){
    cin >> n >> m;

    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            cin >> board[i][j];
        }
    }

    int ans = 0;

    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){

            if(vis[i][j]) continue;
            b = true;
            vis[i][j] = true;
            bfs(i, j);
            if(b == true) ans++;
            
        }
    }

    cout << ans;
    
}