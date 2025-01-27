import java.io.*;
import java.util.*;
/*
* (1.1) 부터 불을 켠 방들은 전부 set에 저장해둔다.
상하좌우로 DFS 탐색을 하며 움직일 수 있는 곳이라면 재귀로 이동
마지막에 set의 size 반환
* */
public class Main {
    static class Pair {
        int x, y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    static int N, M;
    static ArrayList<Pair> [][] xyab;
    static HashSet<Pair> light = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        xyab = new ArrayList[N+1][ N+1];
        for(int i=0; i<=N; i++){
            for(int j=0; j<=N; j++){
                xyab[i][j]=new ArrayList<Pair>();
            }
        }
        for(int i=0; i<M; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(input.nextToken());
            int y = Integer.parseInt(input.nextToken());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            xyab[x][y].add( new Pair(a, b));
        }
        light.add(new Pair(1,1));
        visited = new boolean[N+1][N+1];
        BFS();
        System.out.print(light.size());
    }
    static boolean [][]visited;
    static int[] dx = {0,1,-1,0};
    static int[] dy = {1,0,0,-1};
    static void BFS(){
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(1, 1));
        visited[1][1] = true;
        while(!q.isEmpty()){
            Pair current = q.poll();
            int x=  current.x;
            int y= current.y;
            //문제인 부분 탐색이 너무 느림
            if(!xyab[x][y].isEmpty()){
                visited = new boolean[N+1][N+1];
                visited[x][y] = true;
                for(Pair pairs : xyab[x][y]){
                    light.add(new Pair(pairs.x, pairs.y)); //어차피 set이라 중복없음
                }
                xyab[x][y].clear();
            }
            for(int i=0; i<4; i++){
                int mx = dx[i]+x;
                int my = dy[i]+y;
                if(mx>0&&mx<N+1&&my>0&&my<N+1&&!visited[mx][my]&& light.contains(new Pair(mx, my))){
                    visited[mx][my] = true;
                    q.add(new Pair(mx, my));
                }
            }
        }

    }
}
