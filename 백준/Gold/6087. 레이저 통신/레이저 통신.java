import java.io.*;
import java.util.*;

public class Main {
    static int W, H;
    static class Node {
        int x, y, dir, mirrorCnt;
        public Node(int x, int y, int dir, int mirrorCnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirrorCnt = mirrorCnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer WH = new StringTokenizer(br.readLine());
        W = Integer.parseInt(WH.nextToken()); // y
        H = Integer.parseInt(WH.nextToken()); // x

        int[][] map = new int[H][W];
        ArrayList<int[]> cLocation = new ArrayList<>();

        for(int i=0; i<H; i++) {
            String input = br.readLine();
            for(int j=0; j<W; j++){
                char ch = input.charAt(j);
                if(ch == '*') {
                    map[i][j] = -1; // 벽
                } else if(ch == 'C') {
                    cLocation.add(new int[]{i, j});
                }
            }
        }

        // dist[x][y][dir] : (x,y)에 dir 방향으로 도달하기 위한 최소 거울 수
        int[][][] dist = new int[H][W][4];
        for(int[][] arr : dist){
            for(int[] row : arr){
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }

        // 방향: 우0, 하1, 좌2, 상3
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        // 시작점
        int sx = cLocation.get(0)[0];
        int sy = cLocation.get(0)[1];

        Deque<Node> dq = new ArrayDeque<>();

        for(int d=0; d<4; d++){
            dist[sx][sy][d] = 0;
            dq.offer(new Node(sx, sy, d, 0));
        }

        int result = Integer.MAX_VALUE;

        while(!dq.isEmpty()) {
            Node cur = dq.pollFirst();
            int x = cur.x;
            int y = cur.y;
            int dir = cur.dir;
            int mirrorCnt = cur.mirrorCnt;

            if(dist[x][y][dir] < mirrorCnt) continue;

            if(x == cLocation.get(1)[0] && y == cLocation.get(1)[1]){
                result = Math.min(result, mirrorCnt);
                continue;
            }

            // 1) 직진 (거울 추가 0)
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(nx>=0 && ny>=0 && nx<H && ny<W && map[nx][ny] != -1) {
                // dist[nx][ny][dir]가 더 큰 값이면 갱신 후 덱 앞쪽에 추가
                if(dist[nx][ny][dir] > mirrorCnt) {
                    dist[nx][ny][dir] = mirrorCnt;
                    dq.offerFirst(new Node(nx, ny, dir, mirrorCnt));
                }
            }

            for(int nd=0; nd<4; nd++){
                if(nd == dir) continue; // 같은 방향은 회전 아님(이미 위에서 직진 처리)
                int cost2 = mirrorCnt + 1;
                int rx = x + dx[nd];
                int ry = y + dy[nd];
                if(rx>=0 && ry>=0 && rx<H && ry<W && map[rx][ry] != -1) {
                    // dist[rx][ry][nd]가 더 큰 값이면 갱신 후 덱 뒤쪽에 추가
                    if(dist[rx][ry][nd] > cost2) {
                        dist[rx][ry][nd] = cost2;
                        dq.offerLast(new Node(rx, ry, nd, cost2));
                    }
                }
            }
        }

        System.out.print(result);
    }
}
