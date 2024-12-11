#include <bits/stdc++.h>
using namespace std;
#define X first
#define Y second
#define pii pair<int,int>

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin >> n;
    int l, h;

    vector<pii> vec;
    int mx = 0;
    int ans = 0;
    for(int i=0; i<n; i++){
        cin >> l >> h;
        vec.push_back({l, h});
        mx = max(mx, h);
    }

    int r_mx_idx, l_mx_idx;
    

    sort(vec.begin(), vec.end());
    for(const pii v : vec){
        if(v.Y == mx){
            l_mx_idx = v.X;
            break;
        }
    }
    
    int idx = -1;
    int height = 0;
    for(const pii v : vec){
        if(v.Y == mx){
            ans += (v.X-idx)*height;
            break;
        }
        if(idx == -1){
            idx = v.X;
            height = v.Y;
            continue;
        }
        if(v.Y > height){
            ans += (v.X-idx)*height;
            idx = v.X;
            height = v.Y;
        }
    }

    sort(vec.begin(), vec.end(), greater<pii>());
    for(const pii v : vec){
        if(v.Y == mx){
            r_mx_idx = v.X;
            break;
        }
    }

    idx = -1;
    height = 0;
    for(const pii v : vec){
        if(v.Y == mx){
            ans += (idx-v.X)*height;
            break;
        }
        if(idx == -1){
            idx = v.X;
            height = v.Y;
            continue;
        }
        if(v.Y > height){
            ans += (idx-v.X)*height;
            idx = v.X;
            height = v.Y;
        }
    }

    ans += (r_mx_idx - l_mx_idx + 1) * mx;

    cout << ans;
}