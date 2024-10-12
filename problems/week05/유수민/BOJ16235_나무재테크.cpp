#include <bits/stdc++.h>
using namespace std;

int n, m, k;

int vita[11][11]; // 현재 양분
int a[11][11]; // 겨울에 추가되는 양분
vector<int> age[11][11]; // 심어져 있는 나무 나이

int dx[9] = {0, 1, 0, -1, -1, -1, 1, 1};
int dy[9] = {1, 0, -1, 0, -1, 1, -1, 1};

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    for(int i=0; i<11; i++) fill(vita[i], vita[i]+11, 5);

    cin >> n >> m >> k;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cin >> a[i][j];
        }
    } // 겨울에 추가할 양분

    for(int i=1; i<=m; i++){
        int x, y, z;
        cin >> x >> y >> z;
        age[x][y].push_back(z);
    } // 초기 나무 

    for(int year=0; year<k; year++){
        // 봄
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){

                if(age[i][j].size() == 0) continue;

                sort(age[i][j].begin(), age[i][j].end());

                int cnt = age[i][j].size();
                
                vector<int> tmp;
                int leave = 0;

                for(int t=0; t<cnt; t++){
                    
                    if(vita[i][j] >= age[i][j][t]){ // 양분 먹기
                        vita[i][j] -= age[i][j][t];
                        tmp.push_back(age[i][j][t] + 1);
                    } 
                    else{ // 양분 못먹기
                        leave += (age[i][j][t] / 2);
                    } 
                }

                age[i][j].clear();
                for(int r=0; r<tmp.size(); r++){
                    age[i][j].push_back(tmp[r]);
                }
                vita[i][j] += leave;
            }
        }

        // 가을
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(age[i][j].size() == 0) continue;
                
                int cnt = age[i][j].size();

                for(int t=0; t<cnt; t++){
                    if(age[i][j][t] % 5 == 0){
                        for(int idx=0; idx<8; idx++){
                            int nx = i + dx[idx];
                            int ny = j + dy[idx];
                            if(nx < 1 || nx > n || ny < 1 || ny > n) continue;
                            age[nx][ny].push_back(1);
                        }
                    }
                }
            }
        }

        // 겨울
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                vita[i][j] += a[i][j];
            }
        } // 겨울에 추가할 양분

    }

    int answer = 0;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            answer += age[i][j].size();
        }
    }
    cout << answer;
}