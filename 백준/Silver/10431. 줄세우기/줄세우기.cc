#include <iostream>
#include <vector>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int T;
    cin >> T;
    while (T--) {
        int t;
        cin >> t;
        int cnt = 0;
        vector<int> students(20, 0);

        for (int i = 0; i < 20; i++) {
            int student;
            cin >> student;
            
            // 일단 현재 위치에 넣는다고 가정 (임시 저장 필요 없음, 밀어내고 넣으면 됨)
            // students[i] = student; // 이 줄은 아래 로직에서 처리하므로 생략 가능하나 있어도 무방

            // 앞에 자신보다 키 큰 학생이 있는지 탐색
            bool inserted = false;
            for (int j = 0; j < i; j++) {
                if (students[j] > student) {
                    // *** 수정된 부분: 뒤에서부터 앞으로 밀어야 함 ***
                    for (int k = i - 1; k >= j; k--) {
                        students[k + 1] = students[k];
                        cnt++; // 한 칸 밀릴 때마다 걸음 수 증가
                    }
                    students[j] = student; // 빈 자리에 새 학생 넣기
                    inserted = true;
                    break;
                }
            }
            
            // 만약 앞에 더 큰 학생이 없어서 중간에 끼지 않았다면, 맨 뒤(i)에 넣음
            if (!inserted) {
                students[i] = student;
            }
        }
        cout << t << " " << cnt << "\n";
    }

    return 0;
}