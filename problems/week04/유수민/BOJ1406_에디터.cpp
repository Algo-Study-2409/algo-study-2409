#include<bits/stdc++.h>
using namespace std;

int main(){
    
    string str;
    cin >> str;

    int testcase;
    cin >> testcase;

    list<char> l;
    for(auto e : str) l.push_back(e);
    list<char>::iterator it = l.end();

    char cmd;
    char c;
    while(testcase--){
        cin >> cmd;

        if(cmd == 'L' && it != l.begin()){
            it--;
        }
        else if(cmd == 'D' && it != l.end()){
            it++;
        }
        else if(cmd == 'B' && it != l.begin()){
            it--;
            it = l.erase(it);
        }
        else if(cmd == 'P'){
            cin >> c;
            l.insert(it, c);
        }
    }

    for(auto e : l){
        cout << e;
    }

    return 0;
}