#include<bits/stdc++.h>
using namespace std;

int N;

int arr[51][9]; // 각 이닝 별 선수들의 결과
int order[9]; // 타순
int visited[9];

int ans = 0;

void dfs (int cnt) {
    if (cnt == 9) {
        int score = 0;
        int idx = 0;

        for (int i = 0; i < N; i++) {
            int out = 0;
            int base[4] = {0,};

            while (out < 3) {
                int result = arr[i][order[idx]];

                if (result == 0) {
                    out++;
                    idx = (idx + 1) % 9;
                    continue;
                }

                for (int j = 3; j >= 1; j--) {
                    if (base[j] == 1) {
                        if (j + result >= 4) {
                            score++;
                            base[j] = 0;
                        } else {
                            base[j + result] = 1;
                            base[j] = 0;
                        }
                    }
                }

                if (result == 4) {
                    score++;
                } else {
                    base[result] = 1;
                }

                idx = (idx + 1) % 9;
            }
        }

        ans = max(ans, score);

        return;
    }

    if (cnt == 3) {
        dfs(cnt + 1);
        return;
    }

    for (int i = 1; i < 9; i++) {
        if (visited[i] == 0) {
            visited[i] = 1;
            order[cnt] = i;
            dfs(cnt + 1);
            visited[i] = 0;
        }
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < 9; j++) {
            cin >> arr[i][j];
        }
    }

    visited[0] = 1;
    order[3] = 0;
    dfs(0);

    cout << ans << "\n";
    return 0;
}