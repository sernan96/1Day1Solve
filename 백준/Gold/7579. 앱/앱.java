import java.io.*;
import java.util.*;

public class Main {
    static class Pair implements Comparable<Pair> {
        int mem, cost;

        public Pair(int mem, int cost) {
            this.mem = mem;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair b) {
            return this.cost - b.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NM = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(NM.nextToken());
        int M = Integer.parseInt(NM.nextToken());
        StringTokenizer memory = new StringTokenizer(br.readLine());
        StringTokenizer cValue = new StringTokenizer(br.readLine());
        
        int[] mem = new int[N];
        int[] cost = new int[N];
        int totalCost = 0;
        
        for (int i = 0; i < N; i++) {
            mem[i] = Integer.parseInt(memory.nextToken());
            cost[i] = Integer.parseInt(cValue.nextToken());
            totalCost += cost[i];
        }
        
        int[] dp = new int[totalCost + 1];
        
        Arrays.fill(dp, 0);

        for (int i = 0; i < N; i++) {
            for (int j = totalCost; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + mem[i]);
            }
        }
        
        int result = totalCost;
        for (int i = 0; i <= totalCost; i++) {
            if (dp[i] >= M) {
                result = i;
                break;
            }
        }
        
        System.out.println(result);
    }
}
