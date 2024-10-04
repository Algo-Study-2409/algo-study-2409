#include<bits/stdc++.h>
using namespace std;

/*
    - 4ms
    - DFS, BFS
    - DFS, BFS를 구현
*/

int N, M, V;
vector<vector<int>> arr(1001, vector<int>(1001, 0));
vector<int> visited(1001, 0);

void dfs(int go){
    cout << go << " ";
    visited[go] = 1;
    for (int i = 1; i <= N; i++){
        if (arr[go][i] == 1 && visited[i] == 0){
            dfs(i);
        }
    }
}

void bfs(int go){
    queue<int> q;
    q.push(go);
    visited[go] = 1;
    while (!q.empty()){
        int now = q.front();
        q.pop();
        cout << now << " ";
        for (int i = 1; i <= N; i++){
            if (arr[now][i] == 1 && visited[i] == 0){
                q.push(i);
                visited[i] = 1;
            }
        }
    }
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M >> V;

    for (int i = 0; i < M; i++){
        int a, b;
        cin >> a >> b;
        arr[a][b] = 1;
        arr[b][a] = 1;
    }
    
    dfs(V);
    cout << "\n";
    fill(visited.begin(), visited.end(), 0);
    bfs(V);
    cout << "\n";
}