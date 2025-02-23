import java.util.*;
class Solution {
    static int N, M;
    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        int container = N*M;
        int [] dx = {1, 0, -1, 0};
        int [] dy = {0, 1, 0, -1};
        //저장은 int로
        int [][] map = new int[N+2][M+2];// 테두리에서부터 BFS 시작해야 하니 +2씩 
        for(int [] arr : map){
            Arrays.fill(arr, -1);
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                char x = storage[i].charAt(j);
                map[i+1][j+1] = x-'A';
                //System.out.print(x+" ");
            }
            //System.out.println();
        }
        //BFS 돌면서 -1과 해당 명령이 A라면 A에만 이동가능 -> 짐꺼냈으면 -1로 초기화 
        //만약 AA명령이라면 그냥 A 전부돌면서 -1로 바꿔줌
        //명령 돌아주는 반복문
        for(String command : requests){
            int target = command.charAt(0)-'A';
            if(command.length()==2){//크레인으로 제거하는 경우
                for(int i=1;i<N+1; i++){
                    for(int j=1; j<M+1; j++){
                        if(map[i][j]==target){
                            map[i][j]=-1;
                            container--;
                            //System.out.println("크레인로 제거: "+i+", "+j);
                        }
                    }
                }
            }
            else{ //지게차로 제거 -> BFS
                boolean [][] visited = new boolean [N+2][M+2];
                ArrayDeque <int[]> q = new ArrayDeque<>();
                visited[0][0] = true;
                q.add(new int[]{0,0});
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    int x = cur[0];
                    int y = cur[1];
                    for(int i=0; i<4; i++){
                        int mx = x + dx[i];
                        int my = y + dy[i];
                        if(canGo(mx, my)&&!visited[mx][my]&&(map[mx][my]==target||map[mx][my]==-1)){
                            if(map[mx][my]==target){
                                container--;
                                map[mx][my]=-1;
                                visited[mx][my] = true;
                                //System.out.println("지게차로 제거: "+mx+", "+my);
                                continue;
                            }
                            q.add(new int[]{mx, my});
                            visited[mx][my] = true;
                        }
                    }
                } 
            }
        }
        return container;
    }
    static boolean canGo(int x, int y){
        return x>=0&&y>=0&&x<N+2&&y<M+2;
    }
}