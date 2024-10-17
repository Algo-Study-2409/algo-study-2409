#include <bits/stdc++.h>
using namespace std;

#define pii pair<int,int>
#define X first
#define Y second

vector<pii> v;
int lastIdx;
int dir = 1; // 1 앞 -1 뒤
deque<int> dq;
vector<int> tmp;

int main(){

    int N, Q, k;
    cin >> N >> Q >> k;

    lastIdx = Q + 1;
    for(int i=0; i<Q; i++){
        int cmd, p = -1; // 1오름차순, 2업무처리순서
        cin >> cmd;

        if(cmd == 0){
            cin >> p;
        }
        v.push_back({cmd, p});

        if(cmd == 1) lastIdx = i;  
    }

    

    if(lastIdx == Q+1){
        for(int i=0; i<Q; i++){
            if(v[i].X == 0){
                if(dir == 1) dq.push_front(v[i].Y);
                else dq.push_back(v[i].Y);
            }
            else{
                dir *= -1;
            }          
        }
    }
    else{
        for(int i=0; i<Q; i++){
            if(i < lastIdx){
                if(v[i].X == 0) tmp.push_back(v[i].Y);
                else if(v[i].X == 2) dir *= -1;
            }    
            else if(i == lastIdx){
                sort(tmp.begin(), tmp.end());
                for(int i=0; i<tmp.size(); i++){
                    dq.push_back(tmp[i]);
                    dir = 1;
                }
            }   
            else {
                if(v[i].X == 0){
                    if(dir == 1) dq.push_front(v[i].Y);
                    else dq.push_back(v[i].Y);
                }
                else{
                    dir *= -1;
                }
            }   
        }
    }
    for(int i=0; i<k-1; i++){
        if(dir == 1) dq.pop_front();
        else dq.pop_back();
    }

    cout << (dir == 1 ? dq.front() : dq.back());

    return 0;


}