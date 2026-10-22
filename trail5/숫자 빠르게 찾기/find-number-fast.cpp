#include <iostream>

using namespace std;

int n, m;
int arr[100000];

int main() {
    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    for (int i = 0; i < m; i++) {
        int x;
        cin >> x;
        int left = 0; 
        int right = n-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(arr[mid]==x){    
                cout<<mid+1<<endl;
                break;
            }
            else if(arr[mid]<x){
                left = mid+1;
            }
            else{
                right = mid -1;
            }
        }
        if(left>right){
            cout<<-1<<endl;
        }
    }

    // Please write your code here.

    return 0;
}
