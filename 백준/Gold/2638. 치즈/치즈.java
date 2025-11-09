import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int [][] map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                char x = st.nextToken().charAt(0);
                if(x=='1'){
                    map[i][j] = 1;
                }
            }
        }
        boolean flag = true;
        int [] dx = {1, 0, -1, 0};
        int [] dy = {0, 1, 0, -1};
        int turn=0;
        while (flag){
            flag = false;
            boolean [][] outAir = new boolean[N][M];
            // 외부 공기 BFS
            ArrayDeque<int []>q = new ArrayDeque<>();
            q.add(new int[]{0,0});
            outAir[0][0] = true;
            while (!q.isEmpty()){
                int [] cur = q.poll();
                for(int i=0; i<4; i++){
                    int mx = cur[0]+dx[i];
                    int my = cur[1]+dy[i];
                    if(canGo(mx, my)&&map[mx][my]!=1&&!outAir[mx][my]){
                        q.add(new int[]{mx, my});
                        outAir[mx][my] = true;
                    }
                }
            }
            ArrayList<int[]> removeCheese = new ArrayList<>();
            for(int i=1; i<N-1; i++){
                for(int j=1; j<M-1; j++){
                    if(map[i][j]==1){
                        flag = true;
                        int outAirCnt = 0;
                        for(int dir = 0; dir<4; dir++){
                            int mx =i+dx[dir];
                            int my =j+dy[dir];
                            if(outAir[mx][my]){
                                outAirCnt++;
                            }
                        }
                        if(outAirCnt>=2){
                            removeCheese.add(new int[]{i,j});
                        }
                    }
                }
            }
            if(!flag){
                System.out.print(turn);
                return;
            }
            for(int [] x: removeCheese){
                map[x[0]][x[1]] = 0;
            }
            

            turn++;
        }
    }
    static boolean canGo(int x, int y ){
        return x>=0 && y>=0 && x<N && y<M;
    }
}
/*
외부 공기와 접촉하는 것으로 간주하는 것은 
외부공기-> 맨 가장자리에서 BFS 돌아서 -1로 표시
치즈 1로 표시
녹아 없어질 치즈는 그냥 2중 반복문으로 치즈면 주변에 외부공기 2개 이상 있을 시에  
녹아없어질 치즈 짜로 저장해두었다가 
해당 턴 끝날때 없애주기
그리고 flag로 치즈가 존재하는지 체크해주고
만약 해당 턴에 치즈가 검출안됐으면 종료
*/