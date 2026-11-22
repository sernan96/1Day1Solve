#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M;
vector<int> comb;
vector<int> nums;

void DFS(int len) {
	if (len == M) {
		for (int x: comb) {
			cout << x << " ";
		}
		cout << "\n";
		return;
	}

	for (int i = 0; i < N; i++) {
		comb.push_back(nums[i]);
		DFS(len + 1);
		comb.pop_back();
	}
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int num;
		cin >> num;
		nums.push_back(num);
	}
	sort(nums.begin(), nums.end());

	for (int i = 0; i < N; i++) {
		comb.push_back(nums[i]);
		DFS(1);
		comb.pop_back();
	}

	return 0;
}