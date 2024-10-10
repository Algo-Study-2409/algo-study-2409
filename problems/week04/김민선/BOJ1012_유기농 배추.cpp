#include<bits/stdc++.h>
using namespace std;

/*
    - 0ms
    - BFS
    - 배추에 지렁이 있으면 연결된 배추들 모두 안썪음
    - BFS로 연결된 배추들을 모두 탐색 cnt + 1
    - 반복
*/

int T, M, N, K;
int dx[] = {0, 1, 0, -1};
int dy[] = {1, 0, -1, 0};

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> T;

    while (T--) {
        cin >> M >> N >> K;

        vector<vector<int>> arr(N, vector<int>(M, 0));
        vector<vector<bool>> visited(N, vector<bool>(M, false));

        for (int i = 0; i < K; i++) {
            int x, y;
            cin >> x >> y;
            arr[y][x] = 1;
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0 || visited[i][j]) {
                    continue;
                }
                cnt++;
                queue<pair<int, int>> q;
                q.push({i, j});
                visited[i][j] = true;

                while (!q.empty()) {
                    int x, y;
                    tie(x, y) = q.front();
                    q.pop();

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                            continue;
                        }
                        if (arr[nx][ny] == 0 || visited[nx][ny]) {
                            continue;
                        }
                        q.push({nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        cout << cnt << "\n";
    }
}