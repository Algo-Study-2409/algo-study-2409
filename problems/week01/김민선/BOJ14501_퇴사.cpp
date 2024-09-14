#include<bits/stdc++.h>
using namespace std;

/*
	- 0 ms
	- dp
	- dp[i]: i일 째 얻을 수 있는 최대 수익
	- i) 상담 안함
	- ii) 상담 진행 
*/

int n;
vector<int> dp(16, 0);

int main() {
	cin >> n;
	
	int t, p;
	
	for (int i = 0; i < n; i++) {
		cin >> t >> p;
		
		// 상담 안함 
		dp[i + 1] = max(dp[i], dp[i + 1]);
		
		// 상담 진행 
		if(t + i <= n)
			dp[t + i] = max(p + dp[i], dp[t + i]);
	}
	
	// 마지막 날 하루 근무 가능 
	cout << dp[n];
}
