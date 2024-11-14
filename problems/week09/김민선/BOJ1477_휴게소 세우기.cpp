#include<bits/stdc++.h>
using namespace std;

int N, M, L;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M >> L;

    vector<int> v(N);
    for (int i = 0; i < N; i++) {
        cin >> v[i];
    }
    v.push_back(0);
    v.push_back(L);

    sort(v.begin(), v.end());

    int left = 1;
    int right = L;
    int mid;
    int ans = 987654321;

    while (left <= right) {
        int cnt = 0; // 휴개소 개수
        mid = (left + right) / 2;

        for (int i = 1; i < v.size(); i++) {
            int diff = v[i] - v[i - 1];
            int tmp = diff / mid;
            
            if (tmp > 0) {
                if (diff % mid == 0) {
                    tmp--;
                }
                cnt += tmp;
            }
        }

        if (cnt > M) {
            left = mid + 1;
        } else {
            right = mid - 1;
            ans = min(ans, mid);
        }
    }

    cout << ans << "\n";
    return 0;
}