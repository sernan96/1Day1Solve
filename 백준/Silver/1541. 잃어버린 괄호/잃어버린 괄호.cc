#include<iostream>
#include<string>
using namespace std;
int main() {
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	string str, num = "";
	cin >> str;
	int result = 0;
	bool start = false;
	for (int i = 0; i <= str.size(); i++) {
		if (str[i] ==  '+'  || str[i] == '-' || i == str.size()) {
			if (start) {//기본은 false지만 -등장후엔 활성화됨
				result -= stoi(num);
				num = "";
			}
			else {
				result += stoi(num);
				num = "";
			}
		}
		else{ //부호가 나오기 전까지 num에 문자형의 숫자를 저장
			num += str[i];
		}
		if (str[i] == '-') {//-가 나온순간 부터 -활성화
			start = true;
		}
	}
	cout << result;
	return 0;
}