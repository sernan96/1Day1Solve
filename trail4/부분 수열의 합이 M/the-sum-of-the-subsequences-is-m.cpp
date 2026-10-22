#include <iostream>
#include <vector>
using namespace std;

int n, m;
int A[100];

int main() {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> A[i];
    }

    // Please write your code here.

    vector<int> dp(m+1, 0x7f7f7f7f);
    dp[0] = 0;
    for(int x: A){
        for(int j=m; j>=0; j--){
            if(j-x<0){
                continue;
            }
            dp[j] = min(dp[j], dp[j-x] + 1);
        }
    }
    if(dp[m]==0x7f7f7f7f){
        cout<<-1;
        return 0;
    }
    cout<<dp[m];
    return 0;
}
