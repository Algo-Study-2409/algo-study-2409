#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};

int n, m;
int r, c, d;
int board[51][51];

int cnt = 0;

int main(){
    cin >> n >> m;
    cin >> r >> c >> d;

    r++, c++;

    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            cin >> board[i][j];
        }
    }

    while(1){
        if(board[r][c] == 0){
            board[r][c] = 2;
            cnt++;
        }
        
        bool ch = false;
        for(int i=0; i<4; i++){
            int dir = (d + 3 - i) % 4;
            int nx = r + dx[dir];
            int ny = c + dy[dir];

            if(nx < 1 || nx > n || ny < 1 || ny > m) continue;
            if(board[nx][ny] == 0) {
                d = dir;
                r = nx, c = ny;
                ch = true;
                break;
            }
        }

        if(!ch){
            int dir = d+2<4 ? d+2 : d-2;
            int nx = r + dx[dir];
            int ny = c + dy[dir];

            if(nx < 1 || nx > n || ny < 1 || ny > m) break;
            if(board[nx][ny] == 1) break;

            r = nx, c = ny;
        }

    }

    cout << cnt;

}