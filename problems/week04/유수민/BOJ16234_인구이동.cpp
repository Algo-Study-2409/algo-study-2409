#include<bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define X first
#define Y second

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

int N, L, R;
int arr[51][51];
bool open[51][51][4];
bool vis[51][51];

int main(){

    cin >> N >> L >> R;

    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cin >> arr[i][j];
        }
    }

    int ans = 0;

    while(1){
        int cnt = 0;
        
        // 국경선 열기 
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                for(int t=0; t<4; t++){ // 오른쪽, 아래쪽, 왼쪽, 위쪽

                    int nx = i + dx[t];
                    int ny = j + dy[t];

                    if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
                    
                    int temp = abs(arr[i][j] - arr[nx][ny]);
                    if(temp >= L && temp <= R){
                        open[i][j][t] = true;
                        cnt++;
                    }
                }
            }
        }
        
        // 더 이상 열 국경선이 없음
        if(cnt == 0) break;

        bool bb = false; // 중복 개수 카운트 방지용
        // 인구이동
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                
                if(vis[i][j] == true) continue;

                queue<pii> q;
                q.push({i, j});
                vis[i][j] = true;

                int total = 0;
                vector<pii> v;
                
                while(!q.empty()){
                    auto cur = q.front();
                    q.pop();

                    total += (arr[cur.X][cur.Y]);
                    v.push_back({cur.X, cur.Y});

                    for(int t=0; t<4; t++){
                        int nx = cur.X + dx[t];
                        int ny = cur.Y + dy[t];
                        if(nx < 1 || nx > N || ny < 1 || ny > N) continue;

                        if(open[cur.X][cur.Y][t] == true && !vis[nx][ny]){
                            vis[nx][ny] = true;
                            q.push({nx, ny});
                        }
                    }
                }

                if(v.size() > 1){
                    if(bb == false){
                        ans++;
                        bb = true;
                    }
                    int newP = total / v.size();
                    for(auto e : v) arr[e.X][e.Y] = newP;                    
                }
            }
        }
        
        // 국경선 모두 닫기
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                for(int t=0; t<4; t++){
                    open[i][j][t] = false;
                }
            }
        }
        // 방문 여부 초기화
        for(int i=0; i<51; i++) fill(vis[i], vis[i]+51, false);
        
    }
    

    cout << ans;
    return 0;
}