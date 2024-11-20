#include<bits/stdc++.h>
using namespace std;

int N, M;
vector<int> arr(1000001); // 윈도우에서 각 숫자의 갯수를 가지고있을 배열

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;

    int trigger = (9 * M + 9) / 10;
    queue<int> q;

    for (int i = 0; i < M; i++) {
        int num;
        cin >> num;
        q.push(num);

        arr[num]++;

        if (arr[num] >= trigger) {
            cout << "YES\n";
            return 0;
        }
    }
 
    for (int i = M; i < N; i++) {
        arr[q.front()]--;
        q.pop();

        int num;
        cin >> num;
        arr[num]++;
        q.push(num);

        if (arr[num] >= trigger) {
            cout << "YES\n";
            return 0;
        }
    }

    cout << "NO\n";
    return 0;
}