#include<bits/stdc++.h>
using namespace std;

/*
    - 윤이가 리프 노드에 도착하면 탈출
    - 리프노드: 이웃한 노드가 하나인 노드
    - 그전에 경찰과 같은 노드에 있으면 잡힘
    - 최선의 전략으로 추격전
*/

int N;
vector<vector<int>> adj(200001);
vector<vector<int>> dist(200001, vector<int>(3, -1)); // 0: 윤이, 1: 달구, 2: 포닉스
vector<int> leaf;
int yooni, dalgoo, ponix;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    for(int i = 0; i < N - 1; i++) {
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    // 리프 노드 구하기
    for(int i = 1; i <= N; i++) {
        if(adj[i].size() == 1)
            leaf.push_back(i);
    }

    cin >> yooni >> dalgoo >> ponix;

    for (int i = 0; i < 3; i++) {
        queue<int> q;
        int start = (i == 0 ? yooni : (i == 1 ? dalgoo : ponix));
        dist[start][i] = 0; // 초기 거리 설정
        q.push(start);
        
        vector<bool> visited(N + 1, false);
        visited[start] = true;

        while (!q.empty()) {
            int cur = q.front();
            q.pop();

            for (int next : adj[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next][i] = dist[cur][i] + 1;
                    q.push(next);
                }
            }
        }
    }

    bool flag = false;

    for (int l : leaf) {
        if (dist[l][0] < dist[l][1] && dist[l][0] < dist[l][2]) {
            flag = true;
            break;
        }
    }

    cout << (flag ? "YES" : "NO") << "\n";
}
