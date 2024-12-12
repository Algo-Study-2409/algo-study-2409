#include<bits/stdc++.h>
using namespace std;

int n, k;
vector<int> idx[10];
int cnt[10];
bool del[500001];

int main(){
    cin >> n >> k;
    string num;
    cin >> num;
    
    stack<char> s;

    for(auto e : num){
        while(!s.empty() && s.top() < e && k > 0){
            s.pop();
            k--;
        }
        s.push(e);
    }

    while(k > 0 && !s.empty()){
        s.pop();
        k--;
    }

    string res = "";
    while(!s.empty()){
        res += s.top();
        s.pop();
    }
    reverse(res.begin(), res.end());
    
    cout << res;

}