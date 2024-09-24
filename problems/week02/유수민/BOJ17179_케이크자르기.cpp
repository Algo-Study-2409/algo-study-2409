#include <bits/stdc++.h>
using namespace std;

int n, m, l;
vector<int> cake;
vector<int> cut;
vector<int> res;

void binarySearch(){

    int st, en, mid;
    
    for(int i=0; i<n; i++){ // 자르는 횟수 cut[i]

        st = 1, en = l;
        int best = 0; // 최대 조각 길이
        int cuttedPosition;

        while(st<=en){
            mid = (st + en) / 2;

            int cuts = 0; // 지금까지 자른 횟수
            cuttedPosition = 0; // 마지막으로 잘린 조각의 끝 지점

            for(int j=0; j<m; j++){
                if(cake[j] - cuttedPosition >= mid){
                    cuts++;
                    cuttedPosition = cake[j];
                }
            }

            // 현재 자른 횟수(cuts)와 잘라야하는 횟수(cut[i]) 비교
            if(cuts == cut[i] && l-cuttedPosition < mid) {
                en = mid - 1;
            }
            else if(cuts < cut[i]){
                en = mid - 1; 
            }
            else{
                st = mid + 1;
            } 
        }
        res.push_back(en);
    }     
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m >> l;

    int tmp;
    for(int i=0; i<m; i++){
        cin >> tmp;
        cake.push_back(tmp);
    }
    for(int i=0; i<n; i++){
        cin >> tmp;
        cut.push_back(tmp);
    }

    binarySearch();

    for(const int& e : res) cout << e << "\n";

}


// 시간초과가 발생하는 풀이법 -> 완전 탐색 
// void calc(vector<int> brute){
//     int MX = -1;
//     do{
//         int MN = INT_MAX;
//         int t = 0;
//         for(int i=0; i<brute.size(); i++){
            
//             if(brute[i] == 1){
//                 MN = min(MN, cake[i]-t);
//                 t = cake[i];
//             }
//         }
//         MN = min(MN, l-t);
//         MX = max(MX, MN);
//     }while(next_permutation(brute.begin(), brute.end()));
//     res.push_back(MX);
// }