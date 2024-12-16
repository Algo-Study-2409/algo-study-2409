#include<bits/stdc++.h>
using namespace std;

int cnt[100001];

int main(){
    int n;
    cin >> n;
    vector<int> v(n);
    for(auto &e : v) cin >> e;
    
    long long ans = 0;
    int st = 0;
    
    for(int en = 0; en < n; en++){
        
        while(cnt[v[en]] > 0){
            cnt[v[st]]--;
            st++;
        }
        cnt[v[en]]++;
        ans += (en-st+1);
    }

    cout << ans;
}