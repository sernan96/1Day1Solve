import java.util.*;
class Solution {
    static class robot{
        public int x, y, dX, dY;
        public Queue <Integer> dest = new LinkedList<>();
        public boolean finish = false;
        public robot(int x, int y, Queue<Integer> dest){
            this.x = x;
            this.y = y;
            this.dest = dest;
            int num = dest.poll()-1;//처음 목적지 세팅
            this.dX = p[num][0];
            this.dY = p[num][1];
        }
        public void move(){
            if(this.dX==this.x && this.dY == this.y){
                if(dest.isEmpty()){
                    this.finish = true; 
                    return;   
                }
                else{
                    int num = dest.poll()-1;// 목적지 재세팅
                    this.dX = p[num][0];
                    this.dY = p[num][1];
                }
            }
            if(this.dX!=this.x){
                if(this.dX>this.x){//로봇이 아래로 움직여야함
                    this.x++;
                }
                else{
                    this.x--;
                }
            }
            else {
                if(this.dY>this.y){//로봇이 우측으로 움직여야함
                    this.y++;
                }
                else{
                    this.y--;
                }
            }
        }
    }
    static int [][] p, r;
    static int cnt=0;
    static ArrayList<robot> robots = new ArrayList<>();
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        p = points;
        for(int i=0; i<routes.length; i++){
            int []start = points[routes[i][0]-1];
            Queue <Integer> dest = new LinkedList<>();
            for(int j=1; j<routes[i].length; j++){
                dest.add(routes[i][j]);
            }
            robots.add(new robot(start[0], start[1], dest));
        }
        check();
        boolean flag = true;
        while(flag){
            flag = false;
            for(robot x: robots){
                if(!x.finish){
                    flag = true;
                    x.move();
                }
            }
            check();
        }
        return cnt;
    }
    static void check(){
        int [][] map = new int [101][101];
        for(robot x: robots){
            if(!x.finish){
                map[x.x][x.y]++;
            }
        }
        for(int i=1; i<101; i++){
            for(int j=1; j<101; j++){
                if(map[i][j]>1){
                    cnt++;
                }
            }
        }
    }
}