#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,int>

vector<pii> chicken;
vector<pii> home;
int n, m;
int arr[51][51];
int mn = INT_MAX;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cin >> arr[i][j];
            if(arr[i][j] == 2) chicken.push_back({i, j});
            else if(arr[i][j] == 1) home.push_back({i, j});
        }
    }

    
    vector<int> brute(chicken.size(), 1);
    fill(brute.begin(), brute.begin()+m, 0);

    do{
        vector<pii> idx;
        int total = 0;
        for(const pii& h : home){
            int dist = INT_MAX;     

            for(int i=0; i<brute.size(); i++){
                if(brute[i] == 0) {
                    dist = min(dist, abs(h.X-chicken[i].X)+abs(h.Y-chicken[i].Y));
                }
            }
            total += dist;
        }
        mn = min(total, mn);
    }while(next_permutation(brute.begin(), brute.end()));

    cout << mn;

    return 0;

}