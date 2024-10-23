#include<bits/stdc++.h>
using namespace std;


/*
    - 산봉우리: 주변 8방향의 높이보다 높은 지점이 없는 지점 그룹
    - bfs로 산봉우리 그룹 찾기
        - 같은 높이이면 산봉우리 그룹
        - 산봉우리 그룹 주변 지점이 더 높으면 해당 그룹은 산봉우리가 아님
*/

int N, M;
vector<vector<int>> adj(101, vector<int>(71));
vector<vector<bool>> visited(101, vector<bool>(71));
int dx[8] = {1, -1, 0, 0, 1, -1, 1, -1};
int dy[8] = {0, 0, -1, 1, 1, 1, -1, -1};

bool bfs(int y, int x) {
    queue<pair<int, int>> q;
    q.push({y, x});
    visited[y][x] = true;

    bool is_peak = true;
    int h = adj[y][x];

    while (!q.empty()) {
        int cy = q.front().first;
        int cx = q.front().second;
        q.pop();

        for (int i = 0; i < 8; i++) {
            int ny = cy + dy[i];
            int nx = cx + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;

            if (!visited[ny][nx] && adj[ny][nx] == h) {
                visited[ny][nx] = true;
                q.push({ny, nx});
            }
            
            if (adj[ny][nx] > h) {
                is_peak = false;
            }
        }
    }

    return is_peak;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
	cout.tie(NULL);

    cin >> N >> M;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> adj[i][j];
        }
    }

    int ret = 0;

    for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
            if (!visited[y][x]) {
                if (bfs(y, x)) {
                    ret++;
                }
            }
        }
    }

    cout << ret << "\n";

    return 0;
}
