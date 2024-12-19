#include <bits/stdc++.h>
using namespace std;

int n, m;
int board[8][8];
int mn = INT_MAX;

void recur(int i, int j, int dir, int cost){
    
    if(i == n){
        mn = min(mn, cost+board[i][j]);
        return;
    }

    if(dir != 1 && j+1<=m){
        recur(i+1, j+1, 1, cost+board[i][j]);
    }
    if(dir != 2){
        recur(i+1, j, 2, cost+board[i][j]);
    }
    if(dir != 3 && j-1>=1){
        recur(i+1, j-1, 3, cost+board[i][j]);
    }

}

int main(){

    cin >> n >> m;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            cin >> board[i][j];
        }
    }


    for(int j=1; j<=m; j++){
        recur(1, j, 0, 0);
    }

    cout << mn;
}