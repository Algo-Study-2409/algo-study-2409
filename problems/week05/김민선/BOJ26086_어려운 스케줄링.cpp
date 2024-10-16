#include<bits/stdc++.h>
using namespace std;

/*
    - 16ms
    - deque 사용해 스케줄 관리
    - 정렬 자체를 여러반 할 필요 없이 마지막 정렬만 수행하면 결국 같음
    - 따라서 마지막 정렬 이후로 나눠서 처리
*/

int N, Q, K;
deque<int> schedule;
vector<int> commands;
vector<int> numbers;
int numbers_idx = 0;
bool is_schedule_front = true; // deque의 front가 스케줄의 시작점인지 여부

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> Q >> K;

    int last_sort_idx = -1;

    for (int i = 0; i < Q; i++) {
        int command;
        cin >> command;
        commands.push_back(command);
        if (command == 1) {
            last_sort_idx = i;
        } else if (command == 0) {
            int x;
            cin >> x;
            numbers.push_back(x);
        }
    }

    for (int i = 0; i < last_sort_idx; i++) {
        if (commands[i] == 0) {
            schedule.push_back(numbers[numbers_idx++]);
        } else if (commands[i] == 2) {
            is_schedule_front = !is_schedule_front;
        }
    }

    if (last_sort_idx != -1) {
        if (is_schedule_front) {
            sort(schedule.begin(), schedule.end());
        } else {
            sort(schedule.begin(), schedule.end(), greater<int>());
        }
    }

    for (int i = last_sort_idx + 1; i < Q; i++) {
        if (commands[i] == 0) {
            if (is_schedule_front) {
                schedule.push_front(numbers[numbers_idx++]);
            } else {
                schedule.push_back(numbers[numbers_idx++]);
            }
        } else if (commands[i]  == 2) {
            is_schedule_front = !is_schedule_front;
        }
    }

    // K번째 스케줄 출력
    if (is_schedule_front) {
        cout << schedule[K-1] << "\n";
    } else {
        cout << schedule[schedule.size() - K] << "\n";
    }
}
