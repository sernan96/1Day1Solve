import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1));
        boolean [][]visit = new boolean[maps.length][maps[0].length];
        visit[0][0]= true;
        while(!q.isEmpty()){
            Node current = q.poll();
            int x = current.x;
            int y = current.y;
            int cnt = current.cnt;
            if(x==maps.length-1&&y==maps[0].length-1){
                return cnt;
            }
            for(int i=0; i<4; i++){
                int mx = dx[i]+x;
                int my = dy[i]+y;
                if(mx<0||mx>= maps.length ||my<0||my>= maps[0].length||maps[mx][my]==0||visit[mx][my]){
                    continue;
                }visit[mx][my] = true;
                q.add(new Node(mx, my, cnt+1));
            }
        }
        return -1;
    }
    static int [] dx = {1, 0, -1, 0};
    static int [] dy = {0, -1, 0, 1};
    static class Node{
        int x, y, cnt;
        public Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}