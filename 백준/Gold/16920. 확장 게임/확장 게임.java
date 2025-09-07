import java.io.*;
import java.util.*;

public class Main {
    static int N, M, P;
    static int[] S;                 // 각 플레이어의 최대 확장 거리
    static char[][] board;
    static boolean[][] visited;     // 점령/벽 여부
    static ArrayDeque<int[]>[] q;   // 플레이어별 프런티어 큐
    static int[] ans;               // 플레이어별 점령 칸 수

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        S = new int[P + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) S[i] = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M];
        q = new ArrayDeque[P + 1];
        ans = new int[P + 1];
        for (int i = 1; i <= P; i++) q[i] = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                board[i][j] = c;
                if (c == '#') {
                    visited[i][j] = true;             // 벽
                } else if (c == '.') {
                    // 빈칸
                } else { // '1'..'9'
                    int p = c - '0';
                    visited[i][j] = true;             // 해당 플레이어의 성 (이미 점령됨)
                    q[p].add(new int[]{i, j});        // 프런티어 초기화
                    ans[p]++;                          // 초기 성 포함
                }
            }
        }

        boolean progressed = true;
        while (progressed) {
            progressed = false;

            // 플레이어 1 -> P 순서로, 각자 S[p] "층" 만큼만 확장
            for (int p = 1; p <= P; p++) {
                // S[p]번 층 반복
                for (int step = 0; step < S[p]; step++) {
                    int layerSize = q[p].size();
                    if (layerSize == 0) break; // 더 이상 프런티어 없음

                    // 현재 층만 처리해서 다음 층 프런티어를 만든다
                    for (int t = 0; t < layerSize; t++) {
                        int[] cur = q[p].poll();
                        int x = cur[0], y = cur[1];

                        for (int dir = 0; dir < 4; dir++) {
                            int nx = x + dx[dir], ny = y + dy[dir];
                            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                            if (visited[nx][ny]) continue;
                            if (board[nx][ny] != '.') continue; // 빈칸만 점령 가능

                            visited[nx][ny] = true;
                            board[nx][ny] = (char) ('0' + p);
                            q[p].add(new int[]{nx, ny});
                            ans[p]++;
                            progressed = true;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= P; i++) {
            if (i > 1) sb.append(' ');
            sb.append(ans[i]);
        }
        System.out.println(sb);
    }
}
