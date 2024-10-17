#include <bits/stdc++.h>
using namespace std;
#define X first
#define Y second
#define pii pair<int,int>

char cmd[10001];

int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 }; // 오 아래 왼 위 

int board[101][101]; // 사과 0, 뱀 1~, 빈칸 -1

int n, k, l;

int dir = 0;
pii head = { 1, 1 };
pii tail = { 1, 1 };

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	fill(cmd, cmd + 10001, 'X');
	for(int i=0; i<101; i++) fill(board[i], board[i] + 101, -1);
	board[1][1] = 1;

	cin >> n >> k;

	for (int i = 0; i < k; i++) {
		int r, c;
		cin >> r >> c;
		board[r][c] = 0; // 사과
	}
	cin >> l;

	for (int i = 0; i < l; i++) {
		int x;
		char d;
		cin >> x >> d;
		cmd[x] = d;
	}

	int time = 0;

	while (1) {
		time++;

		int nx = head.X + dx[dir];
		int ny = head.Y + dy[dir];

		if (nx < 1 || nx > n || ny < 1 || ny > n) break;
		else if (board[nx][ny] > 1) break;
		else if (board[nx][ny] == 0) {
			board[nx][ny] = board[head.X][head.Y]+1;
			head = { nx, ny };
		}
		else {
			board[nx][ny] = board[head.X][head.Y] + 1;
			head = { nx, ny };

			
			for (int i = 0; i < 4; i++) {
				int x = tail.X + dx[i];
				int y = tail.Y + dy[i];
				if (x < 1 || x > n || y < 1 || y > n) continue;
				if (board[x][y] == board[tail.X][tail.Y] + 1) {
					board[tail.X][tail.Y] = -1;
					tail = { x, y };
					break;
				}
			}
			
		}


		if (cmd[time] == 'D') {
			dir = (dir + 1) % 4;
		}
		else if(cmd[time] == 'L') {
			dir = (dir + 3) % 4;
		}
	}

	cout << time;



}
