#include<iostream>
#include<vector>
#include <algorithm>
#include <string>
using namespace std;

bool cmp(vector<string> v1, vector<string> v2) {
	int k1 = stoi(v1[1]);
	int k2 = stoi(v2[1]);
	int e1 = stoi(v1[2]);
	int e2 = stoi(v2[2]);
	int m1 = stoi(v1[3]);
	int m2 = stoi(v2[3]);

	if (k1 != k2) return k1 > k2;
	if (e1 != e2) return e1 < e2;
	if (m1 != m2) return m1 > m2;
	return v1[0] < v2[0];
}


vector<vector<string>> v;
int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N;
	cin >> N;
	v.assign(N, vector<string>(4));
	for (int i = 0; i < N; i++) {
		cin >> v[i][0] >> v[i][1] >> v[i][2] >> v[i][3];
	}
	sort(v.begin(), v.end(), cmp);
	for (int i = 0; i < N; i++) {
		cout <<v[i][0]<<"\n";
	}
	return 0;
}
/*
이름 국 영 수 점수 벡터에 순서대로 저장
*/