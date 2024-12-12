#include <bits/stdc++.h>
using namespace std;

int main(){
    int n;
    cin >> n;

    vector<int> v;
    int temp;
    for(int i=0; i<n; i++){
        cin >> temp;
        v.push_back(temp);
    }

    stack<int> s;
    vector<char> ans;
    int cur = 1;

    for(auto e : v){
        if(s.empty()){

            if(cur > e){
                cout << "NO";
                return 0;
            }
            else if(cur == e){
                ans.push_back('+');
                ans.push_back('-');
                cur++;
            }
            else if(cur < e){
                while(cur < e){
                    s.push(cur++);
                    ans.push_back('+');
                }
                ans.push_back('+');
                ans.push_back('-');
                cur++;
            }

        }
        else{
            if(s.top() == e){
                s.pop();
                ans.push_back('-');
            }
            else if(s.top() > e){
                while(s.top() > e){
                    s.pop();
                    ans.push_back('-');
                }
                if(s.top() == e){
                    ans.push_back('-');
                }
                else{
                    cout << "NO";
                    return 0;
                }

            }
            else if(s.top() < e){
                if(cur > e){
                    cout << "NO";
                    return 0;
                }
                else if(cur == e){
                    ans.push_back('+');
                    ans.push_back('-');
                    cur++;
                }
                else if(cur < e){
                    while(cur < e){
                        s.push(cur++);
                        ans.push_back('+');
                    }
                    ans.push_back('+');
                    ans.push_back('-');
                    cur++;
                }
            }
        }

    }

    for(auto e : ans){
        cout << e << "\n";
    }

}