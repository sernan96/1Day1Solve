#include<iostream>
#include<map>
#include<algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	int N;
	cin >> N;
	map <string, bool> isCompany;
	while (N--!=0) {
		string name, state;
		cin >> name >> state;
		if (isCompany.find(name)==isCompany.end() && state =="enter") {
			isCompany.insert(pair<string, bool >(name, true));
		}
		else if(isCompany.find(name) != isCompany.end() && state == "leave"){
			isCompany.erase(isCompany.find(name));
		}
	}
	N = isCompany.size();
	string* arr = new string[N];
	int index = 0;
	for (pair<string, bool> x: isCompany) {
		arr[index++] = x.first;
	}
	sort(arr, arr + N, greater<>());
	for (int i = 0; i < N; i++) {
		cout << arr[i]<<"\n";
	}

}