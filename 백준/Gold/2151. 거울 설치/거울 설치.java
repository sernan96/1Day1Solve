import java.io.*;
import java.util.*;

class Main {
    static int N;
    static class Node{
        int x, y, dir, cnt;
        Node(int x, int y, int dir, int cnt){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int [][] map = new int[N][N];
        int [] dx = {1, 0, -1, 0};//하 우 상 좌
        int [] dy = {0, 1, 0, -1};
        ArrayList<int[]> doors = new ArrayList<>();
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<N; j++){
               char x = input.charAt(j);
               if(x=='#'){
                   doors.add(new int [] {i,j});
               }
               map[i][j] = x=='#'?'.':x;
            }
        }
        //최소한의 거울을 설치 === 방향전환을 최소화
        ArrayDeque<Node> q = new ArrayDeque<>();
        int [][][] visited = new int[N][N][4];// 해당칸에서의 방향에 따른 최소거울개수 저장하고 있음
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int k =0; k<4; k++){
                    visited[i][j][k] = -1;
                }
            }
        }
        for(int i =0; i<4; i++){
            q.add(new Node(doors.get(0)[0],doors.get(0)[1], i, 0));
            visited[doors.get(0)[0]][doors.get(0)[1]][i] = 0;
        }
        int result = Integer.MAX_VALUE;
        int [] reflect = {1, 3};
        while (!q.isEmpty()){
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int dir = cur.dir;
            int cnt = cur.cnt;
            //System.out.println(x+", "+y+" dir: "+dir+" cnt: "+cnt);
            if(x==doors.get(1)[0] && y ==doors.get(1)[1]){//도착한 경우
                result = Math.min(result, cnt);
                continue;
            }
            else if(map[x][y]=='!'){//거울설치 가능
                //하 우 상 좌 순서임
                for(int mv_dir: reflect){
                    int mdir = (dir+mv_dir)%4;
                    int mx = x+dx[mdir];
                    int my = y+dy[mdir];
                    if(canGo(mx, my)&&(visited[mx][my][mdir]==-1|| cnt+1<visited[mx][my][mdir])&&map[mx][my]!='*'){
                        visited[mx][my][mdir] = cnt+1;
                        q.add(new Node(mx, my, mdir, cnt+1));
                    }
                }
            }
            //그냥 직진할때
            int sx = x+dx[dir];
            int sy = y+dy[dir];
            if(canGo(sx, sy)&&(visited[sx][sy][dir]==-1||visited[sx][sy][dir]>cnt)&&map[sx][sy]!='*'){
                visited[sx][sy][dir] = cnt;
                q.add(new Node(sx, sy, dir, cnt));
            }
        }
        System.out.print(result);
    }
    static boolean canGo(int x, int y){
        return  x>=0&&y>=0&&x<N&&y<N;
    }
}