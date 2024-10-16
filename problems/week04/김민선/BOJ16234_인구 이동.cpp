#include <bits/stdc++.h>
using namespace std;

/*
    - 72ms
    - DFS
    - union인 구역을 저장해 놓고, 이동이 더 이상 없을 때까지 반복
*/

int N, L, R;
int A[51][51];
int visited[51][51];
int dx[] = {0, 1, 0, -1};
int dy[] = {1, 0, -1, 0};
int sum;
int cnt;
vector<pair<int, int>> unions;

void dfs(int x, int y) {
    for (int i = 0; i < 4; i++) {
        int ny = y + dy[i];
        int nx = x + dx[i];

        if (ny < 0 || ny >= N || nx < 0 || nx >= N)
            continue;

        if (visited[ny][nx])
            continue;

        int diff = abs(A[y][x] - A[ny][nx]);
        if (diff >= L && diff <= R) {
            visited[ny][nx] = 1;
            sum += A[ny][nx];
            cnt++;
            unions.push_back({nx, ny});
            dfs(nx, ny);
        }
    }
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> L >> R;

    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            cin >> A[y][x];
        }
    }

    int ret = 0;

    while (1) {
        memset(visited, 0, sizeof(visited));
        bool is_moved = false;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (!visited[y][x]) {
                    visited[y][x] = 1;
                    sum = A[y][x];
                    cnt = 1;
                    unions.push_back({x, y});
                    dfs(x, y);
                    if (cnt > 1) {
                        is_moved = true;
                        int avg = sum / cnt;
                        for (auto& u : unions) {
                            A[u.second][u.first] = avg;
                        }
                    }
                    unions.clear();
                }
            }
        }

        if (is_moved){
            ret++;
        }
        else{
            break;
        }
    }

    cout << ret << "\n";
}
