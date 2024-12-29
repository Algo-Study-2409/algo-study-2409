#include<bits/stdc++.h>
using namespace std;

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

int n;
int like[401][5];
vector<int> rd;
int ans;
int board[21][21];



int main(){
    cin >> n;
    int num;
    for(int i=1; i<=n*n; i++){
        cin >> num;
        rd.push_back(num);
        for(int k=0; k<4; k++){
            cin >> like[num][k];
        }
    }

    // 학생 배치하기
    for(const int& student : rd){

        int rx = 1, ry = 1;
        int mx = -1; // 좋아하는 학생수
        int mx2 = -1; // 빈칸 수

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                
                if(board[i][j] != 0) continue;

                int likes = 0;
                int blanks = 0;

                for(int t=0; t<4; t++){ // 상하좌우 확인
                    
                    int nx = i + dx[t];
                    int ny = j + dy[t];
                    if(nx < 1 || nx > n || ny < 1 || ny > n) continue;

                    if (board[nx][ny] == 0) {
                        blanks++;
                    } 
                    else {
                        for (int k = 0; k < 4; k++) {
                            if (board[nx][ny] == like[student][k]) likes++;
                        }
                    }
                }
                if (likes > mx || 
                   (likes == mx && blanks > mx2) || 
                   (likes == mx && blanks == mx2 && (rx == -1 || i < rx || (i == rx && j < ry)))) {
                    mx = likes;
                    mx2 = blanks;
                    rx = i;
                    ry = j;
                }
            }
        }
        board[rx][ry] = student;
    }

    // for(int i=1; i<=n; i++){
    //     for(int j=1; j<=n; j++){
    //         cout << board[i][j] << " ";
    //     }
    //     cout << "\n";
    // }


    // 만족도 구하기
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            int student = board[i][j];
            int cnt = 0;
            for(int t=0; t<4; t++){
                int nx = i + dx[t];
                int ny = j + dy[t];
                if(nx < 1 || nx > n || ny < 1 || ny > n) continue;

                for(int k=0; k<4; k++){
                    if(board[nx][ny] == like[student][k]) cnt++;
                }
            }
            
            if(cnt == 0 || cnt == 1){
                ans += cnt;
            }
            else{
                int ten = 10;
                for(int k=0; k<cnt-2; k++){
                    ten *= 10;
                }
                ans += ten;
            }
        }
    }

    cout << ans; 
    
}