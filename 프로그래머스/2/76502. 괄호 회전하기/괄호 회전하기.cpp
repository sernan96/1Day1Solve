#include <string>
#include <vector>
#include <stack>

using namespace std;

int solution(string s) {
    int answer = 0;
    int N = s.length();

    for (int i = 0; i < N; i++) {
        stack<char> st;
        bool flag = true;

        for (int j = 0; j < N; j++) {
            char c = s[(i + j) % N];

            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
            }
            else {
                if (st.empty()) {
                    flag = false;
                    break;
                }

                if (c == ')' && st.top() != '(') {
                    flag = false;
                    break;
                }
                if (c == ']' && st.top() != '[') {
                    flag = false;
                    break;
                }
                if (c == '}' && st.top() != '{') {
                    flag = false;
                    break;
                }

                st.pop();
            }
        }

        if (flag && st.empty()) {
            answer++;
        }
    }

    return answer;
}