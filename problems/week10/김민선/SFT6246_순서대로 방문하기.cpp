#include<bits/stdc++.h>
using namespace std;

int N, M;
int board[5][5];
int visited[5][5] = {0,};

int ans = 0;
vector<pair<int, int>> v;

int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

void dfs(int x, int y, int idx) {
    if (idx == M) {
        ans++;
        return;
    }

    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 1 || nx > N || ny < 1 || ny > N) {
            continue;
        }

        if (visited[nx][ny] == 1 || board[nx][ny] == 1) {
            continue;
        }

        visited[nx][ny] = 1;
        if (nx == v[idx].first && ny == v[idx].second) {
            dfs(nx, ny, idx + 1);
        } else {
            dfs(nx, ny, idx);
        }
        visited[nx][ny] = 0;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;

    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            board[i][j] = 1;
        }
    }

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> board[i][j];
        }
    }

    for (int i = 0; i < M; i++) {
        int x, y;
        cin >> x >> y;
        v.push_back({x, y});
    }

    visited[v[0].first][v[0].second] = 1;

    dfs(v[0].first, v[0].second, 1);

    cout << ans << "\n";
}