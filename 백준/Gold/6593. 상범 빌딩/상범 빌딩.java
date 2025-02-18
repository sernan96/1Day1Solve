import java.io.*;
import java.util.*;

class Main {
    static int L,R,C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int [] dz = {1, 0, -1};
        int [] dx = {1, 0, -1, 0};
        int [] dy = {0, 1, 0, -1};
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if(L+R+C==0){
                System.out.print(sb);
                return;
            }
            char [][][] map = new char [L][R][C];
            boolean [][][] visited = new boolean [L][R][C];
            int [] start = new int [3];
            int [] end = new int [3];
            for(int i=0; i<L; i++){
                for(int j=0; j<R; j++){
                    String input = br.readLine();
                    for(int k=0; k<C; k++){
                        char x = input.charAt(k);
                        map[i][j][k] = x;
                        if(x=='S'){
                            start[0] = i;
                            start[1] = j;
                            start[2] = k;
                            map[i][j][k] = '.';
                        }
                        else if(x=='E'){
                            end[0] = i;
                            end[1] = j;
                            end[2] = k;
                            map[i][j][k] = '.';
                        }
                    }
                }
                br.readLine();
            }
            ArrayDeque<int []> q = new ArrayDeque<>();
            q.add(new int[]{start[0], start[1], start[2], 0});
            visited[start[0]][start[1]][start[2]] = true;
            boolean flag = false;
            while(!q.isEmpty()){
                int [] cur = q.poll();
                int z = cur[0];
                int x = cur[1];
                int y = cur[2];
                int time = cur[3];
                if(z==end[0]&&x==end[1]&&y==end[2]){
                    sb.append("Escaped in "+time+" minute(s).").append("\n");
                    flag = true;
                    break;
                }
                for(int i=0; i<3; i++){
                    for(int j=0; j<4; j++){
                        int mz = z+dz[i];
                        int mx = x;
                        int my = y;
                        if(dz[i]==0){// 위층이나 아래층을 갈때는 무조건 상 하로만 움직여야하는데 그경우가 아닐때는 mx, my의 이동할 값 넣어줌
                            mx+=dx[j];
                            my+=dy[j];
                        }
                        if(canGo(mz, mx, my)&&!visited[mz][mx][my]&&map[mz][mx][my]=='.'){
                            q.add(new int[]{mz, mx, my, time+1});
                            visited[mz][mx][my] = true;
                        }
                    }
                }
            }
            if(!flag){
                sb.append("Trapped!").append("\n");
            }
        }
    }
    static boolean canGo(int floor, int x, int y){
        return floor>=0&&floor<L&&x>=0&&x<R&&y>=0&&y<C;
    }
}