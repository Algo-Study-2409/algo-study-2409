#include <bits/stdc++.h>
using namespace std;

/*
    - 미리 정렬하여 dfs 사용해 여행 경로 구함
        - map의 key는 자동으로 정렬됨
*/

void dfs(map<string, vector<string>> &adj, string start, vector<string> &answer) {
    for (auto &a : adj[start]) {
        if (a == "")
            continue;
        string next = a;
        a = "";
        dfs(adj, next, answer);
    }

    answer.push_back(start);
}

vector<string> solution(vector<vector<string>> tickets) {
    vector<string> answer;
    map<string, vector<string>> adj;

    for (auto &ticket : tickets) {
        adj[ticket[0]].push_back(ticket[1]);
    }

    for (auto &a : adj) {
        sort(a.second.begin(), a.second.end());
    }

    dfs(adj, "ICN", answer);
    reverse(answer.begin(), answer.end());

    return answer;
}