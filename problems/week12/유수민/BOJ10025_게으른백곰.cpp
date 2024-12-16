#include<bits/stdc++.h>
using namespace std;

int ice[1000001];

int main(){
    int n, k;
    cin >> n >> k;

    int g, x;
    vector<int> v;
    for(int i=0; i<n; i++){
        cin >> g >> x;
        ice[x] = g;
        v.push_back(x);
    }

    sort(v.begin(), v.end());
    
    int ans = ice[v[0]];
    int cnt = ans;

    int st = 0, en = 1;


    while(st < v.size() && en < v.size()){
        int x1 = v[st];
        int x2 = v[en];

        if(x2 - x1 <= 2 * k){
            cnt += ice[v[en]];
            ans = max(ans, cnt);
            en++;
        }
        else{
            cnt -= ice[v[st]];
            st++;
        }
    }
    cout << ans;

}