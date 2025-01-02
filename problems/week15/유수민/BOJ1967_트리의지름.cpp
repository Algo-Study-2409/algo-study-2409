#include <bits/stdc++.h>
using namespace std;

int n;

struct Node{
    int child;
    int w;
};

int maxNode;
int maxSum;
vector<Node> tree[10001];
bool vis[10001];

void dfs(int cur, int sum){
    
    if(sum > maxSum){
        maxNode = cur;
        maxSum = sum;
    }
    for(auto node : tree[cur]){
        if(vis[node.child]) continue;
        vis[node.child] = true;
        dfs(node.child, sum+node.w);
    }
}


int main(){

    cin >> n;
    
    int a, b, c;
    for(int i=0; i<n-1; i++){
        cin >> a >> b >> c;
        tree[a].push_back({b, c});
        tree[b].push_back({a, c});
    }
    vis[1] = true;
    dfs(1, 0);

    fill(vis, vis+10001, false);
    maxSum = 0;
    vis[maxNode] = true;
    dfs(maxNode, 0);
    cout << maxSum;

}