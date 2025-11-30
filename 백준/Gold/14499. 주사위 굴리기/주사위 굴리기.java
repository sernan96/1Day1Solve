import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 0:윗, 1:아랫, 2:북, 3:남, 4:서, 5:동
        int[] dice = new int[6];

        // 전개도 좌표 (윗면 위치만 추적용 → 출력에 사용)
        int[] topPos = {1, 1}; // 초기 윗면 (1,1)

        // 전개도 기반 윗면 회전 매핑
        // direction: 0동,1서,2북,3남
        int[][][][] link = new int[4][3][4][2];
        // 동
        link[1][1][0] = new int[]{1, 2};
        link[1][2][0] = new int[]{3, 1};
        link[3][1][0] = new int[]{1, 0};
        link[1][0][0] = new int[]{1, 1};
        // 서
        link[1][1][1] = new int[]{1, 0};
        link[1][0][1] = new int[]{3, 1};
        link[3][1][1] = new int[]{1, 2};
        link[1][2][1] = new int[]{1, 1};
        // 북
        link[1][1][2] = new int[]{0, 1};
        link[0][1][2] = new int[]{3, 1};
        link[3][1][2] = new int[]{2, 1};
        link[2][1][2] = new int[]{1, 1};
        // 남
        link[1][1][3] = new int[]{2, 1};
        link[2][1][3] = new int[]{3, 1};
        link[3][1][3] = new int[]{0, 1};
        link[0][1][3] = new int[]{1, 1};

        // 실제 dice 값 배열 회전용
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            x = nx;
            y = ny;

            // 전개도 상 윗면 좌표 이동
            int[] next = link[topPos[0]][topPos[1]][dir];
            topPos = new int[]{next[0], next[1]};

            // 6면 배열 회전 적용
            rotate(dice, dir);

            // 지도와 상호작용
            if (map[x][y] == 0) map[x][y] = dice[1];
            else {
                dice[1] = map[x][y];
                map[x][y] = 0;
            }

            System.out.println(dice[0]); // 윗면 값 출력
        }
    }

    static void rotate(int[] d, int dir) {
        int t;
        switch (dir) {
            case 0: // 동
                t = d[0]; d[0] = d[4]; d[4] = d[1]; d[1] = d[5]; d[5] = t; break;
            case 1: // 서
                t = d[0]; d[0] = d[5]; d[5] = d[1]; d[1] = d[4]; d[4] = t; break;
            case 2: // 북
                t = d[0]; d[0] = d[3]; d[3] = d[1]; d[1] = d[2]; d[2] = t; break;
            case 3: // 남
                t = d[0]; d[0] = d[2]; d[2] = d[1]; d[1] = d[3]; d[3] = t; break;
        }
    }
}
