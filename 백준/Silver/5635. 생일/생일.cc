#include<iostream>
#include<map>
#include<algorithm>
using namespace std;

int main() {
	int N;
	cin >> N;
	string * arr = new string[N];
	map<string, string> personInfo;
	for (int i = 0; i < N; i++) {
		string name, day, month, year;
		cin >> name >> day >> month >> year;
		if (day.length() == 1) {
			day = "0" + day;
		}if (month.length() == 1) {
			month = "0" + month;
		}
		string info = year + month + day;
		arr[i] = info;
		personInfo.insert({ info, name });
	}
	sort(arr, arr+N);
	cout << personInfo.find(arr[N-1])->second << "\n" << personInfo.find(arr[0])->second;

}