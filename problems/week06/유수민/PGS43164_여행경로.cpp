#include <bits/stdc++.h>
using namespace std;
#define X first
#define Y second
#define pii pair<int,int>

vector<string> answer;
map<string, int> mp;
map<int, string> mp2;
int t = 2;
vector<int> route[10001];
int num;
bool isUsed[10001][10001];
bool isAnswer = false;

void dfs(int node, vector<int>& v){

    if(v.size()-1 == num) {
        for(auto k : v) answer.push_back(mp2[k]);
        isAnswer = true;
    }
    for(int i=0; i<route[node].size(); i++){
        
        if (isUsed[node][i]) continue;
        v.push_back(route[node][i]);
        isUsed[node][i] = true;
        dfs(route[node][i], v);
        if(!isAnswer){
            isUsed[node][i] = false;
            v.pop_back();
        }
        
    }
}


vector<string> solution(vector<vector<string>> tickets) {
    
    num = tickets.size();
    
    mp["ICN"] = 1;
    mp2[1] = "ICN";
    for(auto e : tickets){
        if(e[0] != "ICN" && mp[e[0]] < 1) {
            mp[e[0]] = t;
            mp2[t] = e[0];
            t++;
        }
        if(e[1] != "ICN" && mp[e[1]] < 1) {
            mp[e[1]] = t;
            mp2[t] = e[1];
            t++;
        }
    }
    
    sort(tickets.begin(), tickets.end());
    
    
    for(auto e : tickets){
        int st = mp[e[0]];
        int en = mp[e[1]];
        route[st].push_back(en);
    }
    
    vector<int> vec;
    vec.push_back(1);
    
    dfs(1, vec);
      

    return answer;
}