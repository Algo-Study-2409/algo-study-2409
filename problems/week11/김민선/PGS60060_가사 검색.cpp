#include <bits/stdc++.h>
using namespace std;

class Trie {
private:
    Trie* child[26];  // a ~ z
    int count;
    
public:
    Trie() {
        count = 0;
        memset(child, 0, sizeof(child));
    }
    
    void insert(const string& word) {
        Trie* node = this;
        for (char c : word) {
            node->count++;
            int idx = c - 'a';
            if (!node->child[idx]) {
                node->child[idx] = new Trie();
            }
            node = node->child[idx];
        }
    }
    
    int search(const string& word) {
        Trie* node = this;
        for (char c : word) {
            if (c == '?') 
                return node->count;
            int idx = c - 'a';
            if (!node->child[idx])
                return 0;
            node = node->child[idx];
        }
        return -1;
    }
};

vector<int> solution(vector<string> words, vector<string> queries) {
    Trie tries[100001];
    Trie reverse_tries[100001];
    vector<int> answer;
    
    for (string word : words) {
        tries[word.length()].insert(word);
        string reversed_word = word;
        reverse(reversed_word.begin(), reversed_word.end());
        reverse_tries[word.length()].insert(reversed_word);
    }

    for (string query : queries) {
        int len = query.size();

        if (query[0] != '?') {
            // 접두사 패턴
            answer.push_back(tries[len].search(query));
        } else {
            // 접미사 패턴
            reverse(query.begin(), query.end());
            answer.push_back(reverse_tries[len].search(query));
        }
    }

    return answer;
}
