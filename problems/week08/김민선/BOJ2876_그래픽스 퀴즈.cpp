#include<bits/stdc++.h>
using namespace std;

/*
    - 8ms
    - 지목한 학생들에게 모두 같은 등급을 줌
        - 다만 학생이 원래 받을 등급을 주는 거임
    - 한 등급만 줄 수 있으므로 연속으로 같은 등급을 줄 수 있는 학생 수를 구함
*/

int N;
int arr[100001][6] = {0, };
int max_cnt[6] = {0, };

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    for (int i = 1; i <= N; i++) {
        int a, b;
        cin >> a >> b;

        arr[i][a] = arr[i - 1][a] + 1;
        arr[i][b] = arr[i - 1][b] + 1;

        for (int j = 1; j <= 5; j++) {
            if (j == a || j == b) {
                continue;
            }

            arr[i][j] = 0;
        }

        max_cnt[a] = max(max_cnt[a], arr[i][a]);
        max_cnt[b] = max(max_cnt[b], arr[i][b]);
    }

    int max_student = 0;
    int grade = 0;

    for (int i = 1; i <= 5; i++) {
        if (max_cnt[i] > max_student) {
            max_student = max_cnt[i];
            grade = i;
        }
    }

    cout << max_student << " " << grade << "\n";

    return 0;
}