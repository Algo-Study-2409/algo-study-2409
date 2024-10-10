#include<bits/stdc++.h>
using namespace std;

/*
    - 20ms
    - 플로이드-와샬
    - 모든 경로에 대해 최단 거리를 구함
    - 모든 시티, 모든 사람에 대해 왕복 시간의 최대값을 구함
*/

#define INF 987654321

int N, M, K;
vector<vector<int>> arr(201, vector<int>(201, INF));
vector<int> friends;
vector<int> res;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;

    for (int i = 1; i <= N; i++) {
        arr[i][i] = 0;
    }

    for (int i = 0; i < M; i++) {
        int a, b, t;
        cin >> a >> b >> t;
        arr[a][b] = t;
    }

    for (int k = 1; k <= N; k++) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (arr[i][k] != INF && arr[k][j] != INF) {
                    arr[i][j] = min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }
    }

    cin >> K;
    for (int i = 0; i < K; i++) {
        int a;
        cin >> a;
        friends.push_back(a);
    }

    int min_max_time = INF;

    for (int i = 1; i <= N; i++) {
        int max_time = 0;
        bool is_path = true;
        for (int j = 0; j < friends.size(); j++) {
            // 경로가 없는 경우
            if (arr[friends[j]][i] == INF || arr[i][friends[j]] == INF) {
                is_path = false;
                break;
            }
            max_time = max(max_time, arr[friends[j]][i] + arr[i][friends[j]]);
        }
        if (!is_path) {
            continue;
        }
        if (min_max_time > max_time) {
            min_max_time = max_time;
            res.clear();
            res.push_back(i);
        } else if (min_max_time == max_time) {
            res.push_back(i);
        }
    }

    for (int i = 0; i < res.size(); i++) {
        cout << res[i] << " ";
    }
}
