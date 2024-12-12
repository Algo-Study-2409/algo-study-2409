#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    vector<int> ht(n);
    for(auto &h : ht) cin >> h;
    
    vector<int> ans(n, 0);
    stack<int> s;
    
    for(int i = 0; i < n; i++){
        while(!s.empty() && ht[s.top()] < ht[i]){
            s.pop();
        }
        if(!s.empty()){
            ans[i] = s.top() + 1; 
        }
        s.push(i);
    }
    
    for(int i = 0; i < n; i++){
        cout << ans[i] << ' ';
    }
}
