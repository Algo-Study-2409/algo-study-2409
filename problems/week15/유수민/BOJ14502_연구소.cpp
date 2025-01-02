#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,int>

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

int n, m;
int board[10][10];
int temp[10][10];
bool vis[10][10];
vector<pii> virus;
vector<pii> blanks;

int ans = INT_MIN;

void calc(){
    queue<pii> q;
    for(auto v : virus){
        q.push(v);
        vis[v.X][v.Y] = true;
    }

    while(!q.empty()){
        auto cur = q.front();
        q.pop();

        for(int t=0; t<4; t++){
            int nx = cur.X + dx[t];
            int ny = cur.Y + dy[t];
            if(nx < 1 || nx > n || ny < 1 || ny > m) continue;
            if(vis[nx][ny]) continue;
            if(temp[nx][ny] != 0) continue;

            temp[nx][ny] = 2;
            q.push({nx, ny});
            vis[nx][ny] = true;
        }
    }
    int cnt = 0;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            if(temp[i][j] == 0) cnt++;
        }
    }

    ans = max(ans, cnt);
}


int main(){
    cin >> n >> m;

    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            cin >> board[i][j];
            if(board[i][j] == 0) blanks.push_back({i, j});
            else if(board[i][j] == 2) virus.push_back({i, j});
        }
    }

    int sz = blanks.size();
    vector<int> brute(sz-3, 0);
    for(int t=0; t<3; t++) brute.push_back(1);

    do{
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                temp[i][j] = board[i][j];
                vis[i][j] = false;
            }
        }

        for(int i=0; i<brute.size(); i++){
            if(brute[i] == 1) temp[blanks[i].X][blanks[i].Y] = 1;
        }

        calc();

    }while(next_permutation(brute.begin(), brute.end()));


    cout << ans;
    
}