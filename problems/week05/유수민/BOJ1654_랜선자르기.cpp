#include<bits/stdc++.h>
using namespace std;


vector<long long> lan;

int main(){
    int k, n;
    cin >> k >> n;

    long long temp;
    long long MX = -1;
    for(int i=0; i<k; i++){
        cin >> temp;
        lan.push_back(temp);

        MX = max(MX, temp);
    }

    long long st = 1;
    long long en = MX;

    long long ans = -1;

    while(st < en){
        long long mid = (st + en + 1) / 2;
        int cnt = 0;
        for(auto e : lan){
            cnt += (e / mid);
        }

        if(cnt < n) en = mid - 1;
        else if(cnt >= n) st = mid;
    }

    cout << st;

    return 0;


}