#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,int>

int n, a, b, c;

bool isLeaf[200001];
vector<int> road[200001]; 
bool cantGo[200001];
int distYoon[200001];
bool vis[200001];


int main(){
    cin >> n;

    int u, v;
    for(int i=0; i<n-1; i++){
        cin >> u >> v;
        road[u].push_back(v);
        road[v].push_back(u);

    }
    cin >> a >> b >> c;

    // 윤이가 방문하는 노드 거리 계산
    queue<pii> q;
    q.push({a, 0});
    vis[a] = true;
    while(!q.empty()){
        pii cur = q.front();
        q.pop();
        distYoon[cur.X] = cur.Y;
        int temp = 0;
        for(const int e : road[cur.X]){
            temp++;
            if(vis[e]) continue;
            vis[e] = true;
            q.push({e, cur.Y+1});
        }
        if(temp == 1) {
            isLeaf[cur.X] = true; 
            // cout << cur.X << " ";
        }
    }

    if(isLeaf[a] == true) {
        cout << "YES";
        return 0;
    }

    // 달구 b
    fill(vis, vis+200001, 0);
    int d = 0, node = b;
    q.push({node, d});
    vis[b] = true;
    while(!q.empty()){
        pii cur = q.front();
        q.pop();
        if(cur.Y == distYoon[cur.X] || cur.Y+1 == distYoon[cur.X]) {
            // cout << "curY" << cur.Y;
            cantGo[cur.X] = true;
            // cout << "cant" << cur.X << "\n";
        }
        for(const int e : road[cur.X]){
            if(vis[e]) continue;
            vis[e] = true;
            q.push({e, cur.Y+1});
        }
    }

    // 포닉스 c
    fill(vis, vis+200001, 0);
    d = 0, node = c;
    q.push({node, d});
    vis[c] = true;
    while(!q.empty()){
        pii cur = q.front();
        q.pop();
        if(cur.Y == distYoon[cur.X] || cur.Y+1 == distYoon[cur.X]) cantGo[cur.X] = true;
        for(const int e : road[cur.X]){
            if(vis[e]) continue;
            vis[e] = true;
            q.push({e, cur.Y+1});
        }
    }

    string ans = "NO";
    // 윤이가 갈 수 있는 거리 계산
    fill(vis, vis+200001, 0);

    q.push({a, 0});
    vis[a] = true;
    while(!q.empty()){
        pii cur = q.front();
        q.pop();
        if(isLeaf[cur.X] == true){
            ans = "YES";
            break;
        }
        for(const int e : road[cur.X]){
            if(vis[e]) continue;
            if(cantGo[e]) continue;
            vis[e] = true;
            q.push({e, cur.Y+1});
        }
    }

    cout << ans;


}