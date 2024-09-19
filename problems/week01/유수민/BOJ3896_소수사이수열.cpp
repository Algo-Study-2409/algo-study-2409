#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int t, tmp;
    vector<int> v;

    cin >> t;
    for(int i=0; i<t; i++){
        cin >> tmp;
        v.push_back(tmp);
    }

    for(const int& c : v){
        bool ch = true;
        for(int i=2; i<=int(sqrt(c)); i++){
            if(c%i == 0){
                ch = false;
                break;
            }
        }
        if(ch == true){
            cout << 0 << "\n";
            continue;
        }

        int st = c+1;
        while(1){
            int t = st;
            bool ch = false;
            for(int i=2; i<=int(sqrt(t)); i++){
                if(st%i == 0){
                    st++;
                    ch = true;
                    break;
                }
            }
            if(ch == false) break;      
        }
        int en = c-1;
        while(1){
            int t = en;
            bool ch = false;
            for(int i=2; i<=int(sqrt(t)); i++){
                if(en%i == 0){
                    en--;
                    ch = true;
                    break;
                }
            }
            if(ch == false) break;
        }

        cout << st-en << "\n";
    }

    return 0;
}