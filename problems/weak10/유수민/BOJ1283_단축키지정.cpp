#include<bits/stdc++.h>
using namespace std;

vector<string> split(string s){
    istringstream iss(s);
    vector<string> result;
    string buffer;

    while(getline(iss, buffer, ' ')){
        result.push_back(buffer);
    }
    return result;
}

bool checkbig(char c) { 
    if (c >= 65 && c <= 90) {
        return true;
    }
    return false;
}

string getkey(string s, vector<int> &al) {
    bool selected = false;
    vector<string> word = split(s);
    string tmp = "";

    for (int i = 0; i < word.size(); i++) { 
        
        char a = word[i][0];
        if (checkbig(a)) a += 32;
        if (!selected && al[a - 'a'] == 0) {
            al[a - 'a'] = 1;
            string key = "[" + word[i].substr(0, 1) + "]"; 
            word[i].replace(0, 1, key);
            selected = true;
        }
        tmp += word[i]; 
        if (i < word.size() - 1) tmp += " ";
    }
    s = tmp;

    if (!selected) { 
        for (int i = 0; i < s.length(); i++) {
            char a = s[i];
            if (checkbig(a)) a += 32;
            if (a != ' ' && al[a - 'a'] == 0) {
                al[a - 'a'] = 1;
                string key = "[" + s.substr(i, 1) + "]";
                s.replace(i, 1, key);
                break;
            }
        }
    }
    return s;
}

int main()
{
    vector<int> al(27, 0);
    int N;
    string s;
    vector<string> orders;
    cin >> N;
    cin.ignore();
    for (int i = 0; i < N; i++) {
        getline(cin, s);
        orders.push_back(s);
    }
    for (int i = 0; i < N; i++) {
        orders[i] = getkey(orders[i], al);
        cout << orders[i] << "\n";
    }
}
