import java.io.*;
import java.util.*;

public class Main {
    static int R, C, N;
    static int [][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for(int i=0; i<R; i++){
            String input = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = input.charAt(j)=='.'? 0: 2;
            }
        }
        int [] dx = {1, 0, 0, -1};
        int [] dy = {0, 1, -1, 0};
        for(int time = 1; time<=N; time++){
            if(time==N){
                break;
            }
            //폭탄이 설치되어 있지 않은 모든 칸에 폭탄 설치
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++) {
                    // 터트릴 경우 vs 아닌 경우
                    if(map[i][j]==1){
                        map[i][j] = 0;
                        for(int dir=0; dir<4; dir++){
                            int mx = i+dx[dir];
                            int my = j+dy[dir];
                            if(canGo(mx, my)&&map[mx][my]!=1){
                                map[mx][my] = 0;
                            }
                        }
                    }
                    else{
                        map[i][j] = map[i][j]!=0? map[i][j]-1:3;
                    }
                }
            }
            time++;
            if(time==N){
                break;
            }
            //시간 1초 지났으니 1초 줄이고 터진 폭탄 처리
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++) {
                    // 터트릴 경우 vs 아닌 경우
                    if(map[i][j]==1){
                        map[i][j] = 0;
                        for(int dir=0; dir<4; dir++){
                            int mx = i+dx[dir];
                            int my = j+dy[dir];
                            if(canGo(mx, my)&&map[mx][my]!=1){
                                map[mx][my] = 0;
                            }
                        }
                    }
                    else{
                        map[i][j] = map[i][j]!=0? map[i][j]-1:0;
                    }
                }
            }
        }
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                System.out.print(map[i][j]==0?'.':'O');
            }
            System.out.println();
        }
    }
    static boolean canGo(int x, int y){
        return x>=0 && y>=0 && x<R&&y<C;
    }
}
/*
일단 R*C 격자에 폭탄의 남은 초를 적어둔다.
그렇게 지시대로 1초가 줄때마다
0보다 큰 양수의 정수값을 지닌 칸들은 전부 -1씩 해주고
만약 0이 되는 칸이 있다면 상하좌우를 0으로 만들어준다.

*/
