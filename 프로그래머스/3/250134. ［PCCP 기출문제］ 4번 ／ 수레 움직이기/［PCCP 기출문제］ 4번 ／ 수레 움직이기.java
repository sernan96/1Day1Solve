import java.util.*;

class Solution {
    static int N, M;
    static class Node{
        int [] loc;
        boolean [][][] visited;
        public Node(int [] loc, boolean [][][] visited){
            this.loc = loc;
            this.visited = visited;
        }
    }
    public int solution(int[][] maze) {
        int answer = Integer.MAX_VALUE;
        N = maze.length;
        M = maze[0].length;
        int [] end = new int [4];
        int [] start = new int [4];
        // 벽과 빈칸 빼고 정보 저장 후 전부 초기화 
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(maze[i][j]==1){
                    start[0] = i;
                    start[1] = j;
                }
                else if(maze[i][j]==2){
                    start[2] = i;
                    start[3] = j;
                }
                else if(maze[i][j]==3){
                    end[0] = i;
                    end[1] = j;
                }
                else if(maze[i][j]==4){
                    end[2] = i;
                    end[3] = j;
                }
                if(maze[i][j]!=5){
                    maze[i][j] =0;
                }
            }
        }
        //BFS를 돌면서 이동
        
        ArrayDeque<Node> q = new ArrayDeque<>();
        boolean [][][] arr = new boolean[N][M][2];
        arr[start[0]][start[1]][0] = true;
        arr[start[2]][start[3]][1] = true;
        
        q.add(new Node(new int[]{start[0], start[1], start[2],start[3], 0},arr ));
        int [] dx = {1, 0, -1, 0};
        int [] dy = {0, 1, 0, -1};
        while(!q.isEmpty()){
            Node now = q.poll();
            int [] cur = now.loc;
            int nowRX = cur[0];
            int nowRY = cur[1];
            int nowBX = cur[2];
            int nowBY = cur[3];
            int cnt = cur[4];
            if(cnt>answer){
                continue;
            }
            if(nowRX == end[0] && nowRY == end[1] && nowBX == end[2] && nowBY == end[3]){
                answer = Math.min(answer, cnt);
                continue;
            }
            boolean [] isDest = {true, true};
            for(int i=0; i<4; i++){
                int moveRX = nowRX;
                int moveRY = nowRY;
                if(nowRX != end[0] || nowRY != end[1]){
                    moveRX += dx[i];
                    moveRY += dy[i]; 
                    isDest[0] = false;
                }
                for(int j = 0; j<4; j++){
                    int moveBX = nowBX;
                    int moveBY = nowBY;
                    if(nowBX != end[2] || nowBY != end[3]){
                        moveBX += dx[j];
                        moveBY += dy[j];
                        isDest[1] = false; 
                    }
                    // 서로 겹치지않고 이동이 가능하다면
                    if(canGo(moveRX, moveRY)&&canGo(moveBX, moveBY) 
                       && (moveRX!=moveBX ||moveRY!=moveBY) 
                       && ((moveRX!= nowBX || moveRY!= nowBY) || (moveBX!= nowRX || moveBY!=nowRY))
                       && maze[moveRX][moveRY]!=5 && maze[moveBX][moveBY]!=5 ){
                        // 이미 자신의 도착칸에 도착했다면 이동할 수 없기에 visited 검사 X
                        
                        if((!isDest[0] && now.visited[moveRX][moveRY][0])||(!isDest[1] && now.visited[moveBX][moveBY][1])){
                            continue;
                        }
                        //System.out.println("빨간수레: "+moveRX+", "+moveRY+" 파란수레: "+moveBX+", "+moveBY+" 카운트: "+(cnt+1));
                        boolean [][][] nowVisited = deepCopy(now.visited);
                        nowVisited[moveRX][moveRY][0] = true;
                        nowVisited[moveBX][moveBY][1] = true;
                        q.add(new Node(new int[]{moveRX, moveRY, moveBX, moveBY, cnt+1}, nowVisited));
                    }
                }
            }
        }
        return answer==Integer.MAX_VALUE? 0:answer;
    }
    static boolean canGo(int x, int y){
        return x>=0&&y>=0&&x<N&&y<M;
    }
    static boolean [][][] deepCopy(boolean [][][] arr){
        boolean [][][] temp = new boolean[N][M][2];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                for(int k=0; k<2; k++){
                    temp[i][j][k] = arr[i][j][k];
                }
            }
        }
        return temp;
    }
}

/*
BFS를 통해서 두 수레의 위치를 이동시킨다.
큐에 다음 이동할 위치를 넣는 경우는 최대 16가지이다.
조심할 것은 서로가 교차해서 지나갈 수 없고 
같은 곳에서 동시에 존재할 수 없으며
자신이 방문했던 곳은 또한 갈 수 없다.
그런데 단순히 방문처리를 각각 진행한다고 했을때는 최단거리를 구하기에 문제가 있다.
그럼으로 int [i][j][빨간수레 or 파란수레] visited 배열로 해당 위치에 도달하는 최단거리를 기재함으로써
-> 이렇게 했을때 같이 이동할때 이전에 안되는 이동으로 쵣나거리를 기재해둬서 이후에 행동이 막힐 가능성있음
그래서 어차피 map크기가 매우작기에 각 이동마다 방문터리를 따로 진행해준다.
방문처리를 진행해준다.
그렇다면 큐에 넣어줄 배열은
int형으로 크기가 5인 배열
{빨간 수레 x, 빨간수레  y, 파란 수레 x, 파란 수레 y, 턴 수}
*/