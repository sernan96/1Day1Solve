import java.io.*;
import java.util.*;
public class Main {
    static int N,M, result;
    static int [][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = 0;
        map = new int[N][M];
        for(int i=0; i<N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                HashSet <Integer> visited = new HashSet<>();
                visited.add(1000*i+j);//방문한 좌표 저장
                DFS(i, j, 0, 0, visited);
            }
        }
        System.out.print(result);
    }
    static int [] dx = {1, 0, -1, 0};
    static int [] dy = {0, 1, 0, -1};
    static void DFS(int i, int j, int depth, int sum, HashSet<Integer> visited){
        if(depth==4){
            result = Math.max(result, sum);
            return;
        }
        for(int dir=0; dir<4; dir++){
            int mx = i+ dx[dir];
            int my = j+ dy[dir];
            if(canGo(mx, my)&&!visited.contains(1000*mx+my)){
                if(depth==2){
                    visited.add(1000*mx+my);
                    DFS(i, j, depth+1, sum+map[mx][my], visited);
                    visited.remove(1000*mx+my);
                }
                visited.add(1000*mx+my);
                DFS(mx, my, depth+1, sum+map[mx][my], visited);
                visited.remove(1000*mx+my);
            }
        }
    }

    static boolean canGo(int x, int y){
        return x>=0&&y>=0&&x<N&&y<M;
    }
}