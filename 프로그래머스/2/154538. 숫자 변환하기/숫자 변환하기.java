import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        int []arr = new int[1000001];
        Arrays.fill(arr, -1);
        arr[x] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<=y; i++){
            if(i-n>0 && arr[i-n]!=-1){
                pq.add(arr[i-n]+1);
            }
            if(i%2==0 && arr[i/2]!=-1){
                pq.add(arr[i/2]+1);
            }
            if(i%3==0 && arr[i/3]!=-1){
                pq.add(arr[i/3]+1);
            }
            if(!pq.isEmpty()){
                arr[i] = pq.poll();
            }
            pq.clear();
        }
        return arr[y];
    }
}
/*
전형적인 DP문제인듯
100만크기의 배열을 만들고 -1로 채운다.
그리고 x로부터 시작해야하니까 arr[x]에 시작값인 0을 넣어준다.
그리고 i=1부터 시작해서 만약
i-n, i가 짝수일때는 i/2, 3의 약수이면 i/3위치의 인덱스로 하고 해당 위치에 저장된 값들 중에 최소값의 +1을 i에 넣어준다
그렇게 i는 y까지 도달하게 되고 마지막에 arr[y]를 return

*/