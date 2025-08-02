import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean [] visited;
    static int [][] dp;
    
    public int solution(int n, int[][] lighthouse) {
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        for(int [] light: lighthouse){
            graph.get(light[0]).add(light[1]);
            graph.get(light[1]).add(light[0]);
        }
        dp = new int[n+1][2];
        visited = new boolean[n+1];
        DFS(1);
        return Math.min(dp[1][0], dp[1][1]);
    }
    static void DFS(int node){
        visited[node] = true;
        dp[node][0] = 0; // 등대 꺼진 경우
        dp[node][1] = 1; // 등대 켜진 경우
        for(int x: graph.get(node)){
            if(visited[x]){
                continue;
            }
            DFS(x);
            dp[node][0] += dp[x][1];
            dp[node][1] += Math.min(dp[x][0], dp[x][1]);
        }
    }
    //마지막엔 결국 인덱스 1에 전부 저장되게끔 설계
}
