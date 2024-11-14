#include <bits/stdc++.h>
using namespace std;

int main(){
    long long T;

    cin >> T;

    int N, M;
    while(T--){
        int ans = 0;
        cin >> N >> M;
        vector<int> A;
        vector<int> B;
        int tmp;
        for(int i=0; i<N; i++){
            cin >> tmp;
            A.push_back(tmp);
        }
        for(int j=0; j<M; j++){
            cin >> tmp;
            B.push_back(tmp);
        }
        sort(A.begin(), A.end(), greater<int>());
        sort(B.begin(), B.end());

        for(auto a : A){

            int st = 0;
            while(B[st] < a){
                st++;
                ans++;
                if(st==B.size()) break;
            }
        }
        cout << ans <<"\n";
    }
}