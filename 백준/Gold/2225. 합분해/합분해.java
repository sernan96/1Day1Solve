import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long mod = 1000000000;
        long dp[][] = new long[n + 1][k + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i][1] = 1;
        }
        for (int i = 1; i < k + 1; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
            }
        }
        System.out.println(dp[n][k] % mod);
    }
}