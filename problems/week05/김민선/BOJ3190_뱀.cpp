    #include <bits/stdc++.h>
    using namespace std;

    /*
        - 0ms
        - 구현
        - deque를 이용하여 뱀의 몸을 저장
        - 뱀의 머리와 꼬리를 deque의 front와 back으로 관리
    */

    // R, D, L, U
    int dy[4] = {0, 1, 0, -1};
    int dx[4] = {1, 0, -1, 0};

    int main(){
        ios::sync_with_stdio(false);
        cin.tie(NULL);
        cout.tie(NULL);

        int N, K;
        cin >> N >> K;

        vector<vector<int>> arr(N+1, vector<int>(N+1, 0));

        for (int i=0; i<K; i++) {
            int y, x;
            cin >> y >> x;
            arr[y][x] = 1; // 사과
        }

        int L;
        cin >> L;

        vector<int> dirs(10001, 0);
        for (int i=0; i<L; i++) {
            int X;
            char C;
            cin >> X >> C;
            if(C == 'D') dirs[X] = 1; // 오른쪽 회전
            else dirs[X] = -1; // 왼쪽 회전
        }

        deque<pair<int, int>> snake;
        snake.push_front({1, 1});
        arr[1][1] = 2;

        int dir = 0; // 초기 방향: 오른쪽
        int time = 0;

        while(true){
            time++;
            int head_y = snake.front().first;
            int head_x = snake.front().second;

            int ny = head_y + dy[dir];
            int nx = head_x + dx[dir];

            if(ny < 1 || ny > N || nx < 1 || nx > N || arr[ny][nx] == 2){
                cout << time;
                return 0;
            }

            if (arr[ny][nx] == 1) {
                // 사과가 있으면
                arr[ny][nx] = 2;
                snake.push_front({ny, nx});
            }
            else {
                // 사과가 없으면
                arr[ny][nx] = 2;
                snake.push_front({ny, nx});
                pair<int, int> tail = snake.back();
                arr[tail.first][tail.second] = 0;
                snake.pop_back();
            }

            if (time <= 10000 && dirs[time] != 0) {
                if (dirs[time] == 1) {
                    // 오른쪽으로 회전
                    dir = (dir + 1) % 4;
                }
                else if (dirs[time] == -1) {
                    // 왼쪽으로 회전
                    dir = (dir + 3) % 4;
                }
            }
        }

        return 0;
    }
