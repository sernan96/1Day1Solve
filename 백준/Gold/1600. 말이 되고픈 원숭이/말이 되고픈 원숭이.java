import java.io.*;
import java.util.*;

public class Main {
    static int K, W, H;
    static int[][] map;
    static int[][][] dist;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] hx = {2, 2, -2, -2, 1, 1, -1, -1};
    static int[] hy = {1, -1, 1, -1, 2, -2, 2, -2};

    static boolean in(int x, int y){ return 0 <= x && x < H && 0 <= y && y < W; }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        if (H == 1 && W == 1) {
            System.out.println(map[0][0] == 0 ? 0 : -1);
            return;
        }

        dist = new int[K + 1][H][W];
        for (int u = 0; u <= K; u++)
            for (int i = 0; i < H; i++)
                Arrays.fill(dist[u][i], -1);

        ArrayDeque<int[]> q = new ArrayDeque<>();
        dist[0][0][0] = 0;
        q.add(new int[]{0, 0, 0}); // x, y, used

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], used = cur[2];

            if (x == H - 1 && y == W - 1) {
                System.out.println(dist[used][x][y]);
                return;
            }

            // 4방향
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (in(nx, ny) && map[nx][ny] == 0 && dist[used][nx][ny] == -1) {
                    dist[used][nx][ny] = dist[used][x][y] + 1;
                    q.add(new int[]{nx, ny, used});
                }
            }
            // 말 이동
            if (used < K) {
                for (int d = 0; d < 8; d++) {
                    int nx = x + hx[d], ny = y + hy[d];
                    if (in(nx, ny) && map[nx][ny] == 0 && dist[used + 1][nx][ny] == -1) {
                        dist[used + 1][nx][ny] = dist[used][x][y] + 1;
                        q.add(new int[]{nx, ny, used + 1});
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
