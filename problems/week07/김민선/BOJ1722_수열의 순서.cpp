#include<bits/stdc++.h>
using namespace std;

#define ll long long

int N, command;
vector<int> arr;

ll factorial(int n) {

    if (n == 0) {
        return 1;
    }

	ll ret = 1;
	for (int i = 1; i <= n; i++) {
		ret *= i;
	}
	return ret;
}


int main() {
    cin >> N;
    cin >> command;

	ll num;
	vector <int> v;
    if (command == 1) {
		for (int i = 1; i <= N; i++) {
			arr.push_back(i);
		}
        cin >> num;
		num--;
		for (int i = 0; i < N; i++) {
			int fact = factorial(N - i - 1);
			int idx = num / fact;
			num %= fact;
			v.push_back(arr[idx]);
			arr.erase(arr.begin() + idx);
		}

		for (int i = 0; i < N; i++) {
			cout << v[i] << " ";
		}
		cout << "\n";
    } else if (command == 2) {
        for (int i = 0 ; i < N; i++) {
            int num;
            cin >> num;
            v.push_back(num);
        }

		ll ret = 1;
        vector<bool> visited(N + 1, false);
        for (int i = 0; i < N; i++) {
            int idx = 0;
            for (int j = 1; j < v[i]; j++) {
                if (!visited[j]) idx++;
            }
            ret += idx * factorial(N - i - 1);
            visited[v[i]] = true;
        }

		cout << ret << "\n";
    }
}