#include<bits/stdc++.h>
using namespace std;

int n;

int game[51][10];
vector<vector<int>> turn;
bool base[4];
int mx = INT_MIN;


void calc(int idx){

    int score = 0;
    int round = 0;
    int cur_player = turn[idx][round];
    int out_count = 0;
    int cur_innings = 1;
    fill(base, base+4, false);

    while(1){

        int res = game[cur_innings][cur_player];
        switch(res){
            case 1 :
                if(base[3]){
                    score++;
                    base[3] = false;
                }
                for(int i=2; i>=1; i--){
                    if(base[i]){
                        base[i+1] = true;
                        base[i] = false;
                    }
                }
                base[1] = true;
                break;
            case 2 :
                for(int i=3; i>=2; i--){
                    if(base[i]){
                        score++;
                        base[i] = false;
                    }
                }
                if(base[1]==true) {
                    base[3] = true;
                    base[1] = false;
                }
                base[2] = true;
                break;
            case 3 :
                for(int i=1; i<=3; i++){
                    if(base[i]) score++;
                    base[i] = false;
                }
                base[3] = true;
                break;
            case 4 :
                for(int i=1; i<=3; i++){
                    if(base[i]) score++;
                    base[i] = false;
                }
                score++;
                break;
            case 0 :
                out_count++;
                break;
        }
        if(out_count == 3) {
            cur_innings++;
            out_count = 0;
            fill(base, base+4, false);
        }
        if(cur_innings > n) break;
        round = (round == 8 ? 0 : round+1);
        cur_player = turn[idx][round];
    }

    mx = max(mx, score);
}

int main(){
    cin >> n;
    
    for(int i=1; i<=n; i++){
        for(int player=1; player<=9; player++){
            cin >> game[i][player];
        }
    }


    // { _ _ _ 1 _ _ _ _ _ _}
    // {2, 3, 4, 5, 6, 7 ,8 ,9} -> 브루트포스
    vector<int> brute = {2,3,4,5,6,7,8,9};
    do{
        vector<int> temp;
        for(int i=0; i<brute.size(); i++){
            if(i==3) temp.push_back(1);
            temp.push_back(brute[i]);
        }
        turn.push_back(temp);

    }while(next_permutation(brute.begin(), brute.end()));

    for(int i=0; i<turn.size(); i++){
        calc(i);
    }
    
    cout << mx;
}