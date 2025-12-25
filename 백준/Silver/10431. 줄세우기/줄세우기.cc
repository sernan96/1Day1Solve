#include <iostream>
#include <vector>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int T;
    cin >> T;
    while (T--!=0) {
        int t;
        cin >> t;
        int cnt = 0;
        vector<int> students(20, 0);
        for (int i = 0; i < 20;i++) {
            int student;
            cin >> student;
            bool isIt = false;
            //앞에 새 학생보다 키큰 경우가 있는지
            for (int j = 0; j < i; j++) {
                if (students[j]>student) {
                    isIt = true;
                    for (int k = i-1; k >=j; k--) {
                        students[k + 1] = students[k];
                        cnt++;
                    }
                    students[j] = student;
                    break;
                }
            }
            if (!isIt) {
                students[i] = student;
            }
        }
        cout << t << " " << cnt << "\n";
    }

    return 0;
}
/*
새로운 학생이 추가될때 새로운 학생보다 키가 큰 학생의 수를 세어 + 해주면 된다.
그럼 학생이 중간에 낄때 뒤로 미뤄주는 것은 어떻게?

*/