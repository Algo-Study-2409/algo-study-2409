#include <bits/stdc++.h>
using namespace std;
#define X first
#define Y second
#define pii pair<int,int>

int N, P, L, M;
string cmd;
int x, p, l;
int problem[100001];

bool cmpBig(pii a, pii b){
    if(a.X == b.X) return a.Y > b.Y;
    else return a.X > b.X;
}

bool cmpSmall(pii a, pii b){
    if(a.X == b.X) return a.Y < b.Y;
    else return a.X < b.X;
}


int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    fill(problem, problem+100001, -1);
    priority_queue<pii> bq;
    priority_queue<pii, vector<pii>, greater<pii>> sq;
    cin >> N;
    for(int i=0; i<N; i++){
        cin >> P >> L;
        problem[P] = L;
        bq.push({L, P}); // {난이도, 문제번호}
        sq.push({L, P});
    }

    cin >> M;
    vector<int> ans;
    for(int i=0; i<M; i++){
        cin >> cmd;
        if(cmd == "recommend"){
            cin >> x;

            if(x == 1) {
                auto cur = bq.top();
                while(problem[cur.Y] == -1){
                    bq.pop();
                    cur = bq.top();
                }
                while(problem[cur.Y] != cur.X){
                    bq.pop();
                    cur = bq.top();
                }
                ans.push_back(cur.Y);

            }
            else{
                auto cur = sq.top();
                while(problem[cur.Y] == -1){
                    sq.pop();
                    cur = sq.top();
                }
                while(problem[cur.Y] != cur.X){
                    sq.pop();
                    cur = sq.top();
                }
                ans.push_back(cur.Y);
            }
        }
        else if(cmd == "add"){
            cin >> p >> l;
            problem[p] = l;
            sq.push({l, p});
            bq.push({l, p});
            
        }
        else{ // solved
            cin >> p;
            problem[p] = -1;
        }
        
    }

    for(auto e : ans) cout << e << "\n";

}