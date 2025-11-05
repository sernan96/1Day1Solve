import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static ArrayList<int[]> crazy;
    static boolean [][] crazyVisited;
    static int [] jongsu;
    static int [] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int [] dy = {0,-1, 0, 1, -1, 0, 1, -1, 0, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        crazy = new ArrayList<>();
        crazyVisited = new boolean[R][C];
        for(int i=0; i<R; i++){
            String input = br.readLine();
            for(int j=0; j<C; j++){
                if(input.charAt(j)=='I'){
                    jongsu = new int[]{i, j};
                }
                else if(input.charAt(j)=='R'){
                    crazy.add(new int[]{i, j});
                    crazyVisited[i][j] = true;
                }
            }
        }
        String command = br.readLine();
        for(int turn=0; turn<command.length(); turn++){
            //종수 이동
            int dir = command.charAt(turn)-'0';
            int mx = jongsu[0] +dx[dir];
            int my = jongsu[1] +dy[dir];
            if(mx<0||my<0||mx>=R||my>=C){
                mx = jongsu[0];
                my = jongsu[1];
            }
            else{
                jongsu[0] = mx;
                jongsu[1]= my;
            }
            //System.out.println("종수 위치 "+jongsu[0]+", "+jongsu[1]);
            // 종수 생존 확인
            if(crazyVisited[mx][my]){
                System.out.print("kraj "+(turn+1));
                return;
            }
            //미친아두이노들 이동(동시에 이동한다는 가정)
            crazyVisited = new boolean[R][C];
            HashSet<String> alive = new HashSet<>();
            HashSet<String> dead = new HashSet<>();
            for(int [] x: crazy){
                String moved = move(x);
                if(alive.contains(moved)){
                    dead.add(moved);
                }
                alive.add(moved);
                st = new StringTokenizer(moved);
                if(Integer.parseInt(st.nextToken())==jongsu[0]&&Integer.parseInt(st.nextToken())==jongsu[1]){
                    System.out.print("kraj "+(turn+1));
                    return;
                }
            }
            for(String x : dead){
                alive.remove(x);
            }
            crazy.clear();
            for(String x: alive){
                st = new StringTokenizer(x);
                int [] robot = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
                crazy.add(robot);
                crazyVisited[robot[0]][robot[1]]= true;
            }
            // 종수 생존 확인
            if(crazyVisited[jongsu[0]][jongsu[1]]){
                System.out.print("kraj "+(turn+1));
                return;
            }
        }
        for (int i=0; i<R; i++){
            for(int j = 0; j<C; j++){
                if(crazyVisited[i][j]){
                    System.out.print('R');
                }
                else if(jongsu[0]==i && jongsu[1]==j){
                    System.out.print('I');
                }
                else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }
    static String move (int [] arr){
        PriorityQueue<int []> pq = new PriorityQueue<>(Comparator.comparingInt(o1->Math.abs(jongsu[0]-o1[0])+Math.abs(jongsu[1]-o1[1])));
        for(int i=1; i<=9; i++){
            if(i==5){
                continue;
            }
            int mx = arr[0]+dx[i];
            int my = arr[1]+dy[i];
            if(mx<0||my<0||mx>=R||my>=C){
                continue;
            }
            pq.add(new int[]{mx, my});
        }
        if(pq.isEmpty()){
            return "";
        }
        int [] move = pq.poll();
        return move[0]+" "+move[1];
    }
}