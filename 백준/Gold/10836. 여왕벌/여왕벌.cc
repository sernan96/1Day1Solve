#include <iostream>
#include <vector>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N, M;
    cin >> M >> N;

    //초기 애벌레 크기 세팅
    vector<vector<int>> bees(M + 1, vector<int>(M + 1, 1));
    //성장값 누적
    vector<int> standard_bees(2 * M, 0);
    while (N--!=0) {
        //N번째 일 성장값 

        int zero, one, two;
        cin >> zero >> one >> two;
        //만약 전부 0이면 그냥 다음 입력 받으면 됨
        if (one + two == 0) {
            continue;
        }
        else{
            //zero에 입력된 값은 해당 배열의 0이 끝나는 지점-> 즉 1또는 2가 시작되는 지점
            if (one > 0) {
                standard_bees[zero]++;
                if (two > 0) {
                    standard_bees[one + zero]++;
                }
            }
            //만약 0, 1이 없고 2만 존재하는 경우
            else {
                standard_bees[zero] += 2;
            }
        }
    }

    //누적합 계산
    for (int i = 1; i < standard_bees.size(); i++) {
        standard_bees[i] += standard_bees[i - 1];
    }


    //0열 애벌레 성장시키기
    for (int i = 0; i < M; i++) {
        bees[i][0] += standard_bees[i];
    }
    // 나머지 애벌레 성장시키기
    for (int j = 0; j < M-1; j++) {
        bees[0][j + 1] += standard_bees[M + j];
        for (int i = 1; i < M; i++) {
            bees[i][j + 1] = bees[0][j + 1];
        }
    }

    for (int i = M - 1; i >= 0; i--) {
        for (int j = 0; j < M; j++) {
            cout << bees[i][j] << " ";
        }
        cout << "\n";
    }



    return 0;
}
