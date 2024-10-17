#include <bits/stdc++.h>
using namespace std;

/*
    - 136ms
    - 구현
    - 봄: 나이가 어린 나무부터 양분을 먹음
    - 여름: 죽은 나무가 양분이 됨
    - 가을: 나이가 5의 배수인 나무가 번식
    - 겨울: 양분 추가
    - 봄, 여름 한번에 처리 
*/

int N, M, K;

vector<vector<int>> A; // 겨울에 추가되는 양분
vector<vector<int>> power; // 각 셀에 있는 양분의 양
vector<int> trees_list[11][11]; // 각 셀에 있는 나무들의 나이 리스트

int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};

void spring_and_summer() {
    for(int y = 1; y <= N; y++) {
        for(int x = 1; x <= N; x++) {
            if(trees_list[y][x].empty())
				continue;

            sort(trees_list[y][x].begin(), trees_list[y][x].end());

            vector<int> alive;
            int dead_power = 0;

            for(auto &age : trees_list[y][x]) {
                if(power[y][x] >= age) {
                    power[y][x] -= age;
                    alive.push_back(age + 1);
                }
                else {
                    dead_power += age / 2;
                }
            }

            trees_list[y][x] = alive;
            power[y][x] += dead_power;
        }
    }
}

void fall() {
    for(int y = 1; y <= N; y++) {
        for(int x = 1; x <= N; x++) {
            for (int &age : trees_list[y][x]) {
                if(age % 5 == 0) {
                    for(int dir = 0; dir < 8; dir++) {
                        int ny = y + dy[dir];
                        int nx = x + dx[dir];

                        if(ny < 1 || ny > N || nx < 1 || nx > N) {
                            continue;
                        }
                        
                        trees_list[ny][nx].push_back(1);
                    }
                }
            }
        }
    }
}

void winter() {
    for(int y = 1; y <= N; y++) {
        for(int x = 1; x <= N; x++) {
            power[y][x] += A[y][x];
        }
    }
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M >> K;

    A.assign(N + 1, vector<int>(N + 1, 0));
    for(int y = 1; y <= N; y++) {
        for(int x = 1; x <= N; x++) {
            cin >> A[y][x];
        }
    }

    power.assign(N + 1, vector<int>(N + 1, 5));

    for(int i = 0; i < M; i++) {
        int r, c, age;
        cin >> r >> c >> age;
        trees_list[r][c].push_back(age);
    }

    for(int i = 0; i < K; i++) {
        spring_and_summer();
        fall();
        winter();
    }

    int ret = 0;
    for(int y = 1; y <= N; y++) {
        for(int x = 1; x <= N; x++) {
            ret += trees_list[y][x].size();
        }
    }

    cout << ret;
}
