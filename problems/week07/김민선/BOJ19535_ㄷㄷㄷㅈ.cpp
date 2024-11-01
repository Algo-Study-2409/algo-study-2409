#include <bits/stdc++.h>
using namespace std;

/*
    - 148ms
    - D 트리: 'ㄷ'이 > 'ㅈ' * 3
    - G 트리: 'ㄷ'이 < 'ㅈ' * 3
    - DUDUDUNGA 트리: 'ㄷ' == 'ㅈ' * 3
	- 'ㅈ'는 해당 노드의 자식 3개를 그룹으로 하는 경우의 수
	- 'ㄷ'은 해당 edge의 양 끝점을 그룹으로 하는 경우의 수
*/

#define ll long long

int N;
ll g_cnt = 0, d_cnt = 0;
vector<int> adj[300001];
vector<pair<int, int>> edge;

// nC3 계산 함수
ll combi_num(int n) {
    return 1LL * n * (n - 1) * (n - 2) / 6;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
	cout.tie(NULL);

    cin >> N;

    for (int i = 0; i < N - 1; i++) {
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
        edge.push_back({a, b});
    }

    // ㅈ
    for (int i = 1; i <= N; i++) {
        int degree = adj[i].size();
        if (degree >= 3) {
            g_cnt += combi_num(degree);
        }
    }

    // ㄷ
    for (auto e : edge) {
        int a = e.first;
        int b = e.second;

        if (adj[a].size() > 1 && adj[b].size() > 1) {
            d_cnt += 1LL * (adj[a].size() - 1) * (adj[b].size() - 1);
        }
    }

    if (d_cnt > 3 * g_cnt) {
        cout << "D" << "\n";
    } else if (d_cnt < 3 * g_cnt) {
        cout << "G" << "\n";
    } else {
        cout << "DUDUDUNGA" << "\n";
    }

    return 0;
}
