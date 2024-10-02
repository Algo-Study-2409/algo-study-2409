#include <bits/stdc++.h>
using namespace std;

int n;
int a, b, c, d;
vector<int> v;
vector<int> cmd;

int MN = INT_MAX;
int MX = INT_MIN;

int main(){
    cin >> n;
    int num;
    for(int i=0; i<n; i++){
        cin >> num;
        v.push_back(num);
    }
    cin >> a >> b >> c >> d;
    while(a--) cmd.push_back(1);
    while(b--) cmd.push_back(2);
    while(c--) cmd.push_back(3);
    while(d--) cmd.push_back(4);

    do{
        int cur = v[0];
        for(int i=0; i<cmd.size(); i++){
            if(cmd[i] == 1){
                cur += v[i+1];
            }
            else if(cmd[i] == 2){
                cur -= v[i+1];
            }
            else if(cmd[i] == 3){
                cur *= v[i+1];
            }
            else{
                cur /= v[i+1];
            }
        }
        MN = min(MN, cur);
        MX = max(MX, cur);

    }while(next_permutation(cmd.begin(), cmd.end()));

    cout << MX << "\n" << MN;
    
}