#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,int>

int num[1000001];
int dp[1000001];


int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int n;
    cin >> n;

    for(int i=1; i<=n; i++){
        cin >> num[i];
    }
    vector<pii> ans;
    stack<pii> s;

    s.push({num[1], 1}); //{순서, 오큰수}

    for(int i=2; i<=n; i++){
        pii cur = s.top();
        while(cur.X < num[i]){
            ans.push_back({cur.Y, num[i]});
            s.pop();
            if(s.empty()) break;
            cur = s.top();
        }
        s.push({num[i], i});
    }
    while(!s.empty()){
        pii cur = s.top();
        s.pop();
        ans.push_back({cur.Y, -1});
    }
    sort(ans.begin(), ans.end());

    for(auto e : ans) cout << e.Y << " ";

    return 0;
}