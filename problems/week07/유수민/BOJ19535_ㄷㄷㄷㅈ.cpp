#include <bits/stdc++.h>
using namespace std;

#define pii pair<int,int>
#define X first
#define Y second

int tree[300001];
vector<pii> edge;
int n; 

int main(){
    cin >> n;
    int u, v;
    for(int i=0; i<n-1; i++){
        cin >> u >> v;
        tree[u]++;
        tree[v]++;
        edge.push_back({u,v});
    }
    long long cntD = 0;
    long long cntG = 0;

    // 'ㄷ'
    for(auto e : edge){
        u = e.first;
        v = e.second;
        cntD += (tree[u]-1) * (tree[v]-1);
    }
    // 'ㅈ'
    for(int i=1; i<=n; i++){
        if(tree[i] >= 3){
            cntG += (tree[i]) * (tree[i]-1) * (tree[i]-2) / 6;
        }
    }

    // 결과 출력
    if(cntD > cntG * 3) cout << "D";
    else if(cntD <  cntG * 3) cout << "G";
    else if(cntD == cntG * 3) cout << "DUDUDUNGA";
}