import java.util.*;

class Solution {
    static PriorityQueue<Integer> pq;
    static boolean [][] visited;
    static String[] map;
    static int N, M;
    public int[] solution(String[] maps) {
        map = maps;
        pq = new PriorityQueue<>();
        N = maps.length;
        M = maps[0].length();
        visited = new boolean[N][M];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(!visited[i][j]&&map[i].charAt(j)!='X'){
                    BFS(i, j);
                }   
            }
        }
        
        if(pq.isEmpty()){
            return new int [] {-1};
        }
        int[] answer = new int[pq.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = pq.poll();
        }
        return answer;
    }
    static int [] dx = {1, 0, -1, 0};
    static int [] dy = {0, 1, 0, -1};
    static void BFS(int i, int j){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int []{i, j});
        visited[i][j] = true;
        int sum = 0;
        while(!q.isEmpty()){
            int [] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            sum+=map[x].charAt(y)-'0';
            for(int dir=0; dir<4; dir++){
                int mx = x+dx[dir];
                int my = y+dy[dir];
                if(canGo(mx, my)&&!visited[mx][my]&&map[mx].charAt(my)!='X'){
                    q.add(new int [] {mx, my});
                    visited[mx][my]= true;
                }
            }
        }
        pq.add(sum);
    }
    static boolean canGo(int x, int y){
        return x>=0&&y>=0&&x<N&&y<M;
    }
}
/*
전체적인 순서 
각 칸을 순회하며 각 칸에서 BFS를 돎
BFS를 돌다가 더이상 이동할 수 없거나 해당 섬을 전부 탐색하였다면
우선순위큐에 넣고 그걸 전부 반복 
그냥 마지막에 우선순위큐가 비어있으면 -1 출력 아니라면 배열에 담아서 return
*/