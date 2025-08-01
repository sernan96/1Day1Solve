import java.io.*;
import java.util.*;
public class Main {
    static int N, result;
    static int [][] map;
    static boolean [][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int [N][N];
        result = 0;
        int maxHeight = -1;
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int height = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, height);
                map[i][j] = height;
            }
        }
        for(int rain=0; rain<=maxHeight; rain++){
            //각 비의 높이 마다 BFS돌기
            visited = new boolean[N][N];
            int count = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j]>rain && !visited[i][j]){
                        BFS(i, j, rain);
                        count++;
                    }
                }
            }

            result = Math.max(result, count);
        }
        System.out.print(result);
    }

    static int [] dx = {1, 0, -1, 0};
    static int [] dy = {0, 1, 0, -1};
    static void BFS(int i, int j, int rain){
        ArrayDeque<int []> q = new ArrayDeque<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;
        while (!q.isEmpty()){
            int [] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            for(int dir = 0; dir<4; dir++){
                int mx = x+dx[dir];
                int my = y+dy[dir];
                if(canGo(mx, my)&&!visited[mx][my]&&map[mx][my]>rain){
                    q.add(new int []{mx, my});
                    visited[mx][my] = true;
                }
            }
        }
    }
    static boolean canGo(int x, int y){
        return x<N&&y<N&&x>=0&&y>=0;
    }
}
/*
각 비가 올 수 있는 수를 순회하면서
BFS를 돌며 최대 영역 수를 탐색한다.
그럼 높이가 최대 100이므로
100번의 BFS를 돈다.
그험 100*100*100
충분히 가능한 시간으로 보임
*/