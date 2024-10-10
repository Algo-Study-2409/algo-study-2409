#include <bits/stdc++.h>
using namespace std;

vector<int> idx(500001);       
vector<int> pre(500001);      
vector<long long> sum(500001);

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    string cmd;

    for (int i = 1; i <= n; i++) {
        cin >> cmd;

        if (cmd == "push") { 
            long long x;
            cin >> x;
            pre[i] = idx[i - 1];              
            sum[i] = sum[pre[i]] + x;        
            idx[i] = i;                       
        }
        else if (cmd == "pop") {
            idx[i] = pre[idx[i - 1]]; 
        }
        else if (cmd == "restore") { 
            int t;
            cin >> t;
            idx[i] = idx[t]; 
        }
        else if (cmd == "print") {
            idx[i] = idx[i - 1]; 
            cout << sum[idx[i]] << '\n'; 
        }
    }

    return 0;
}
