import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1개 대각선 위와 2개 전 것들 총 3개 비교
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- != 0) {
            int N = Integer.parseInt(br.readLine());
            int[][] dp = new int[N][2];
            int[][] map = new int[N][2];
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[j][i] = Integer.parseInt(st.nextToken());
                }
            }
            dp[0][0] = map[0][0];
            dp[0][1] = map[0][1];
            for (int i = 1; i < N; i++) {
                dp[i][0] = dp[i - 1][1] + map[i][0];
                dp[i][1] = dp[i - 1][0] + map[i][1];
                if (i > 1) {
                    dp[i][0] = Math.max(dp[i][0], dp[i - 2][1] + map[i][0]);
                    dp[i][0] = Math.max(dp[i][0], dp[i - 2][0] + map[i][0]);
                    dp[i][1] = Math.max(dp[i][1], dp[i - 2][1] + map[i][1]);
                    dp[i][1] = Math.max(dp[i][1], dp[i - 2][0] + map[i][1]);
                }
            }
            sb.append(Math.max(dp[N - 1][0], dp[N - 1][1]) + "\n");
        }
        System.out.print(sb);
    }
}