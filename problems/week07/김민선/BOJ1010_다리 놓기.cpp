#include<bits/stdc++.h>
using namespace std;

/*
    - 0ms
    - 조합
    - 동쪽 사이트에서 서쪽 사이트를 선택하는 경우의 수
        - 다리가 교차 안하므로 동쪽 사이트 정하면 서쪽 사이트도 정해짐
        - mCn 구하기
*/ 

#define ll long long

int T, N, M;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> T;

    while (T--) {
        cin >> N >> M;

        ll ret = 1;

        if (N > M / 2) {
            N = M - N;
        }

        for (int i = 0; i < N; i++) {
            ret *= (M - i);
            ret /= (N - i);
        }

        cout << ret << "\n";
    }
}