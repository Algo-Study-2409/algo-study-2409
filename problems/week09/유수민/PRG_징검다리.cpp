#include <bits/stdc++.h>
using namespace std;

vector<int> rock;
int N;

bool check(int mid){
    
    int cnt = 0;
    int last_idx = 0;
    
    for(int i=0; i<rock.size(); i++){
        if(rock[i] - last_idx < mid) {
            cnt++;
        }
        else{
            last_idx = rock[i];
        }
    }
    
    return cnt <= N;
    
}

int solution(int distance, vector<int> rocks, int n) {
    int answer = 0;
    rocks.push_back(distance);
    
    sort(rocks.begin(), rocks.end());
    
    for(auto r : rocks){
        rock.push_back(r);
    }
    N = n;
    
    int st = 1;
    int en = distance;
    
    while(st <= en){
        int mid = (st+en) / 2; // 각 바위 사이의 거리
  
        if(check(mid) == true) {
            answer = mid;
            st = mid + 1;
        }
        else{
            en = mid - 1;
        }
    }
    return answer;
}