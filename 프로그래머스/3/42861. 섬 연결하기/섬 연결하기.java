import java.util.*;
class Solution {
    static class Edge{
        int nodeA, nodeB, weight;
        public Edge(int nodeA, int nodeB, int weight){
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.weight = weight;
        }
    }
    static int [] parents;
    public int solution(int n, int[][] costs) {
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(Comparator.comparingInt((Edge o1)->o1.weight));
        parents = new int [n];
        for(int i=0; i<n; i++){
            parents[i] = i;
        }
        int answer = 0;
        for(int [] x: costs){
            pq.add(new Edge(x[0], x[1], x[2]));
        }
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(find(cur.nodeA)==find(cur.nodeB)){
                //System.out.println(find(cur.nodeA)+" "+find(cur.nodeB));
                continue;
            }
            union(cur.nodeA, cur.nodeB);
            answer+=cur.weight;
        }
        return answer;
    }
    static int find(int x){
        if(x==parents[x]){
            return x;
        }
        return parents[x] = find(parents[x]);
    }
    static void union(int x, int y){
        int xP = find(x);
        int yP = find(y);
        if(xP > yP){
            parents[xP] = yP;
        }
        else if(xP<yP){
            parents[yP] = xP;
        }
    }
}
//우선순위큐에 각 엣지들을 넣어서
//작은 순대로 뽑으며 연결해줌
// 단 사이클을 발생 시키는 엣지는 제외
// 사이클이 어떻게 발생하는 지는 hashSet에 이미 연결된 노드의 번호를 저장
// 만약 set에 엣지의 양 노드가 모두 존재한다면 continue 아니라면 노드 추가해주고 answer+= 엣지 가중치
// 반례 발생 따로 그룹핑이 된 덩어리들끼리 연결할때 문제가 생김