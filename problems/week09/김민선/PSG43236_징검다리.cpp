#include <bits/stdc++.h>

using namespace std;

int solution(int distance, vector<int> rocks, int n) {
    int answer = 0;

    rocks.push_back(0);
    rocks.push_back(distance);
    
    sort(rocks.begin(), rocks.end());
    
    // 특정 거리 기준 이하 제거
    int left = 0;
    int right = distance;
    
    while (left <= right) {
        int target = (left + right) / 2;
        int prev = 0;
        int cnt = 0;
        
        for (int i = 1; i < rocks.size(); i++) {
            if (rocks[i] - prev < target) {
                cnt++;
            } else {
                prev = rocks[i];
            }
        }
        
        if (cnt > n) {
            right = target - 1;
        } else {
            left = target + 1;
            answer = target;
        }
    }
    
    return answer;
}