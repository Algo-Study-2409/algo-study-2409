#include<bits/stdc++.h>
using namespace std;

/*
    - 1번 컴퓨터와 연결된 컴퓨터의 수를 dfs로 구함
*/

int N, M;
vector<vector<int>> arr(101, vector<int>(101, 0));
vector<int> visited(101, 0);
int ret;

void dfs(int go) {
    ret++;
    for (int i = 1; i <= N; i++) {
        if (arr[go][i] == 1 && visited[i] == 0) {
			visited[i] = 1;
			dfs(i);
		}
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;

    for (int i = 0; i < M; i++) {
        int a, b;
        cin >> a >> b;
        arr[a][b] = 1;
        arr[b][a] = 1;
    }

	visited[1] = 1;
    dfs(1);

	cout << ret - 1 << "\n";
}