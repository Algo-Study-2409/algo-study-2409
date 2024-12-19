#include<bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,int>

int n, d;
int dist[10001];
vector<pii> graph[10001];

int main(){
    cin >> n >> d;

    int st, en, len;
    for(int i=0; i<n; i++){
        cin >> st >> en >> len;
        if(st<=d && en<=d) {
            graph[en].push_back({st, len});
        }
    }

    fill(dist, dist+d+1, INT_MAX);
    dist[0] = 0;

    for(int i=1; i<=d; i++){
        if(graph[i].size() == 0){
            dist[i] = dist[i-1]+1;
            continue;
        }
        for(auto nxt : graph[i]){
            dist[i] = min(dist[i], min(dist[i-1]+1, dist[nxt.X]+nxt.Y));
        }
    }

    cout << dist[d];
}