import java.io.*;
import java.util.*;

public class Main{
    static int [][] map;
    static int N, M, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NM = new StringTokenizer(br.readLine());
        N = Integer.parseInt(NM.nextToken());
        M = Integer.parseInt(NM.nextToken());
        K = Integer.parseInt(NM.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<M; j++){
                char x = input.charAt(j);
                if(x=='1'){
                    map[i][j]= 1;
                }
                else{
                    map[i][j]=0;
                }
            }
        }
        System.out.print(BFS());
    }
    static int BFS(){
        Queue<int []> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0}); // x, y, 깨부신 벽 갯수
        boolean [][][] visited = new boolean[N][M][K+1];
        int [][] distance = new int [N][M];
        distance[0][0] = 1;
        visited[0][0][0] = true;
        int [] mx = {1, 0, -1, 0};
        int [] my = {0, 1, 0, -1};
        while (!q.isEmpty()){
            int [] current = q.poll();
            int x = current[0];
            int y = current[1];
            int crush = current[2];
            if(x == N-1 && y == M-1){
                return distance[N-1][M-1];
            }
            for(int i=0; i<4; i++){
                int dx = x + mx[i];
                int dy = y + my[i];
                if(dx>=0 && dx<N && dy>=0 && dy<M ){
                    if(map[dx][dy]==0 && !visited[dx][dy][crush]){
                        q.add(new int[]{dx, dy, crush});
                        visited[dx][dy][crush] = true;
                        distance[dx][dy] = distance[x][y] +1;
                    }
                    if(map[dx][dy]==1 && crush < K && !visited[dx][dy][crush+1]){
                        q.add(new int[]{dx, dy, crush+1});
                        visited[dx][dy][crush+1] = true;
                        distance[dx][dy] = distance[x][y] +1;
                    }
                }
            }

        }
        return -1;
    }
}