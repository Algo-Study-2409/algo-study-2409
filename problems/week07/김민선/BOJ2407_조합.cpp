#include<bits/stdc++.h>
using namespace std;
#define ll long long

/*
    - 0ms
    - 조합 출력
	- nCr = n-1Cr-1 + n-1Cr = ...
    - 수의 범위가 크므로 string으로 처리
*/

int N, M;
string dp[101][101];

string str_add(string a, string b) {
	string ret;
	int n = max(a.size(), b.size());
	int sum = 0;
	for (int i = 0; i < n; i++) {
		if (!a.empty()) {
			sum += a.back() - '0';
			a.pop_back();
		}

		if (!b.empty()) {
			sum += b.back() - '0';
			b.pop_back();
		}

		ret.push_back((sum % 10) + '0');
		sum /= 10;
	}

	if (sum)
		ret.push_back(sum + '0');

	reverse(ret.begin(), ret.end());
	return ret;
}

string combi(int n, int r) {
	if (n == r || r == 0)
		return "1";

	string &ret = dp[n][r];
	if (ret != "")
		return ret;

	ret = str_add(combi(n - 1, r - 1), combi(n - 1, r));
	return ret;
}


int main() {

    cin >> N >> M;

    cout << combi(N, M) << "\n";
}