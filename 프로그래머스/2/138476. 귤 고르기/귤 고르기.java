import java.util.*;

class Solution {
    static class pair {
        int kind, amount;
        public pair(int kind, int amount){
            this.kind = kind;
            this.amount = amount;
        }
    }
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        //개수 세어서 저장 
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for(int t: tangerine){
            if(cnt.containsKey(t)){
                cnt.put(t, cnt.get(t)+1);
            }
            else{
                cnt.put(t, 1);
            }
        }
        PriorityQueue<pair> pq = new PriorityQueue<pair>(Comparator.comparingInt(o1->o1.amount*(-1)));
        for(int kind: cnt.keySet()){
            pq.add(new pair(kind, cnt.get(kind)));
        }
        while(k>0){
            pair cur = pq.poll();
            k-=cur.amount;
            answer++;
        }
        return answer;
    }
}
/*
우선 각 크기별 개수를 저장해준다.
최소종류의 귤을 상자에 담기위해선
가장 많은 수를 가진 종류 순으로 포장해주면 된다.
그렇게 하기 위해서 우선순위큐에 귤의 종류와 개수를 class로 정의해
넣어주고 원하는 개수만큼 구하기 위해서 구할 수 있을때까지 pq를 뽑아주면 된다.
뽑을때마다 answer++
마지막에 return해주면 끝
*/