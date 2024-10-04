#include<bits/stdc++.h>
using namespace std;

/*
    - 68ms
    - 구현
    - map, set을 이용하여 난이도에 따라 문제를 정렬
    - 문제를 추가, 추천, 푼 문제를 처리
*/

#define PII pair<int, int>

int N, M;
map<int, set<int>> mp; // L(난이도), P(문제 번호) 배열
unordered_map<int, int> problem_to_level; // 문제 번호 -> 난이도


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    int P, L;
    while (N--) {
        cin >> P >> L;
        mp[L].insert(P);
        problem_to_level[P] = L;
    }

    cin >> M;
    while (M--) {
        string s;
        cin >> s;

        if (s == "add") {
            cin >> P >> L;
            mp[L].insert(P);
            problem_to_level[P] = L;
            continue;
        }

        if (s == "recommend") {
            int x;
            cin >> x;

            if (x == 1) {
                cout << *mp.rbegin()->second.rbegin() << "\n";
            }

            if (x == -1) {
                cout << *mp.begin()->second.begin() << "\n";
            }
        }

        if (s == "solved") {
            cin >> P;
            L = problem_to_level[P];
            mp[L].erase(P);
            if (mp[L].empty()) {
                mp.erase(L);
            }
            problem_to_level.erase(P);
        }
    }
}