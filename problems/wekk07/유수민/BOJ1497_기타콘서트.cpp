#include <bits/stdc++.h>
using namespace std;

int n, m;
string song[10];

bool cmp(pair<int,int> A, pair<int,int> B){
    if(A.first == B.first) return A.second < B.second;
    return A.first > B.first;
}

int cntPlay(string s){
    int cnt = 0;
    for(auto e : s){
        if(e == '1') cnt++;
    }
    return cnt;
}

string add(string s1, string s2){
    string res = "";
    if(s1.empty()) return s2;

    for(int i=0; i<m; i++){
        if(s1[i] == '0' && s2[i] == '0') res += "0";
        else res += "1";
    }
    return res;
}

string change(string s){
    string res = "";
    for(auto e : s){
        if(e == 'Y') res += "1";
        else res += "0";
    }
    return res;
}

int main(){
    cin >> n >> m;
    string guiter, s;

    for(int i=0; i<n; i++){
        cin >> guiter >> s;
        song[i] = change(s);
    }


    vector<pair<int,int>> v;

    for(int t = 1; t <= n; t++){ // n개중 t개를 고른다.
        vector<int> brute(n, 1);
        fill(brute.begin(), brute.begin()+n-t, 0);

        do{
            string sum = "";
            for(int i=0; i<n; i++){
                if(brute[i] == 1) sum = add(sum, song[i]);
            }
            // cout << sum << "\n";
            v.push_back({cntPlay(sum), t});
        }while(next_permutation(brute.begin(), brute.end()));
    }

    sort(v.begin(), v.end(), cmp);
    if(v[0].first == 0) cout << -1;
    else cout << v[0].second;
}