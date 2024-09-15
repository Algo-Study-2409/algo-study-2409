#include <bits/stdc++.h>
using namespace std;

int n, m;
vector<int> heavy[100];
vector<int> light[100];
int vis[100];
int target;

int check(vector<int> arr[], int st, int depth){
    
    fill(vis, vis+100, 0);
    
    queue<int> q;
    vis[st] = 1;

    for(const int& e : arr[st]) {
        q.push(e);
        vis[e] = 1;
    }

    while(!q.empty()){
        int cur = q.front();
        q.pop();

        for(auto c : arr[cur]){
            if(vis[c] == 0) {
                q.push(c);
                vis[c] = 1;
            }
        }
    }

    int res = 0;
    for(int i=1; i<=n; i++){
        if(vis[i] == 1 && i != st) res++;
    }
    return res;
}


int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m;
    target = (n+1)/2;

    int a, b;

    for(int i=0; i<m; i++){
        cin >> a >> b;
        heavy[a].push_back(b);
        light[b].push_back(a);
    }

    int ans = 0;

    for(int i=1; i<=n; i++){
        int cnt1 = check(heavy, i, 0);
        int cnt2 = check(light, i, 0);

        if(cnt1 >= target || cnt2 >= target) ans++;
    }

    cout << ans;
    return 0;
}