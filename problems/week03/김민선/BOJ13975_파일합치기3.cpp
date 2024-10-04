#include<bits/stdc++.h>
using namespace std;

/*
    - 612ms
    - 우선순위 큐
    - i) 가장 작은 두 묶음을 합침
    - ii) 합친 묶음을 다시 큐에 넣음
    - iii) 큐에 하나의 묶음만 남을 때까지 반복
*/

#define ll long long

priority_queue<ll, vector<ll>, greater<ll>> pq;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int T;
    cin >> T;

    int k;
    ll num;
    while (T--) {
        cin >> k;
        while (k--) {
            cin >> num;
            pq.push(num);
        }

        ll sum = 0;
        ll num1;
        ll num2;
        while (pq.size() > 1) {
            num1 = pq.top();
            pq.pop();
            num2 = pq.top();
            pq.pop();

            sum += num1 + num2;
            cout << num1 << " " << num2 << " " << sum << "\n";
            pq.push(num1 + num2);
        }
        pq.pop();
        cout << sum << "\n";
    }
}