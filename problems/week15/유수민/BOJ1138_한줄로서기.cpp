#include<bits/stdc++.h>
using namespace std;

int n;
int cnt[11];
int row[11];

int main(){
    cin >> n;

    for(int i=1; i<=n; i++){
        cin >> cnt[i];
    }
    row[cnt[1]+1] = 1; // 1번 사람 순서 고정

    for(int i=2; i<n; i++){
        
        int zero = 0;
        for(int j=1; j<=n; j++){
            if(zero == cnt[i]) {
                zero = j;
                break;
            }
            if(row[j] == 0) zero++;
        }
        int idx = zero;
        while(row[idx] != 0){
            idx++;
        }
        row[idx] = i;
    }
    for(int i=1; i<=n; i++){
        if(row[i] == 0){
            row[i] = n;
            break;
        }
    }

    for(int i=1; i<=n; i++){
        cout << row[i] << " ";
    }







}