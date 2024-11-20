#include<bits/stdc++.h>
using namespace std;

int N;

int alphabet[26] = {0,};

vector<string> words;

void print_words(int y, int idx) {

    if (idx == -1) {
        cout << words[y] << "\n";
        return;
    }

    for (int x = 0; x < words[y].size(); x++) {
        if (idx == x) {
            cout << "[" << words[y][x] << "]";
        } else {
            cout << words[y][x];
        }
    }
    cout << "\n";
}

int main() {
    cin >> N;
    // cin으로 입력받은 후에 getline 사용시 cin.ignore()를 사용해 남은 개행문자를 제거해줘야 함
    cin.ignore();

    for (int i = 0; i < N; i++) {
        string s;
        getline(cin, s, '\n');
        words.push_back(s);
    }

    for (int i = 0; i < N; i++) {
        bool flag = false;
        for (int j = 0; j < words[i].size(); j++) {
            if (j == 0) {
                if (alphabet[tolower(words[i][j]) - 'a'] == 0) {
                    flag = true;
                    alphabet[tolower(words[i][j]) - 'a'] = 1;
                    print_words(i, j);
                    break;
                }
                continue;
            }

            if (words[i][j] == ' ') {
                if (alphabet[tolower(words[i][j + 1]) - 'a'] == 0) {
                    flag = true;
                    alphabet[tolower(words[i][j + 1]) - 'a'] = 1;
                    print_words(i, j + 1);
                    break;
                }
                continue;
            }
        }

        if (!flag) {
            for (int j = 0; j < words[i].size(); j++) {
                if (words[i][j] == ' ') {
                    continue;
                }
                
                if (alphabet[tolower(words[i][j]) - 'a'] == 0) {
                    flag = true;
                    alphabet[tolower(words[i][j]) - 'a'] = 1;
                    print_words(i, j);
                    break;
                }
            }
        }

        if (!flag) {
            print_words(i, -1);
        }
    }
}