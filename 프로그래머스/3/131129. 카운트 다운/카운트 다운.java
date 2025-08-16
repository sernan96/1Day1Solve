import java.util.*;

class Solution {
    public int[] solution(int target) {
        int INF = 1_000_000_000;
        int[][] dp = new int[target + 1][2]; // [라운드 수, 싱글+불 수]

        // 초기값: 아직 못 만드는 상태를 INF로
        for (int i = 1; i <= target; i++) {
            dp[i][0] = INF;
            dp[i][1] = 0;
        }
        dp[0][0] = 0;
        dp[0][1] = 0;

        // 1발로 도달 가능한 점수 초기화 (싱글/더블/트리플/불)
        for (int k = 1; k <= 20; k++) {
            for (int j = 1; j <= 3; j++) {
                int s = k * j;
                if (s <= target) {
                    dp[s][0] = 1;
                    dp[s][1] = (j == 1 ? 1 : 0); // 싱글이면 +1
                }
            }
        }
        if (50 <= target) {
            dp[50][0] = 1;
            dp[50][1] = 1; // 불도 싱글처럼 카운트
        }

        // 점화식: i 점수를 만드는 최소 라운드, 동률 시 싱글+불 최대
        for (int i = 1; i <= target; i++) {
            // 싱글/더블/트리플
            for (int k = 1; k <= 20; k++) {
                for (int j = 1; j <= 3; j++) {
                    int score = k * j;
                    int prev = i - score;
                    if (prev < 0 || dp[prev][0] == INF) continue;

                    int candRounds = dp[prev][0] + 1;
                    int candSB = dp[prev][1] + (j == 1 ? 1 : 0);

                    if (candRounds < dp[i][0] || (candRounds == dp[i][0] && candSB > dp[i][1])) {
                        dp[i][0] = candRounds;
                        dp[i][1] = candSB;
                    }
                }
            }
            // 불(50점)
            if (i - 50 >= 0 && dp[i - 50][0] != INF) {
                int candRounds = dp[i - 50][0] + 1;
                int candSB = dp[i - 50][1] + 1; // 불 카운트 +1
                if (candRounds < dp[i][0] || (candRounds == dp[i][0] && candSB > dp[i][1])) {
                    dp[i][0] = candRounds;
                    dp[i][1] = candSB;
                }
            }
        }

        return dp[target];
    }
}
