#include<bits/stdc++.h>
using namespace std;

int N, C;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> C;

    vector<int> v(N);
    for (int i = 0; i < N; i++) {
        cin >> v[i];
    }
    sort(v.begin(), v.end());

    int left = 0;
    int right = v[N - 1] - v[0];
    int mid;
    int ans = 0;

    while (left <= right) {
        int cnt = 1;
        int prev = v[0];
        mid = (left + right) / 2;

        for (int i = 1; i < N; i++) {
            if (v[i] - prev >= mid) {
                cnt++;
                prev = v[i];
            }
        }

        if (cnt >= C) {
            left = mid + 1;
            ans = mid;
        } else {
            right = mid - 1;
        }
    }

    cout << ans << "\n";
    return 0;
}