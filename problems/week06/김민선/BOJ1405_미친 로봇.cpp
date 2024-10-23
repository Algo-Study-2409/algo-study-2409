#include<bits/stdc++.h>
using namespace std;

/*
    - 단순할 경로: 같은 곳을 한번만 지나는 경로
    - 단순한 경로의 확률: 각 이동 확률의 곱
    - 로봇의 이동 경로가 단순할 확률은 = N번 단순히 이동했을 때의 확률의 합
*/

int N;

double prob[4]; // 동 서 남 북 이동 확률
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, -1, 1};

bool visited[31][31];
double ret;

void dfs(int x, int y, int cnt, double p) {
    if(cnt == N) {
        ret += p;
        return;
    }

    visited[x][y] = true;

    for(int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if(visited[nx][ny]) continue;

        dfs(nx, ny, cnt + 1, p * prob[i]);
    }

    visited[x][y] = false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    for(int i = 0; i < 4; i++) {
        cin >> prob[i];
        prob[i] /= 100;
    }

    dfs(15, 15, 0, 1);

    cout.precision(11);
	cout << fixed << ret << "\n";

}