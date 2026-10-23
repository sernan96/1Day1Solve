
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, m;

int main() {
    cin >> n >> m;

    const int INF_MAX = 0x7f7f7f7f;
    vector<vector<int>> D(n+1, vector<int>(n+1, INF_MAX));

    for(int i=1; i<=n; i++){
        D[i][i] = 0;
    }

    for(int i=0; i<m; i++){
        int start, end, weight;
        cin >> start >> end >> weight;
        D[start][end] = min(D[start][end], weight);
    }

    for(int k=1; k<=n; k++){
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){

                if(D[i][k] != INF_MAX && D[k][j] != INF_MAX){
                    D[i][j] = min(D[i][j], D[i][k] + D[k][j]);
                }

            }
        }
    }

    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            int result = D[i][j]==INF_MAX? -1: D[i][j];
            cout << result << " ";
        }
        cout << '\n';
    }

    return 0;
}
