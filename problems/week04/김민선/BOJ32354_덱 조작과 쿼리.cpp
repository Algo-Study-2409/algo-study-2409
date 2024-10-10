#include<bits/stdc++.h>
using namespace std;

/*
    - 116ms
    - persistent 스택 사용
        - 자료구조를 변경하면서 이전 버전을 유지하는 자료구조
    - push, pop: 스택 처럼 동작
    - restore: 해당 명령을 했던 시점으로 덱을 되돌림
    - print: 현재 덱에 있는 모든 카드에 적힌 수의 합을 출력
*/

typedef long long ll;

// persistent 자료구조
// 자료구조를 변경하면서 이전 버전을 유지하는 자료구조
struct node_t {
    ll sum;
    int value;
    int prev_idx;
    
    node_t(ll sum, int value, int prev_idx) : sum(sum), value(value), prev_idx(prev_idx) {}
};

int versions[500001]; // i번째 명령 이후 노드 버전
vector<node_t*> nodes;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N;
    cin >> N;

    nodes.push_back(new node_t(0, 0, -1));
    versions[0] = 0;  // 0번째 명령은 초기 상태를 가리킴

    for (int i = 1; i <= N; i++) {
        string s;
        cin >> s;

        if (s == "push") {
            int num;
            cin >> num;
            nodes.push_back(new node_t(nodes[versions[i - 1]]->sum + num, num, versions[i - 1]));
            versions[i] = nodes.size() - 1; 
            continue;
        } 

        if (s == "pop") {
            int prev_idx = nodes[versions[i - 1]]->prev_idx;
            versions[i] = (prev_idx == -1) ? 0 : prev_idx;  // 덱이 비어있으면 초기 상태로
            continue;
        }

        if (s == "restore") {
            int version;
            cin >> version;
            versions[i] = versions[version];
            continue;
        }

        if (s == "print") {
            cout << nodes[versions[i - 1]]->sum << "\n";
            versions[i] = versions[i - 1];
            continue;
        }
    }
    return 0;
}
