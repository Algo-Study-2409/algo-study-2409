#include<bits/stdc++.h>
using namespace std;

int n, m;
int ret = 0;
deque<int> dq;

/*
	- 0 ms
	- greedy
	- deque에서 목표 idx까지 이동하는 최소 횟수 구하기
*/
int main() {
	cin >> n >> m;
	for (int i = 1; i <= n; i++)
		dq.push_back(i);

	int num;
	int idx = 0;
	for (int i = 0; i < m; i++) {
		cin >> num;
		
		for (int j = 0; j < dq.size(); j++) {
			if (dq[j] == num) {
				idx = j;
				break;
			}
		}

		if (idx <= dq.size() / 2) {
			// 왼쪽으로 이동
			while (dq.front() != num) {
				dq.push_back(dq.front());
				dq.pop_front();
				ret++;
			}
			dq.pop_front();
		}
		else {
			// 오른쪽으로 이동
			while (dq.front() != num) {
				dq.push_front(dq.back());
				dq.pop_back();
				ret++;
			}
			dq.pop_front();
		}
	}

	cout << ret;
}
