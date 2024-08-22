import java.io.*;
import java.util.*;

public class Main{
    static class Pair{
        int x, y, breaked;
        public Pair(int x, int y, int breaked){
            this.x = x;
            this.y = y;
            this.breaked = breaked;
        }
    }
    static int [][] map;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NM = new StringTokenizer(br.readLine());
        N = Integer.parseInt(NM.nextToken());
        M = Integer.parseInt(NM.nextToken());
        map = new int[N+2][M+2];
        for(int i=0; i<=N+1; i++){
            String input = (i>0 && i<N+1)? br.readLine():"";
            for(int j=0; j<=M+1; j++){
                if(i>=1 && i<=N && j<=M && j>=1){
                    char x = input.charAt(j-1);
                    map[i][j] = (x == '1') ? 1 : 0;
                }
                else{
                    map[i][j] = -1; // 경계 처리
                }
            }
        }
        BFS();
    }

    static void BFS(){
        Queue<int[]> go = new LinkedList<>();
        boolean[][] visitedNoBreak = new boolean[N+2][M+2];
        boolean[][] visitedBreak = new boolean[N+2][M+2];
        int[][] distanceNoBreak = new int[N+2][M+2];
        int[][] distanceBreak = new int[N+2][M+2];

        int[] mx = { 0, -1, 0, 1};
        int[] my = { -1, 0, 1, 0};

        go.add(new int[]{1, 1, 0});
        distanceNoBreak[1][1] = 1;

        while (!go.isEmpty()){
            int[] current = go.poll();
            int x = current[0];
            int y = current[1];
            int breaked = current[2];

            if(x == N && y == M){
                int result = (breaked == 0) ? distanceNoBreak[x][y] : distanceBreak[x][y];
                System.out.print(result);
                return;
            }

            for(int i = 0; i < 4; i++){
                int dx = x + mx[i];
                int dy = y + my[i];

                if(dx >= 1 && dx <= N && dy >= 1 && dy <= M){
                    if(map[dx][dy] == 0){ // 이동 가능한 경우
                        if(breaked == 0 && !visitedNoBreak[dx][dy]){
                            go.add(new int[]{dx, dy, 0});
                            visitedNoBreak[dx][dy] = true;
                            distanceNoBreak[dx][dy] = distanceNoBreak[x][y] + 1;
                        } else if(breaked == 1 && !visitedBreak[dx][dy]){
                            go.add(new int[]{dx, dy, 1});
                            visitedBreak[dx][dy] = true;
                            distanceBreak[dx][dy] = distanceBreak[x][y] + 1;
                        }
                    } else if(map[dx][dy] == 1 && breaked == 0 && !visitedBreak[dx][dy]){ // 벽을 부수는 경우
                        go.add(new int[]{dx, dy, 1});
                        visitedBreak[dx][dy] = true;
                        distanceBreak[dx][dy] = distanceNoBreak[x][y] + 1;
                    }
                }
            }
        }

        System.out.print("-1"); // 경로가 없을 경우
    }
}
