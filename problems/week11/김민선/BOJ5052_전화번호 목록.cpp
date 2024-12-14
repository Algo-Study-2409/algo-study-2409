#include<bits/stdc++.h>
using namespace std;

struct trie_node {
    trie_node* children[10]; // 0 ~ 9
    bool is_end;

    trie_node() {
        is_end = false;
        for (int i = 0; i < 10; i++) {
            children[i] = nullptr;
        }
    }
};

class Trie
{
public:
    trie_node* root;
    Trie() {
        root = new trie_node();
    }
    
    /*
        입력 시 접두사 없으면 true
    */
    bool insert_check(string num) {
        trie_node* cur_node = root;
        for (char n : num) {
            trie_node* next_node = cur_node->children[n - '0'];
            if (next_node == nullptr) {
                next_node = new trie_node();
                cur_node->children[n - '0'] = next_node;
            }
            cur_node = next_node;
            if (cur_node->is_end)
                return false;
        }
        cur_node->is_end = true;
        return true;
    }
};


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int t, n;

    cin >> t;

    while (t--) {
        cin >> n;
        vector<string> arr;

        for (int i = 0 ; i < n; i++) {
            string num;
            cin >> num;
            arr.push_back(num);
        }

        sort(arr.begin(), arr.end());

        Trie* trie = new Trie();
        bool flag = false;
        for (int i = 0; i < n; i++) {
            if (flag = !trie->insert_check(arr[i])) {
                cout << "NO\n";
                break;
            }
        }

        if (!flag)
            cout << "YES\n";
    }
}