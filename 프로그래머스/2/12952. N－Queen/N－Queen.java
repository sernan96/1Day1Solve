class Solution {
    static int N, cnt;
    static boolean[] col;
    static boolean[] diag1; // row - col + N - 1
    static boolean[] diag2; // row + col

    public int solution(int n) {
        N = n;
        cnt = 0;
        col = new boolean[N];
        diag1 = new boolean[2 * N];
        diag2 = new boolean[2 * N];

        dfs(0);
        return cnt;
    }

    void dfs(int row) {
        if (row == N) {
            cnt++;
            return;
        }

        for (int c = 0; c < N; c++) {
            int d1 = row - c + N - 1;
            int d2 = row + c;

            if (col[c] || diag1[d1] || diag2[d2]) continue;

            col[c] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            dfs(row + 1);

            col[c] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}