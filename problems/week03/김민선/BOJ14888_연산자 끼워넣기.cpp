#include<bits/stdc++.h>
using namespace std;

/*
    - 0ms
    - 백트래킹, 브루트포스
    - 연산자의 개수를 줄여가며 모든 경우의 수를 탐색
    - 연산자의 개수가 0이 되면 계산
*/

#define ADD 0
#define SUB 1
#define MUL 2
#define DIV 3

int N;
vector<int> arr;
// 남은 연산자의 개수
int operator_cnt[] = {0, 0, 0, 0}; // +, -, *, /
int res_min = 1234567890;
int res_max = -1234567890;

void dfs(int idx, int sum) {
    if (idx == N) {
        res_min = min(res_min, sum);
        res_max = max(res_max, sum);
        return;
    }

    if(operator_cnt[ADD] > 0) {
        operator_cnt[ADD]--;
        dfs(idx + 1, sum + arr[idx]);
        operator_cnt[ADD]++;
    }

    if(operator_cnt[SUB] > 0) {
        operator_cnt[SUB]--;
        dfs(idx + 1, sum - arr[idx]);
        operator_cnt[SUB]++;
    }

    if(operator_cnt[MUL] > 0) {
        operator_cnt[MUL]--;
        dfs(idx + 1, sum * arr[idx]);
        operator_cnt[MUL]++;
    }

    if(operator_cnt[DIV] > 0) {
        operator_cnt[DIV]--;
        dfs(idx + 1, sum / arr[idx]);
        operator_cnt[DIV]++;
    }
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    int num;
    for (int i = 0; i < N; i++) {
        cin >> num;
        arr.push_back(num);
    }

    for (int i = 0; i < 4; i++) {
        cin >> operator_cnt[i];
    }

    dfs(1, arr[0]);

    cout << res_max << "\n";
    cout << res_min << "\n";
}