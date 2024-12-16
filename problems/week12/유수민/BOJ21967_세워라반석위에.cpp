#include <bits/stdc++.h>
using namespace std;

int arr[1000001];
int isIn[11];

bool check(){
    int mn = INT_MAX;
    int mx = INT_MIN;
    for(int i=1; i<=10; i++){
        if(isIn[i]>0){
            mx = max(mx, i);
            mn = min(mn, i);
        }
    }
    if(mx - mn <= 2) return true;
    else return false;
}
int main(){

    int n;
    cin >> n;

    for(int i=1; i<=n; i++){
        cin >> arr[i];
    }

    int st=1, en=1;
    int ans = 0;

    isIn[arr[en]] = 1;
    while(st <= n && en <= n){
        if(check()) {
            ans = max(ans, en-st+1);
            en++;
            isIn[arr[en]]++;
        }
        else {
            isIn[arr[st]]--;
            st++;
        }
    }

    cout << ans;
}