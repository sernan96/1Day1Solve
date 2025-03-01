import java.util.*;
class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        //2중 ArrayList로 그래프 표현
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<Integer>());
        }
        //무방향 그래프이기에 양쪽에 전부 연결
        for (int []x: edge_list){
            int start = x[0];
            int end = x[1];
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        int [][] dp = new int[k][n+1]; // 시간에 따른 각 노드의 오류 최소값 저장
        for(int []x: dp){
            Arrays.fill(x, Integer.MAX_VALUE);
        }
        dp[0][gps_log[0]] = 0;
        for(int i=1; i<k; i++){//시간별 1씩 증가하며 순회
            for(int j=1; j<=n; j++){
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);//그 노드 그대로 있었던 상황
                for(int x: graph.get(j)){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][x]);
                }
                dp[i][j] += j!=gps_log[i]&&dp[i][j]!=Integer.MAX_VALUE? 1:0;
            }
        }
        return dp[k-1][gps_log[k-1]]==Integer.MAX_VALUE?-1:dp[k-1][gps_log[k-1]];
    }
}