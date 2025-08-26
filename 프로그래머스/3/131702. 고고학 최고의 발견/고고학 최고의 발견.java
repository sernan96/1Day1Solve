import java.util.*;

class Solution {
    int N;
    int result = Integer.MAX_VALUE;

    int[][] base;

    int[] firstRow;

    static final int[] dx = {0, 0, 0, 1, -1};
    static final int[] dy = {0, 1, -1, 0, 0};

    public int solution(int[][] clockHands) {
        N = clockHands.length;
        base = new int[N][N];
        firstRow = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                base[i][j] = (4 - clockHands[i][j]) & 3; // (4 - a) % 4, 비음수라 &3 사용 가능
            }
        }

        dfs(0);
        return result;
    }

    private void dfs(int col) {
        if (col == N) {
            simulate(); 
            return;
        }
        for (int k = 0; k < 4; k++) {
            firstRow[col] = k;
            dfs(col + 1);
        }
    }

    private void simulate() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(base[i], 0, tmp[i], 0, N);
        }

        int[] cur = Arrays.copyOf(firstRow, N);

        int presses = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = cur[j];
                if (k == 0) continue;

                presses += k;
                if (presses >= result) return;

                for (int d = 0; d < 5; d++) {
                    int x = i + dx[d], y = j + dy[d];
                    if (x < 0 || y < 0 || x >= N || y >= N) continue;
                    tmp[x][y] = (tmp[x][y] + 4 - k) & 3;
                }
            }

            if (i + 1 < N) {
                for (int j = 0; j < N; j++) {
                    cur[j] = tmp[i + 1 - 1][j];
                }
            }
        }

        for (int j = 0; j < N; j++) {
            if (tmp[N - 1][j] != 0) return;
        }
        result = Math.min(result, presses);
    }
}
