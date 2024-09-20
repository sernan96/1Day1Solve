import java.io.*;
import java.util.*;
public class Main {
    static int M, N, cnt=0;
    static int [][] map, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];
        for(int i=0; i<M; i++){
            StringTokenizer input =new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(input.nextToken());
                dp[i][j] =-1;
            }
        }
        DFS(0,0);
        System.out.print(dp[0][0]);
    }
    static int []dx ={1, 0, -1, 0};
    static int []dy ={0, 1, 0, -1};
    static int DFS(int x, int y){
        if(x==M-1 && y==N-1){
            return 1;
        }
        if(dp[x][y]>=0){
            return dp[x][y];
        }
        dp[x][y] =0;
        for(int i=0; i<4; i++){
            int mx = dx[i]+x;
            int my = dy[i]+y;
            if(mx>=0 && mx<M && my>=0 && my<N && map[x][y]>map[mx][my]){
                dp[x][y]+=DFS(mx, my);
            }
        }
        return dp[x][y];
    }
}
