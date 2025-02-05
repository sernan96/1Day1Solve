import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H;
    static int [][]map;
    static boolean [][][] visited;
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NM = new StringTokenizer(br.readLine());
        N = Integer.parseInt(NM.nextToken());
        M = Integer.parseInt(NM.nextToken());
        map = new int[N][M];
        H = -1;
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<M; j++){
                int n = input.charAt(j)-'0';
                H = Math.max(H, n);
                map[i][j] = n;
            }
        }
        visited= new boolean[N][M][H+1];
        int result = 0;
        //1층 부터 H까지 담을 수 있는 물 check하는 반복문
        for(int i=1; i<=H; i++){
            for(int j =0; j<N; j++){
                for(int k =0; k<M; k++){
                    if(!visited[j][k][i]&& map[j][k]<i){
                        result+=BFS(j, k, i);
                    }
                }
            }
        }
        System.out.print(result);
    }
    static int [] dx = {0, 1, 0, -1};
    static int [] dy = {1, 0, -1, 0};
    static int BFS(int x, int y, int height){
        ArrayDeque<Node> q = new ArrayDeque<>();
        visited[x][y][height] = true;
        q.add(new Node(x, y));
        int cnt =1;
        boolean leaking = false;
        while(!q.isEmpty()){
            Node cur = q.poll();
            for(int i=0; i<4; i++){
                int mx = cur.x+dx[i];
                int my = cur.y+dy[i];
                if(canGo(mx, my) && !visited[mx][my][height] && map[mx][my] < height){
                    cnt++;
                    q.add(new Node(mx, my));
                    visited[mx][my][height]=true;
                }else if(!canGo(mx, my)){
                    leaking = true;
                }
            }
        }
        return leaking?0:cnt;
    }

    static boolean canGo(int mx, int my) {
        return mx >= 0 && my >= 0 && mx < N && my < M;
    }
}
