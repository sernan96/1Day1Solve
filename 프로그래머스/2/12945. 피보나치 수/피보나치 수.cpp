#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    int mod = 1234567;
    vector<int> v(n+1, 0);
    v[0] = 0;
    v[1] = 1;
    for(int i = 2; i<=n; i++){
        v[i] = ((v[i-2] % mod) + (v[i-1] % mod))%mod;
    }
    return v[n];
}