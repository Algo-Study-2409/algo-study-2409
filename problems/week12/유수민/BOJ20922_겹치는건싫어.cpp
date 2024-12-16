#include <bits/stdc++.h>
using namespace std;

int isIn[100001];
vector<int> v;

int main(){

    int n, k;
    cin >> n >> k;
    int temp;
    for(int i=1; i<=n; i++){
        cin >> temp;
        v.push_back(temp);
    }

    int ans = 0;

    int st = 0, en = 0;
    isIn[v[en]]++;
    while(en < n && st < n){
        if(isIn[v[en]] <= k){
            ans = max(ans, en-st);
            en++;
            isIn[v[en]]++;
        }
        else{
            isIn[v[st]]--;
            st++;
        }
    }

    cout << ans+1;

}