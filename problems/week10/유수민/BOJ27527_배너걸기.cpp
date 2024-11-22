#include<bits/stdc++.h>
using namespace std;

int n, m;
int arr[200001];
int mp[1000001];


int main(){
    
    cin >> n >> m;
    
    for(int i=1; i<=n; i++){
        cin >> arr[i];
    }

    int standard = (9*m%10 == 0 ? 9*m/10 : 9*m/10+1);

    for(int i=1; i<=m; i++){
        mp[arr[i]]++;
        if(mp[arr[i]] >= standard){
            cout << "YES";
            return 0;
        }
    }

    for(int i=m+1; i<=n; i++){
        mp[arr[i-m]]--;
        mp[arr[i]]++;
        if(mp[arr[i]] >= standard){
            cout << "YES";
            return 0;
        }
    }

    cout << "NO";
}