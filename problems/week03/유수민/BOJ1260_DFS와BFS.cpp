#include <bits/stdc++.h>
using namespace std;


int n, m, v;
int vis[1001];
vector<int> vec[1001];


void dfs(int cur){
    vis[cur] = 1;
    cout << cur << " ";
    for(auto e : vec[cur]){
        if(vis[e] == 0){
            dfs(e);
        }
    }
}

void bfs(int cur){

    queue<int> q;
    q.push(cur);
    vis[cur] = 1;

    while(!q.empty()){
        int t = q.front();
        q.pop();
        cout << t << " ";

        for(auto e : vec[t]){
            if(vis[e] == 0) {
                q.push(e);
                vis[e] = 1;
            }
        }
    } 
}

int main(){

    cin >> n >> m >> v;

    int a, b;
    for(int i=0; i<m; i++){
        cin >> a >> b;
        vec[a].push_back(b);
        vec[b].push_back(a);
    }

    for(int i=1; i<=n; i++) sort(vec[i].begin(), vec[i].end());

    // dfs
    fill(vis, vis+n+1, 0); 
    dfs(v);

    cout << "\n";

    // bfs
    fill(vis, vis+n+1, 0); 
    bfs(v);

}