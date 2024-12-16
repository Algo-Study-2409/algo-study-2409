#include<bits/stdc++.h>
using namespace std;

struct trie_node {
    bool is_end;
    trie_node* children[26];

    trie_node() {
        is_end = false;

        for (int i = 0; i < 26; i++) {
            children[i] = nullptr;
        }
    }

};

class Trie {
public:
    trie_node* root;

    Trie () {
        root = new trie_node();
    }

    void insert(string str) {
        trie_node* cur_node = root;
        for (char c : str) {
            trie_node* next_node = cur_node->children[c - 'a'];
            if (next_node == nullptr) {
                next_node = new trie_node();
                cur_node->children[c - 'a'] = next_node;
            }
            cur_node = next_node;
        }
        cur_node->is_end = true;
    }

    bool search(string str) {
    trie_node* cur_node = root;
    for (char c : str) {
            trie_node* next_node = cur_node->children[c - 'a'];
            if (next_node == nullptr) {
                return false;
            }
            cur_node = next_node;
        }
        return true;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m;
    cin >> n >> m;

    Trie trie;

    for (int i = 0; i < n; i++) {
        string str;
        cin >> str;
        trie.insert(str);
    }

    int cnt = 0;

    for (int i = 0; i < m; i++) {
        string str;
        cin >> str;
        if (trie.search(str)) {
            cnt++;
        }
    }

    cout << cnt;
}