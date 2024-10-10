#include<bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define X first
#define Y second

int nCity, mRoad;
int kk;
vector<int> people;
int cost[201][201];
vector<pii> ans;

int main(){

    for(int i=0; i<201; i++) fill(cost[i], cost[i]+201, -1);

    cin >> nCity >> mRoad;

    int a, b, c;
    for(int i=0; i<mRoad; i++){
        cin >> a >> b >> c;
        cost[a][b] = c;
    }

    for(int i=1; i<=nCity; i++){
        cost[i][i] = 0;
    }

    cin >> kk;
    for(int i=0; i<kk; i++){
        int st;
        cin >> st;
        people.push_back(st);
    }

    for(int k=1; k<=nCity; k++){
        for(int i=1; i<=nCity; i++){
            for(int j=1; j<=nCity; j++){
                if(cost[i][k] == -1 || cost[k][j] == -1) continue;
                if(cost[i][j] == -1) cost[i][j] = cost[i][k]+cost[k][j];
                else cost[i][j] = min(cost[i][j], cost[i][k]+cost[k][j]);
            }

        }
    }

    for(int i=1; i<=nCity; i++){
        int MX = INT_MIN;
        for(int j=1; j<=nCity; j++){
            if(cost[i][j] == -1 || cost[j][i] == -1) continue;
            int temp = (cost[i][j] + cost[j][i]);
            MX = max(temp, MX);
        }
        ans.push_back({MX, i}); 
    }

    sort(ans.begin(), ans.end());

    // for(auto e : ans) cout << e.X << " " << e.Y << " \n";

    int cc = ans[0].X;
    for(int i=0; i<ans.size(); i++){
        if(ans[i].X == cc) cout << ans[i].Y << " "; 
    }

    return 0;

}
