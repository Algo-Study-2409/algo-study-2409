#include <bits/stdc++.h>
using namespace std;

int main(){

    stack<char> s;
    string str;
    cin >> str;

    for(auto e : str){
        
        if(e == '('){
            s.push(e);
            
        }
        else if(e == ')'){
            if(s.empty() || s.top() != '('){
                s.push(e);
            }
            else s.pop();
        }
    }
    int ans = 0;
    while(!s.empty()){
        s.pop();
        ans++;
    }

    cout << ans;

}