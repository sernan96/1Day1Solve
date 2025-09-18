import java.io.*;
import java.util.*;

public class Main {
    static int R, C, result=0;
    static boolean success;
    static int [][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for(int i=0; i<R; i++){
            String input = br.readLine();
            for(int j=0; j<C; j++){
                if(input.charAt(j)=='.'){
                    map[i][j] = 1;
                }
            }
        }
        for(int i=0; i<R; i++){
            success=false;
            DFS(i, 0);
        }
        System.out.print(result);
    }
    static int [] dx ={-1, 0, 1};
    static void DFS(int x, int y){
        if(y==C-1){//도달
            result++;

            map[x][y] = 0;
            success= true;
            return;
        }
        for(int i =0; i<3; i++){
            int mx = x+dx[i];
            int my = y+1;
            if(canGo(mx, my)&&map[mx][my]==1){
                DFS(mx, my);
                if(success){
                    map[x][y] = 0;
                    return;
                }
                else{
                    map[x][y] = 0;
                }
            }
        }

    }
    static boolean canGo(int x, int y){
        return x>=0&&y>=0&&x<R&&y<C;
    }
}
/*
DFS를 통해서 이미 방문했었던 성공이든 실패한 곳들은 방문할 필요가 없기에
먼저 다음 재귀문을 통해 도출해낸 결과를 통해 방문처리를 해주는게 핵심

*/