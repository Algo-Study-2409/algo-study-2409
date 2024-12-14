#include<bits/stdc++.h>
using namespace std;

vector<string> split(string s, string dl) {
    vector<string> ret;
    long long pos = 0;
    string token = "";
    int dl_size = dl.size();
    while ((pos = s.find(dl)) != string::npos) {
        token = s.substr(0, pos);
        ret.push_back(token);
        s.erase(0, pos + dl_size);
    }
    ret.push_back(s);
    return ret;
}

struct TrieNode {
    TrieNode* children[2];
    bool isEnd;
    
    TrieNode() {
        children[0] = children[1] = nullptr;
        isEnd = false;
    }
};

class IPTrie {
public:
    TrieNode* root;
    
    IPTrie() : root(new TrieNode()) {}

    // IP 주소를 32비트 이진수로 변환
    int convertToBinary(const string& ip) {
        int addr_bits;
        vector<string> addrs = split(ip, ".");
        
        addr_bits = stoi(addrs[0]) << 24 |
                    stoi(addrs[1]) << 16 |
                    stoi(addrs[2]) << 8  |
                    stoi(addrs[3]);

        return addr_bits;
    }

    
    void insert(const string& ip, int subnet) {
        int addr = convertToBinary(ip);
        addr &= (0xFFFFFFFF << (32 - subnet)); // 정규화 
        TrieNode* node = root;

        for (int i = 0; i < subnet; i++) {
            int bit = (addr >> (31 - i)) & 1;
            if (!node->children[bit])
                node->children[bit] = new TrieNode();
            node = node->children[bit];
        }
        node->isEnd = true;
    }

    void print(TrieNode *node, int addr, int subnet) {
        if (node->isEnd) {
            cout << ((addr >> 24) & 0xFF) << "."
                << ((addr >> 16) & 0xFF) << "."
                << ((addr >> 8) & 0xFF) << "."
                << ((addr) & 0xFF) << "/"
                << subnet << "\n";
            return;
        }
        
        if (node->children[0] != nullptr)
            print(node->children[0], addr, subnet + 1);
        if (node->children[1] != nullptr)
            print(node->children[1], addr | (1 << (31 - subnet)), subnet + 1);
    }

    void merge(TrieNode *node) {
        if (node->children[0] == nullptr && node->children[1] == nullptr) {
            return;
        }

        if (node->isEnd) {
            return;
        }

        if (node->children[0] != nullptr && node->children[1] != nullptr &&
            node->children[0]->isEnd && node->children[1]->isEnd) 
        {
            node->isEnd = true;
            return;
        }

        if (node->children[0] != nullptr)
            merge(node->children[0]);
        if (node->children[1] != nullptr)
            merge(node->children[1]);
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int T;
    cin >> T;
    
    for (int t = 1; t <= T; t++) {
        int N;
        cin >> N;
        
        IPTrie *trie = new IPTrie();

        for (int i = 0; i < N; i++) {
            string ip_subnet;
            cin >> ip_subnet;
            
            auto pos = ip_subnet.find('/');
            string ip = ip_subnet.substr(0, pos);
            int subnet = stoi(ip_subnet.substr(pos + 1));
            
            trie->insert(ip, subnet);
        }
        
        cout << "Case #" << t << ":\n";
        trie->merge(trie->root);
        trie->print(trie->root, 0, 0);
    }

    return 0;
}