#include <bits/stdc++.h>
using namespace std;

int n, m;
int cost[11];
string dp[51];
vector<int> num[51];

string mknum(vector<int> v) {
    sort(v.begin(), v.end(), greater<int>());

    if (v.empty()) return "0";
    if (v[0] == 0) return "0";

    string str = "";
    for (auto e : v) str += to_string(e);
    return str;
}

bool bigger(string s1, string s2) {
    if (s1.length() == s2.length()) {
        for (int i = 0; i < s1.length(); i++) {
            if (s1[i] != s2[i])
                return s1[i] > s2[i];
        }
        return false;
    }
    return s1.length() > s2.length();
}

int main() {
    fill(dp, dp + 51, "-1");

    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> cost[i];

        if(dp[cost[i]] < to_string(i)){
            dp[cost[i]] = to_string(i);
            num[cost[i]].clear();
            num[cost[i]].push_back(i);
        }
    }
    cin >> m;

    for (int i = 1; i <= m; i++) {
        for (int t = 1; t <= i / 2; t++) {
            int r1 = t;
            int r2 = i - t;

            vector<int> temp;

            if (dp[r1] != "-1") {
                temp.insert(temp.end(), num[r1].begin(), num[r1].end());
            }
            if (dp[r2] != "-1") {
                temp.insert(temp.end(), num[r2].begin(), num[r2].end());
            }

            if (!temp.empty() && (bigger(mknum(temp), dp[i]) || dp[i] == "-1")) {
                dp[i] = mknum(temp);
                num[i] = temp;
            }
        }
    }

    cout << dp[m];

    return 0;
}
