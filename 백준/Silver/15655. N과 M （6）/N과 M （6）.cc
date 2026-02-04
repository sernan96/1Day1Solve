#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M;
vector<int> arr;

void makeNM(vector<int> NMs, int last_idx) {
    if (NMs.size() == M) {
        for (int x : NMs) {
            cout << x << " ";
        }
        cout << "\n";
        return;
    }
    for (int i = last_idx + 1; i < arr.size(); i++) {
        if (i>last_idx) {
            NMs.push_back(arr[i]);
            makeNM(NMs, i);
            NMs.pop_back();
        }
    }

}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N >> M;
    arr.assign(N, 0);
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }
    sort(arr.begin(), arr.end());
    vector<int> NMs;
    for (int i = 0; i < arr.size(); i++) {
        NMs.push_back(arr[i]);
        makeNM(NMs, i);
        NMs.pop_back();
    }
    return 0;
}
/*
* 
*/