#include <bits/stdc++.h>
using namespace std;

int n, c;
vector<int> house;

// 가장 인접 거리 mid 가능?
bool check(int mid){
    int cnt = 1; // 첫 번째 집에 공유기를 설치하고 시작
    int last_idx = house[0]; 

    for(int i = 1; i < n; i++) {
        if(house[i] - last_idx >= mid) { 
            cnt++;
            last_idx = house[i];
        }
    }
    return cnt >= c;

}

int main(){
    cin >> n >> c;

    int tmp;
    for(int i=0; i<n; i++){
        cin >> tmp;
        house.push_back(tmp);
    }
    sort(house.begin(), house.end());
    
    int ans = 0;
    int st = 1;
    int en = house[n-1] - house[0];

    while(st <= en){
        int mid = (st+en) / 2; // 가장 인접한 공유기 사이의 거리를 최대로 만들자.

        if(check(mid) == true){
            st = mid + 1;
            ans = max(ans, mid);
        }
        else{
            en = mid-1;
        }    
    }
    cout << ans;
}