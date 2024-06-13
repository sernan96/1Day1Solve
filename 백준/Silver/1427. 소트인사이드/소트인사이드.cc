#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	string str;
	cin>>str;
	vector<int> v;
	for (int i = 0; i < str.length(); i++) {
		v.push_back(str[i]-'0');
	}
	sort(v.begin(), v.end(), greater<>());
	for (int i = 0; i < str.length(); i++) {
		cout <<v[i];
	}
}