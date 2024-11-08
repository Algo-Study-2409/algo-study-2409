#include<bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define X first
#define Y second

int n;
int a, b;
int dp[100001][6];
vector<pii> table;
vector<pii> ans;

bool cmp(pii s, pii f){
    if(s.X == f.X) return s.Y < f.Y;
    else return s.X > f.X;
}

int main(){
    cin >> n;
    for(int i=1; i<=n; i++){
        cin >> a >> b;
        table.push_back({a, b});
    }

    dp[1][table[0].X] = 1;
    dp[1][table[0].Y] = 1;

    for(int i=1; i<table.size(); i++){
        int c1 = table[i].X;
        int c2 = table[i].Y;
        if(dp[i][c1] == 0) dp[i+1][c1] = 1;
        else dp[i+1][c1] = dp[i][c1] + 1;

        if(dp[i][c2] == 0) dp[i+1][c2] = 1;
        else dp[i+1][c2] = dp[i][c2] + 1;

    }

    for(int i=1; i<=n; i++){
        for(int c=1; c<=5; c++){
            ans.push_back({dp[i][c], c});
        }
    }



    sort(ans.begin(), ans.end(), cmp);

    cout << ans[0].X << " " << ans[0].Y;


}