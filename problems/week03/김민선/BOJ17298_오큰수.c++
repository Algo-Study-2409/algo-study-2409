#include<bits/stdc++.h>
using namespace std;

/*
    - 180ms
    - 스택
    - 스택에는 아직 오큰수를 찾지 못한 원소들의 인덱스가 저장
    - 오큰수 찾으면 스택에서 pop
*/

int N;
vector<int> arr(1000001);
vector<int> res_arr(1000_001, -1);
vector<int> stk;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    for (int i = 0; i < N; i++) {
        while (!stk.empty() && arr[stk.back()] < arr[i]) {
            res_arr[stk.back()] = arr[i];
            stk.pop_back();
        }
        stk.push_back(i);
    }

    for (int i = 0; i < N; i++) {
        cout << res_arr[i] << " ";
    }
}