#include <bits/stdc++.h>
using namespace std;

int N, M, L;

// [성공] 이분탐색을 이용하여 풀이

int main(){
    cin >> N >> M >> L;
    
    vector<int> store;
    vector<int> between;

    int tmp;
    for(int i=0; i<N; i++){
        cin >> tmp;
        store.push_back(tmp);
    }    
    sort(store.begin(), store.end());

    int t = 0;
    for(auto s  : store){
        between.push_back(s-t);
        t = s;
    }
    if(t != L) between.push_back(L-t);

    int st = 1;
    int en = L;
    int ans = L;
    while(st <= en){
        int mid = (st+en)/2;
        int cnt = 0;
        for(auto b : between){
            cnt += (b-1)/mid;
        }
        if(cnt <= M) {
            ans = mid;
            en = mid-1;
        }
        else st = mid+1;
    }
    cout << ans;
}   