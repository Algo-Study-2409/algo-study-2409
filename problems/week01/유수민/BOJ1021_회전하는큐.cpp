#include <bits/stdc++.h>
using namespace std;

int n, m;
int MN = 0;

int f(deque<int> d, int target){
    int cur = d.front();
    int cnt = 0;
    while(cur != target){
        d.pop_front();
        d.push_back(cur);
        cnt++;
        cur = d.front();
    }
    return cnt;
}
int b(deque<int> d, int target){
    int cur = d.back();
    int cnt = 0;
    while(cur != target){
        d.pop_back();
        d.push_front(cur);
        cnt++;
        cur = d.back();
    }
    return cnt+1;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    vector<int> v;
    deque<int> deq;
    int tmp;

    cin >> n >> m;
    for(int i=0; i<m; i++) {
        cin >> tmp;
        v.push_back(tmp);
    }
    for(int i=1; i<=n; i++) deq.push_back(i);

    for(const int& target : v){
        int cnt1 = f(deq, target);
        int cnt2 = b(deq, target);

        if(cnt1 < cnt2){
            MN += cnt1;
            int cur = deq.front();
            while(cur != target){
                deq.pop_front();
                deq.push_back(cur);
                cur = deq.front();
            }
            deq.pop_front();
        }
        else{
            MN += cnt2;
            int cur = deq.back();
            while(cur != target){
                deq.pop_back();
                deq.push_front(cur);
                cur = deq.back();
            }
            deq.pop_back();
        }
    }
    cout << MN;
    return 0;
}