#include <iostream>
#include<stack>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N;
    cin >> N;
    stack<pair<int, int>> st;
    long long pair_cnt = 0;
    while (N-- != 0) {
        int person;
        cin >> person;
        int same_height = 1;
        //자기보다 작은 값들은 전부 pop해주면서 카운트
        while (!st.empty() && st.top().first < person) {
            pair_cnt += st.top().second;
            st.pop();
        }
        if (!st.empty() ) {
            if (st.top().first == person) {
                pair_cnt += st.top().second;
                same_height = st.top().second + 1;
                //만약에 앞에 더 큰 사람이 있다면 쌍 추가
                if (st.size() > 1) {
                    pair_cnt++;
                }
                st.pop();
            }
            else {
                pair_cnt++;
            }
        }
        st.push({person, same_height});
    }
    cout << pair_cnt;
    return 0;
}