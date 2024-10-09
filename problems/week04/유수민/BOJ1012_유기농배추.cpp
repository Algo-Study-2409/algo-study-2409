#include<bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define X first
#define Y second

int ground[51][51];
int vis[51][51];
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

int main(){

    int testcase;
    cin >> testcase;

    while(testcase--){
        int ans = 0;
        int n, m, k;
        vector<pii> st;
        cin >> n >> m >>  k;

        for(int i=0; i<51; i++) {
            fill(ground[i], ground[i]+51, 0);
            fill(vis[i], vis[i]+51, 0);
        }

        for(int i=0; i<k; i++){
            int x, y;
            cin >> x >> y;
            ground[x][y] = 1;
            st.push_back({x, y});
        }

        queue<pii> q;
        for(auto start : st){
            if(vis[start.X][start.Y] == 1) continue;

            ans++;
            q.push(start);

            while(!q.empty()){
                pii cur = q.front();
                q.pop();

                for(int i=0; i<4; i++){
                    int nx = cur.X + dx[i];
                    int ny = cur.Y + dy[i];
                    if(nx<0 || nx>=n | ny<0 || ny>=m) continue;
                    if(vis[nx][ny] == 1) continue;
                    if(ground[nx][ny] == 1) {
                        vis[nx][ny] = 1;
                        q.push({nx, ny});
                    }
                }
            }

        }
        cout << ans << "\n";
    }

    return 0;
}