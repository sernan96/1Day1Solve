import java.util.*;

class Solution {
    static class Node{
        int s, e;
        public Node(int s, int e){
            this.s = s;
            this.e = e;
        }
    }
    public int solution(int[][] targets) {
        int answer = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o1->o1.e));
        for(int [] x: targets){
            pq.add(new Node(x[0], x[1]));
        }
        int end = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(end<=cur.s){
                answer++;
                end = cur.e;
            }
        }
        return answer;
    }
}
/*
회의실 배정이랑 거의 똑같음 -> 그리디로
s기준으로(같으면 e가 작은 순서로) 정렬하고  하나를 뽑아가면서 
겹치지 않는 타겟이 나오면 +1 
*/