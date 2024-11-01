#include <bits/stdc++.h>
using namespace std;

int n;
bool isUsed[21];
long long fac[21];

int main(){
    
    int cmd;
    cin >> n;
    cin >> cmd;

    // 팩토리얼 미리 저장
    fac[0] = 1;
    for(int i=1; i<=n; i++){
        fac[i] = i * fac[i-1];
    }

    // 순열 찾기
    if(cmd == 1) {
        long long k;
        cin >> k;
        k--;

        vector<int> ans;
        for(int i=1; i<=n; i++){
            int cnt = k / fac[n-i]; // 넘어간 번호 개수
            k %= fac[n-i]; // 남은 번호

            int num = 1;
            for(int j=0; j<=cnt; num++){
                if(!isUsed[num]) j++;
            }
            num--;   
            ans.push_back(num);
            isUsed[num] = true;
        }
        for(auto e : ans) cout << e << " ";
    }
    // 순열의 번호 찾기
    else {
        vector<int> sequence(n);
        for (int i = 0; i < n; i++) cin >> sequence[i];
        
        long long idx = 0;

        for(int i=0; i<n; i++){
            int num = sequence[i];
            int cnt = 0;

            for(int j=1; j<num; j++){
                if(!isUsed[j]) cnt++;
            }

            idx += cnt * fac[n-1-i];
            isUsed[num] = true;
        }

        cout << idx+1;
    }

    return 0;

}