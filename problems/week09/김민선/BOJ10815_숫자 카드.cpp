#include <bits/stdc++.h>
using namespace std;

int N, M;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    vector<int> sang(N);

    for (int i = 0; i < N; i++) {
        cin >> sang[i];
    }

    sort(sang.begin(), sang.end());

    cin >> M;

    for (int i = 0; i < M; i++) {
        int target;
        cin >> target;
        int left = 0;
        int right = N - 1;
        int mid;
        
        while (left <= right) {
            mid = (left + right) / 2;

            if (sang[mid] == target) {
                break;
            } else if (sang[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (sang[mid] == target) {
            cout << 1 << " ";
        } else {
            cout << 0 << " ";
        }
    }

    return 0;
}
