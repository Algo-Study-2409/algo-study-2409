#include<bits/stdc++.h>
using namespace std;

/*
    - dp[x][y][z] x, y일 때 최대 수확량 / z: 스프링 쿨러 사용 여부
    - 스프링 쿨러 사용 여부에 따라 dp[x][y] 값이 정해짐
    - 스프링 쿨러 사용 x
        - dp[x][y][0] = max(dp[x][y][0], dp[nx][ny][0] + arr[nx][ny])    
            - 사용 안한 위, 왼쪽 값 중 큰 값 + 현재 값
    - 스프링 쿨러 사용 o
        - dp[x][y][1] = max(dp[x][y][1], dp[nx][ny][1] + arr[nx][ny])
            - 사용한 위, 왼쪽 값 중 큰 값 + 현재 값
        - dp[x][y][1] = max(dp[x][y][1], dp[nx][ny][0] + arr[nx][ny] * 2)
            - 사용 안한 위, 왼쪽 값 중 큰 값 + 현재 값 * 2
*/

int N;
int arr[1001][1001] = {0, };
int dp[1001][1001][2] = {0, }; // x, y일 때 최대 수확량 / 0: 스프링 쿨러 사용 x, 1: 스프링 쿨러 사용 o
int dx[] = {1, 0};
int dy[] = {0, 1};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> arr[i][j];
        }
    }

    dp[1][1][1] = arr[1][1] * 2;
    dp[1][1][0] = arr[1][1];

    for (int y = 1; y <= N; y++) {
        for (int x = 1; x <=N; x++) {
            for (int i = 0; i < 2; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx > N || ny > N) {
                    continue;
                }
                
                dp[nx][ny][0] = max(dp[nx][ny][0], dp[x][y][0] + arr[nx][ny]);
                dp[nx][ny][1] = max(dp[nx][ny][1], dp[x][y][1] + arr[nx][ny]);
                dp[nx][ny][1] = max(dp[nx][ny][1], dp[x][y][0] + arr[nx][ny] * 2);
            }
        }
    }   


    cout << dp[N][N][1] << "\n";

    return 0;
}