import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int e: enemy){
            pq.add(e);
            n-=e;
            if(n<0){
                if(k>0){
                    n+=pq.poll();
                    k--;
                }
                else break;
            }
            answer++;
        }
        return answer;
    }
}
/*
최대한 많은 라운드를 진행하는 것이기에
무적권을 쓰는 경우는 enemy가 큰 경우순으로 사용하면 되고
무적권을 다 소진한 경우에는 작은 순으로 사용하면 된다.
그럼 우선 무적권을 전부 소진하고 남은 적들을 처치해주거나
무적권을 사용하기 전에 먼저 가능한 적들을 처치하고 k를 더한 값과 enemiy의 길이 중 작은걸로 return해주는 방법이 있다.

그럼 난 2번째 방법을 사용하겠다 


*/