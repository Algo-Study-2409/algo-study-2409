#include <bits/stdc++.h>
using namespace std;

struct TrieNode {
    map<string, TrieNode*> children;
};

class Trie {
public:
    TrieNode* root;
    Trie() {
        root = new TrieNode();
    }
    
    void insert(const vector<string>& words) {
        TrieNode* current = root;
        for(const string& word : words) {
            if(current->children.find(word) == current->children.end()) {
                current->children[word] = new TrieNode();
            }
            current = current->children[word];
        }
    }
    
    void print(TrieNode* node, int depth) {
        for(auto a : node->children) {
            for(int i = 0; i < depth; i++) cout << "--";
            cout << a.first << '\n';
            print(a.second, depth + 1);
        }
    }
    
    void printAll() {
        print(root, 0);
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int N;
    cin >> N;
    
    Trie trie;
    while(N--) {
        int K;
        cin >> K;
        vector<string> words(K);
        for(int i = 0; i < K; i++) {
            cin >> words[i];
        }
        trie.insert(words);
    }
    
    trie.printAll();
    return 0;
}