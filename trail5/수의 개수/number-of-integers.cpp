#include <iostream>
#include <algorithm>
using namespace std;

int n, m;
int arr[100000];


int lower_bound(int x){
    int left = 0;
    int right = n-1;
    int mid_answer = n;
    while(left<=right){
        int mid = (left+right)/2;

        if(arr[mid]>=x){
            right = mid-1;
            mid_answer = min(mid, mid_answer);
        }
        else{
            left = mid+1;
        }
    }
    return mid_answer;
}
int upper_bound(int x){
    int left = 0;
    int right = n-1;
    int mid_answer = n;
    while(left<=right){
        int mid = (left+right)/2;

        if(arr[mid]>x){
            right = mid-1;
            mid_answer = min(mid, mid_answer);
        }
        else{
            left = mid+1;
        }
    }
    return mid_answer;
}
int main() {
    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    for (int i = 0; i < m; i++) {
        int x;
        cin >> x;
        cout<<(upper_bound(x)-lower_bound(x))<<endl;
    }

    // Please write your code here.

    return 0;
}
