import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o1->Math.abs(o1[0]-storey)));
        HashSet<Integer> visited = new HashSet<>();
        q.add(new int[]{0, 0});
        visited.add(0);
        
        while(!q.isEmpty()){
            int [] cur = q.poll();
            int curFloor = cur[0];
            int curCnt = cur[1];
            if(curFloor==storey){
                return curCnt;
            }
            for(int i=0; i<=8; i++){
                //+- 한번씩 가능하다면 q에 넣어주기
                int moveFloorUp = curFloor+(int)Math.pow(10, i);
                int moveFloorDown = curFloor-(int)Math.pow(10, i);
                if(!visited.contains(moveFloorUp)){
                    q.add(new int []{moveFloorUp, curCnt+1});
                    visited.add(moveFloorUp);
                }
                if(moveFloorDown >= 0 && !visited.contains(moveFloorDown)){
                    q.add(new int []{moveFloorDown, curCnt+1});
                    visited.add(moveFloorDown);
                }
            }
            
        }
        return answer;
    }
}
/*
BFS로 진행한다.
*/