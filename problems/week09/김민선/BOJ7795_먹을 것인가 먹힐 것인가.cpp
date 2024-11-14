#include<bits/stdc++.h>
using namespace std;

int T, N, M;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> T;

    while (T--) {
        cin >> N >> M;

        vector<int> A(N);
        vector<int> B(M);

        for (int i = 0; i < N; i++) {
            cin >> A[i];
        }

        for (int i = 0; i < M; i++) {
            cin >> B[i];
        }

        sort(A.begin(), A.end());
        sort(B.begin(), B.end());

        int ret = 0;
        int minn = 0;
        for (int i = 0; i < N; i++) {
            for (int j = minn; j < M; j++) {
                if (A[i] <= B[j]) {
                    break;
                }

                minn++;
            }

            ret += minn;
        }

        cout << ret << "\n";
    }

    return 0;
}