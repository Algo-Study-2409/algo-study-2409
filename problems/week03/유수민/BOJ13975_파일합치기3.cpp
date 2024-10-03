#include <bits/stdc++.h>
using namespace std;

int testcase;

int main(){

    cin >> testcase;

    vector<long long> res;
    while(testcase--){
        long long ans = 0;
        int K;
        cin >> K;
        priority_queue<long long, vector<long long>, greater<long long>> q;

        long long num;
        for(int i=0; i<K; i++){
            cin >> num;
            q.push(num);
        }
        
        while(!q.empty()){
            long long cur1 = q.top();
            q.pop();
            long long cur2 = q.top();
            q.pop();
            ans += (cur1+cur2);
            q.push(cur1+cur2);
            if(q.size() == 1) break;
        }
        res.push_back(ans);
    }

    for(auto e : res) cout << e << "\n";
    

}