#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,int>

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

int n, m;
int board[101][101];
int vis[101][101];

void dfs(int(*board)[101], int depth, int count){

    for(int i=0; i<101; i++) fill(vis[i], vis[i]+101, 0);
    queue<pii> q;
    q.push({1, 1});
    int cnt = 0;
    

    while(!q.empty()){
        pii cur = q.front();
        q.pop();
        vis[cur.X][cur.Y] = 1;

        for(int i=0; i<4; i++){
            int nx = cur.X + dx[i];
            int ny = cur.Y + dy[i];

            if(nx<1 || nx>n || ny<1 || ny>m) continue;
            if(vis[nx][ny] == 1) continue;

            if(board[nx][ny] == 1){
                board[nx][ny] = 2;
                vis[nx][ny] = 1;
                cnt++;
            }
            else{
                q.push({nx, ny});
                vis[nx][ny] = 1;
            }
        }
    }
    
    if(cnt == 0){
        cout << depth << "\n" << count;
        return;
    }

    else {
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(board[i][j] == 2) board[i][j] = 0;
            }
        }
        dfs(board, depth+1, cnt);
    }    
    
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            cin >> board[i][j];
        }
    }
    
    dfs(board, 0, 0);

    return 0;
}