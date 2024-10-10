#include <bits/stdc++.h>
using namespace std;

/*
    - 60ms
    - 리스트
        - 양방향 연결 리스트로 구현되어 있음
        - 삽입, 삭제가 O(1)에 가능
*/

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    string s;
    int M;
    cin >> s >> M;

    list<char> editor(s.begin(), s.end());
    auto cursor = editor.end();

    for (int i = 0; i < M; i++) {
        char cmd;
        cin >> cmd;

        if (cmd == 'L') {
            if (cursor != editor.begin()) {
                cursor--;
            }
        } else if (cmd == 'D') {
            if (cursor != editor.end()) {
                cursor++;
            }
        } else if (cmd == 'B') {
            if (cursor != editor.begin()) {
                cursor = editor.erase(prev(cursor));
            }
        } else if (cmd == 'P') {
            char c;
            cin >> c;
            editor.insert(cursor, c);
        }
    }

    for (char c : editor) {
        cout << c;
    }
}
