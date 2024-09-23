#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,int>
#define piii pair<pair<int, int>, int>

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

int board[51][51];
int T, R, C;
int air1, air2;
queue<piii> dust;


void spread(){ // 확산

    while(!dust.empty()){
        piii cur = dust.front();
        dust.pop();

        int s = cur.Y / 5;

        for(int i=0; i<4; i++){
            int nx = cur.X.X + dx[i];
            int ny = cur.X.Y + dy[i];

            if(nx<1 || nx>R || ny<1 || ny>C) continue;
            else if(board[nx][ny] == -1) continue;

            board[nx][ny] += s;
            board[cur.X.X][cur.X.Y] -= s;
        }

    }

}

void reverse(){ // 공기청정기 위쪽 : 반시계

    int tmp1 = board[1][1];
    // 윗줄
    for(int j=1; j<C; j++){
        board[1][j] = board[1][j+1];
    }
    // 왼쪽줄
    for(int i=air1-1; i>1; i--){
        board[i][1] = board[i-1][1];
    }

    int tmp2 = board[air1][C];
    // 아래줄
    for(int j=C; j>1; j--){
        if(board[air1][j-1] == -1) board[air1][j] = 0;
        else board[air1][j] = board[air1][j-1];
    }
    // 오른쪽줄
    for(int i=1; i<air1; i++){
        board[i][C] = board[i+1][C];
    }

    board[2][1] = tmp1;
    board[air1-1][C] = tmp2;
    board[air1][1] = -1;
}

void rotate(){ // 공기청정기 아래쪽 : 시계 

    int tmp1 = board[air2][C];
    // 윗줄
    for(int j=C; j>1; j--){
        if(board[air2][j-1] == -1) board[air2][j] = 0;
        else board[air2][j] = board[air2][j-1];
    }
    int tmp2 = board[R][C];
    // 오른쪽줄
    for(int i=R; i>air2; i--){
        board[i][C] = board[i-1][C];
    }
    int tmp3 = board[R][1];
    // 아래줄
    for(int j=1; j<C; j++){
        board[R][j] = board[R][j+1];
    }
    // 왼쪽줄
    for(int i=air2+1; i<R; i++){
        board[i][1] = board[i+1][1];
    }
    
    board[air2+1][C] = tmp1;
    board[R][C-1] = tmp2;
    if(R-1 != air2) board[R-1][1] = tmp3;

    board[air2][1] = -1;
}


int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> R >> C >> T;
    for(int i=1; i<=R; i++){
        for(int j=1; j<=C; j++){
            cin >> board[i][j];
            if(board[i][j] == -1 && air1 == 0){
                air1 = i;
                air2 = i+1;
            }
        }
    }

    for(int t=0; t<T; t++){
        for(int i=1; i<=R; i++){
            for(int j=1; j<=C; j++){
                if(board[i][j] > 0) dust.push({{i, j}, board[i][j]});
            }
        }
        spread();
        reverse();
        rotate();
    }

    int total = 0;
    for(int i=1; i<=R; i++){
        for(int j=1; j<=C; j++){        
            if(board[i][j] > 0) total += board[i][j];
        }
    }

    // for(int i=1; i<=R; i++){
    //     for(int j=1; j<=C; j++){        
    //         cout << board[i][j] << " ";
    //     }
    //     cout << "\n";
    // }

    cout << total;
    return 0;
}