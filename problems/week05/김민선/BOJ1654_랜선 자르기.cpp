#include<bits/stdc++.h>
using namespace std;

/*
    - 4ms
    - 이분탐색
    - 랜선의 길이를 이분탐색으로 찾음
*/

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int K, N;
    cin >> K >> N;
    vector<long long> arr(K);
    long long max_len = 0;

    for (int i = 0; i < K; i++) {
        cin >> arr[i];
        max_len = max(max_len, arr[i]);
    }

    long long left = 1;
    long long right = max_len;
    long long ret = 0;

    while (left <= right) {
        long long mid = (left + right) / 2;
        long long cnt = 0;

        for(int i = 0; i < K; i++) {
            cnt += arr[i] / mid;
            if (cnt >= N)
                break;
        }

        if (cnt >= N) {
            ret = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    cout << ret << '\n';
    return 0;
}
