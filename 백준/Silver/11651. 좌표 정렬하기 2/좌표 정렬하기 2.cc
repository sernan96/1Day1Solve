#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

bool comx(vector<int>a, vector<int>b) {
	return a[0] < b[0];
}
bool comy(vector<int>a, vector<int>b) {
	return a[1] < b[1];
}
int main() {
	int n;
	cin >> n;
	vector<vector<int>>xy(n, vector<int>(2, 0));
	for (int i = 0; i < n; i++) {
		cin >> xy[i][0] >> xy[i][1];
	}	
	sort(xy.begin(), xy.end(), comx);
	stable_sort(xy.begin(), xy.end(), comy);
	for (int k = 0; k < n; k++) {
		cout << xy[k][0] <<" "<< xy[k][1]<<'\n';
	}
	return 0;
}