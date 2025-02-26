import java.util.*;

class Solution {
    static int [] edge, parent;
    public int[] solution(int[] nodes, int[][] edges) {
        int maxNode = -1;
        for(int x: nodes){
            maxNode = Math.max(maxNode, x);
        }
        edge = new int [maxNode+1];
        parent = new int [maxNode+1];
        for(int i = 1; i <= maxNode; i++) {
            parent[i] = i;
        }
        for(int [] x: edges){
            int n1 = x[0];
            int n2 = x[1];
            edge[n1]++;
            edge[n2]++;
            union(n1, n2);
        }
        Map<Integer, int[]> graph = new HashMap<>();
        for(int x: nodes){
            int tempHead = find(x);//임시반장
            int [] info = graph.containsKey(tempHead)?graph.get(tempHead):new int[]{0,0};
            int oddEven = info[0];
            int reverseOddEven = info[1];
            if((edge[x]%2==0&& x%2==0)||((edge[x]%2!=0&& x%2!=0)) ){ //짝수 홀수 노드인 경우
                oddEven++;
            }
            if((edge[x]%2==0&& x%2!=0)||((edge[x]%2!=0&& x%2==0))) {// 역홀짝 노드인 경우
                reverseOddEven++;
            }
            graph.put(tempHead, new int[]{oddEven, reverseOddEven});
        }
        int tree = 0;
        int reverseTree = 0;
        for(int [] x: graph.values()){
            int t = x[0];
            int rT = x[1];
            if(t==1){
                tree++;
            }
            if(rT==1){
                reverseTree++;
            }
        }
        return new int[]{tree, reverseTree};
    }
    static int find(int node){
        if(parent[node]==node){
            return node;
        }
        return parent[node] = find(parent[node]);
    }
    static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y){
            parent[y] = x;
        }
    }
}
//- 그래프 생성(어차피 홀짝, 역홀짝 개수만 알면 되기에 문제상 루트노드는 아니지만 집합의 임시루트(?)로 생각해서 트리의 정보만 쏙쏙 빼서 보면 되니 유니온 파인드로 하는게 편할듯함)
//- 홀수, 짝수, 역홀수, 역짝수 개수 저장할 class 생성 및 저장
//- 마지막에 홀짝, 역홀짝 트리 판별로 result 반환 및 종료