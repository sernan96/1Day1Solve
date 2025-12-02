import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    // 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static boolean ok = false;   // 답을 찾았는지 여부

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        int rx = 0, ry = 0;   // 빨간 구슬 위치
        int bx = 0, by = 0;   // 파란 구슬 위치

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                if (c == 'R') {
                    rx = i;
                    ry = j;
                    map[i][j] = '.';   // 구슬 자리는 빈칸으로 처리
                } else if (c == 'B') {
                    bx = i;
                    by = j;
                    map[i][j] = '.';
                } else {
                    map[i][j] = c;
                }
            }
        }

        dfs(rx, ry, bx, by, 0);

        System.out.println(ok ? 1 : 0);
    }

    // 구슬 하나를 dir 방향으로 끝까지 굴리는 함수
    // 반환: {최종 x, 최종 y, 이동한 칸 수, 구멍에 빠졌는지(1/0)}
    static int[] roll(int x, int y, int dir) {
        int dist = 0;
        int isFall = 0;

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 안전하게 범위 체크
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;

            // 벽이면 멈춘다
            if (map[nx][ny] == '#') break;

            // 한 칸 이동
            x = nx;
            y = ny;
            dist++;

            // 구멍에 빠졌으면 종료
            if (map[x][y] == 'O') {
                isFall = 1;
                break;
            }
        }

        return new int[]{x, y, dist, isFall};
    }

    static void dfs(int rx, int ry, int bx, int by, int depth) {
        if (ok) return;          // 이미 정답 찾았으면 종료
        if (depth == 10) return; // 10번 이상은 안 됨

        for (int dir = 0; dir < 4; dir++) {
            int[] r = roll(rx, ry, dir);
            int[] b = roll(bx, by, dir);

            int rnx = r[0], rny = r[1];
            int bnx = b[0], bny = b[1];
            int rDist = r[2], bDist = b[2];
            boolean rFall = (r[3] == 1);
            boolean bFall = (b[3] == 1);

            // 파란 구슬이 빠졌으면 이 경우는 실패
            if (bFall) continue;

            // 빨간 구슬만 빠졌으면 성공
            if (rFall) {
                ok = true;
                return;
            }

            // 둘 다 안 빠졌는데 같은 칸에 있으면, 더 많이 움직인 구슬이 뒤에 있었던 거라 한 칸 뒤로 뺀다
            if (rnx == bnx && rny == bny) {
                if (rDist > bDist) {
                    rnx -= dx[dir];
                    rny -= dy[dir];
                } else {
                    bnx -= dx[dir];
                    bny -= dy[dir];
                }
            }

            // 둘 다 제자리면 더 진행해도 의미 없음
            if (rnx == rx && rny == ry && bnx == bx && bny == by) continue;

            dfs(rnx, rny, bnx, bny, depth + 1);
        }
    }
}
