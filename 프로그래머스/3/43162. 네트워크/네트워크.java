import java.util.*;

class Solution {
    boolean [] visited;
    ArrayList<HashSet<Integer>> graph;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean [n];
        graph = new ArrayList<>();
        for(int i=0; i<n; i++){
            graph.add(new HashSet<Integer>());
        }
        //그래프 연결
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i==j || computers[i][j]==0){
                    continue;
                }
                graph.get(i).add(j);
                graph.get(j).add(i);
            }
        }
        for(int i=0; i<n; i++){
            if(!visited[i]){
                answer++;
                BFS(i);
            }   
        }
        return answer;
    }
    void BFS(int start){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int x: graph.get(cur)){
                if(!visited[x]){
                    q.add(x);
                    visited[x] = true;
                }
            }
        }
    }
}
/*
집합 개수 구하는 것 
1~N노드 각 각 단순 BFS돌면서 해당 BFS로 진입하는 개수만 구하면 됨
*/