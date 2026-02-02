#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    string word;
    cin >> word;
    //simple 문자열에 중복된 알파벳의 압축 버전을 넣는다.
    string simple = "";
    int pre_len = 1;
    for (int i = 0; i < word.length(); ) {
        int same_len = 1;
        for (int j = i + 1; j < word.length(); j++) {
            if (word[i] != word[j]) {
                break;
            }
            else {
                same_len++;
            }
        }
        if (word[i]!='w' && pre_len != same_len) {
            cout << 0;
            return 0;
        }
        simple = simple + word[i];
        i += same_len;
        pre_len = same_len;
    }
    bool is_right = true;
    if (simple.length()%4!=0) {
        cout << 0;
        return 0;
    }
    else {
        for (int i = 0; i < simple.length(); i+=4) {
            if (simple.substr(i, 4) != "wolf") {
                cout << 0;
                return 0;
            }
        }
    }
    cout << 1;
    return 0;
}
/*
연달아 있는 동일한 알파벳을 하나로 압축시켜주면 
단순 wolf의 반복으로 변환할 수 있다.

*/