import java.io.*;
import java.util.*;
public class Main {
    static int [][] dp, map;
    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] =num;
            }
        }
        int max = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                max = Math.max(max, DFS(i,j));
            }
        }
        System.out.print(max);
    }
    static int [] dx ={1, 0, -1, 0};
    static int [] dy ={0, 1, 0, -1};
    static int DFS(int x, int y){
        if(dp[x][y]!=0){
            return dp[x][y];
        }
        dp[x][y] = 1;
        for(int i=0; i<4; i++){
            int mx = dx[i] + x;
            int my = dy[i] + y;
            if(mx<0||mx>=N || my<0 || my>=N || map[x][y]>=map[mx][my]){
                continue;
            }
            dp[x][y] = Math.max(DFS(mx, my)+1, dp[x][y]);
        }
        return dp[x][y];
    }
}
