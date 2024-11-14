#include <bits/stdc++.h>
using namespace std;

int n, m;

int main(){
    cin >> n;
    vector<int> ans;
    map<int,int> mp;

    int tmp;
    for(int i=0; i<n; i++){
        cin >> tmp;
        mp[tmp] = 1;
    }

    cin >> m;
    for(int i=0; i<m; i++){
        cin >> tmp;
        if(mp[tmp] == 1) ans.push_back(1);
        else ans.push_back(0);
    }

    for(auto e : ans) cout << e << " ";
}