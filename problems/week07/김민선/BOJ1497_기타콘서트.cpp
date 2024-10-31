#include<bits/stdc++.h>
using namespace std;

/*
    - 0ms
    - 비트마스킹
    - dfs로 모든 경우의 수를 탐색
    - 모든 노래가 아닌 칠 수 있는 노래들을 최소한의 기타로 쳐야함
*/

#define ll long long

int N, M;

vector<ll> arr;
int ret = 11;

void dfs(int idx, ll bit, int cnt) {
    if (idx == N) {
        if (bit == (1 << M) - 1) {
            ret = min(ret, cnt);
        }
        return;
    }

    for (int i = idx; i < N; i++) {
        dfs(i + 1, bit | arr[i], cnt + 1);
        dfs(i + 1, bit, cnt);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;

    for (int i = 0; i < N; i++) {
        string s;
        cin >> s;
        cin >> s;
        ll bit = 0;
        for (int j = 0; j < M; j++) {
            if (s[j] == 'Y') {
                bit |= (1 << j);
            }
        }
    }

    dfs(0, 0, 0);

    if (ret == 11) {
        cout << -1 << "\n";
    } else {
        cout << ret << "\n";
    }
}