#include <bits/stdc++.h>
using namespace std;

vector<int> v[101];
bool vis[101];

int n;
int k;

int main(){

    cin >> n;
    cin >> k;
    while(k--){
        int a, b;
        cin >> a >> b;
        v[a].push_back(b);
        v[b].push_back(a);
    }

    vis[1] = true;
    queue<int> q;
    q.push(1);

    int ans = 0;

    while(!q.empty()){
        int cur = q.front();
        q.pop();
        ans++;
    
        for(auto e : v[cur]){
            if(vis[e] == false){
                q.push(e);
                vis[e] = true;
            }
        }
    }

    cout << ans-1;
}