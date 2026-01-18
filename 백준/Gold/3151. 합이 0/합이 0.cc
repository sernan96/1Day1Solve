#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N;
    cin >> N;
    vector<int> students(N, 0);
    for (int i = 0; i < N; i++) {
        cin >> students[i];
    }
    sort(students.begin(), students.end());
    long long cnt = 0;
    for (int i = 0; i < N-2; i++) {
        int left = i + 1;
        int right = N - 1;
        while (left < right) {
            int sum = students[i] + students[left] + students[right];
            if (sum == 0) {
                //중복되는 부분 세어주는 로직
                if (students[left] == students[right]) {
                    int n = right - left + 1;
                    cnt += n * (n - 1) / 2;
                    break;
                }
                int lcnt = 1;
                int rcnt = 1;
                while (left + 1 < right && students[left] == students[left + 1]) {
                    left++;
                    lcnt++;
                }
                while (right - 1 > left && students[right] == students[right - 1]) {
                    right--;
                    rcnt++;
                }
                cnt += lcnt * rcnt;
                left++;
                right--;
            }
            else if (sum > 0) {
                right--;
            }
            else if (sum < 0) {
                left++;
            }
        }
    }
    cout << cnt;
    return 0;
}

/*
3번째 팀원을 이분탐색으로 찾아내기 또는
2, 3번째 팀원을 투포인터로 찾아내면 된다. 
*/