#include <bits/stdc++.h>
using namespace std;

#define pii pair<int,int>
#define pipi pair<int, pair<int,int>> 
#define pp pair<pair<int,int>, pair<int,int>>
#define X first
#define Y second

vector<int> scv;

int ans = INT_MAX;

bool vis2[61][61];
bool vis3[61][61][61];

int d1[6] = {9, 9, 3, 3, 1, 1};
int d2[6] = {3, 1, 9, 1, 3, 9};
int d3[6] = {1, 3, 1, 9, 9, 3};
int d4[4] = {9, 3};
int d5[4] = {3, 9};


void fight2(){

    priority_queue<pipi, vector<pipi>, greater<pipi>> pq;
    pq.push({0, {scv[0], scv[1]}});
    vis2[scv[0]][scv[1]] = true;

    while(!pq.empty()){
        auto cur = pq.top();
        pq.pop();

        if(cur.Y.X == 0 && cur.Y.Y == 0){
            ans = min(ans, cur.X);
            continue;
        }

        for(int i=0; i<4; i++){
            int nx = cur.Y.X - d4[i];
            int ny = cur.Y.Y - d5[i];
            int cnt = cur.X;

            nx = nx < 0 ? 0 : nx;
            ny = ny < 0 ? 0 : ny;

            if(nx > ny) swap(nx, ny);
            if(vis2[nx][ny]) continue;

            pq.push({cnt+1, {nx, ny}});
            vis2[nx][ny] = true;

        }
    }

}

void fight3(){

    priority_queue<pp, vector<pp>, greater<pp>> pq;
    pq.push({{0, scv[0]}, {scv[1], scv[2]}});
    vis3[scv[0]][scv[1]][scv[2]] = true;

    while(!pq.empty()){

        auto cur = pq.top();
        pq.pop();

        if(cur.X.Y == 0 && cur.Y.X == 0 && cur.Y.Y == 0){
            ans = min(ans, cur.X.X);
            continue;
        }

        for(int i=0; i<6; i++){
            int nx = cur.X.Y - d1[i];
            int ny = cur.Y.X - d2[i];
            int nz = cur.Y.Y - d3[i];
            int cnt = cur.X.X;

            nx = nx < 0 ? 0 : nx;
            ny = ny < 0 ? 0 : ny;
            nz = nz < 0 ? 0 : nz;

            vector<int> temp;
            temp.push_back(nx);
            temp.push_back(ny);
            temp.push_back(nz);
            sort(temp.begin(), temp.end());
            nx = temp[0];
            ny = temp[1];
            nz = temp[2];
            
            if(vis3[nx][ny][nz]) continue;

            pq.push({{cnt+1, nx}, {ny, nz}});
            vis3[nx][ny][nz] = true;

        }
    }

}


int main(){

    int n;
    cin >> n;

    int p;

    for(int i=0; i<n; i++){
        cin >> p;
        scv.push_back(p);
    }

    if(n==1){
        cout << (p%9 == 0 ? p/9 : p/9+1);
        return 0;
    }

    sort(scv.begin(), scv.end());
    
    if(n==2){
        fight2();
    }
    else{
        fight3();
    }

    cout << ans;

    return 0;

}