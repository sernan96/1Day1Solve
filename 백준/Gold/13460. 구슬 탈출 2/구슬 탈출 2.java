import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static char[][] map;
    static boolean[][][][] visit;
    static int[] dx = {-1,1,0,0}; // 상하좌우
    static int[] dy = {0,0,-1,1};

    static class State{
        int rx,ry,bx,by,cnt;
        State(int rx,int ry,int bx,int by,int cnt){
            this.rx=rx; this.ry=ry; this.bx=bx; this.by=by; this.cnt=cnt;
        }
    }

    static class Move{
        int x,y,dist;
        boolean hole;
        Move(int x,int y,int dist,boolean hole){
            this.x=x; this.y=y; this.dist=dist; this.hole=hole;
        }
    }

    static Move roll(int x,int y,int dir){
        int nx=x, ny=y, d=0;
        while(true){
            int tx = nx + dx[dir];
            int ty = ny + dy[dir];
            if(map[tx][ty]=='#') break;
            nx=tx; ny=ty; d++;
            if(map[nx][ny]=='O') return new Move(nx,ny,d,true);
        }
        return new Move(nx,ny,d,false);
    }

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[N][M][N][M];

        int rx=0,ry=0,bx=0,by=0;

        for(int i=0;i<N;i++){
            String s=br.readLine();
            for(int j=0;j<M;j++){
                map[i][j]=s.charAt(j);
                if(map[i][j]=='R'){ rx=i; ry=j; map[i][j]='.'; }
                if(map[i][j]=='B'){ bx=i; by=j; map[i][j]='.'; }
            }
        }

        Queue<State> q = new LinkedList<>();
        q.add(new State(rx,ry,bx,by,0));
        visit[rx][ry][bx][by]=true;

        while(!q.isEmpty()){
            State cur = q.poll();
            if(cur.cnt>=10) continue;

            for(int d=0;d<4;d++){
                Move r = roll(cur.rx,cur.ry,d);
                Move b = roll(cur.bx,cur.by,d);

                if(b.hole) continue;
                if(r.hole){
                    System.out.println(cur.cnt+1);
                    return;
                }

                if(r.x==b.x && r.y==b.y){
                    if(r.dist > b.dist){
                        r.x -= dx[d];
                        r.y -= dy[d];
                    }else{
                        b.x -= dx[d];
                        b.y -= dy[d];
                    }
                }

                if(!visit[r.x][r.y][b.x][b.y]){
                    visit[r.x][r.y][b.x][b.y]=true;
                    q.add(new State(r.x,r.y,b.x,b.y,cur.cnt+1));
                }
            }
        }

        System.out.print(-1);
    }
}
