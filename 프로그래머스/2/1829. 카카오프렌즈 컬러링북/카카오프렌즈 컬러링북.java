import java.util.*;

class Solution {
    static boolean [][] visited;
    static int [][] picture;
    static int N, M;
    static HashMap<Integer, Integer> map;
    public int[] solution(int m, int n, int[][] pic) {
        map = new HashMap<>();
        picture = pic;
        N = picture.length;
        M = picture[0].length;
        visited = new boolean[N][M];
        int group = 1;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(picture[i][j]!=0 && !visited[i][j]){
                    map.put(group, 1);
                    BFS(i, j, picture[i][j], group);
                    group++;
                }
            }
        }
        int result = 0;
        for(int x: map.values()){
            result = Math.max(result, x);
        }
        return new int[]{map.size(), result};
    }
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int []dx = {0, 1, 0, -1};
    static int []dy = {1, 0, -1, 0};
    static void BFS(int i, int j, int target, int group){
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(i, j));
        visited[i][j] = true;
        picture[i][j] = group;
        while(!q.isEmpty()){
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            for(int k=0; k<4; k++){
                int mx = dx[k]+x;
                int my = dy[k]+y;
                if(canGo(mx, my)&&!visited[mx][my]&&picture[mx][my]==target){
                    picture[mx][my] = group;
                    visited[mx][my] = true;
                    map.put(group, map.get(group)+1);
                    q.add(new Node(mx, my));
                }
            }
        }
    }
    static boolean canGo(int x, int y){
        return x>=0&&y>=0&&x<N&&y<M;
    }
}