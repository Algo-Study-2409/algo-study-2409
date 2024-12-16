#include <bits/stdc++.h>
using namespace std;

const int MX = 10000 * 500 + 5;

const int ROOT = 1;
int unused = 2;

bool isTail[MX]; 
int nxt[MX][26];


int n, m;

int c2i(char c){
    return c - 'a';
}

void insert(string& str){
    
    int cur = ROOT;

    for(auto s : str){
        if(nxt[cur][c2i(s)] == -1){
            nxt[cur][c2i(s)] = unused++;
        }
        cur = nxt[cur][c2i(s)];
    }
    isTail[cur] = true;

}

bool find(string& str){

    int cur = ROOT;
    for(auto s : str){
        if(nxt[cur][c2i(s)] == -1) return false;
        cur = nxt[cur][c2i(s)];
    }
    return true;

}

int main(){
    cin >> n >> m;
    for(int i=0; i<MX; i++) fill(nxt[i], nxt[i]+26, -1);
    string str;


    for(int i=0; i<n; i++){
        cin >> str;
        insert(str);
    }

    int ans = 0;

    for(int i=0; i<m; i++){
        cin >> str;
        if(find(str)) ans++;
    }

    cout << ans;
}