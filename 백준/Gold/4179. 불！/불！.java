import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    public static void main(String [] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char [][] map = new char [N][M];
        int [] jLoc = new int[2];
        ArrayDeque<int []> jQ = new ArrayDeque<>();
        ArrayDeque<int []> fQ = new ArrayDeque<>();
        boolean [][] visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                char x= str.charAt(j);
                map[i][j] = x;
                if(x =='J'){
                    jQ.add(new int[]{i, j});
                    visited[i][j] = true;
                }
                else if(x =='F'){
                    fQ.add(new int[]{i, j});
                }
            }
        }
        int [] dx = {1, 0, -1, 0};
        int [] dy = {0, 1, 0, -1};
        for(int run =1; ; run++){
            //불이 없는 경우는 그냥 넘어감
            //새로운 불 위치 넣기 -> 번질 수 있는 불
            ArrayList<int []> fNext = new ArrayList<>();
            while(!fQ.isEmpty()){
                int [] cur = fQ.poll();
                for(int i=0; i<4; i++){
                    int mx = cur[0]+dx[i];
                    int my = cur[1]+dy[i];
                    if(canGo(mx, my)&&map[mx][my]=='.'){
                        fNext.add(new int[]{mx, my});
                        map[mx][my] = 'F';
                    }
                }
            }
            if(!fNext.isEmpty()){
                fQ.addAll(fNext);
            }
            //지훈 이동가능 좌표 넣기
            ArrayList<int []> jNext = new ArrayList<>();
            while(!jQ.isEmpty()){
                int [] cur = jQ.poll();
                for(int i=0; i<4; i++){
                    int mx = cur[0]+dx[i];
                    int my = cur[1]+dy[i];
                    if(!canGo(mx, my)){
                        System.out.print(run);
                        return;
                    }
                    if(canGo(mx,my) && map[mx][my]=='.'&&!visited[mx][my]){
                        jNext.add(new int[]{mx, my});
                        visited[mx][my] = true;
                    }
                }
            }
            if(jNext.size()==0){
                System.out.print("IMPOSSIBLE");
                return;
            }
            else{
                jQ.addAll(jNext);
            }
            
        }
    }
    static boolean canGo(int x, int y){
        return x>=0&&y>=0&&x<N&&y<M;
    }
}
// 지훈이가 움직일 수 있는 위치 BFS로 리스트 저장 (.으로 이동)
// 불이 확산되는 BFS 1회 수행(새로 추가된 불들은 다음 BFS에서 돌아줘야함)
// 다음 움직일 지점이 탈출지점이라면 종료
