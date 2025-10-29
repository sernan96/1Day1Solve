import java.io.*;
import java.util.*;

public class Main {
    static int N, C, M;
    static class Order implements Comparable<Order> {
        int from, to, box;

        public Order(int from, int to, int box) {
            this.from = from;
            this.to = to;
            this.box = box;
        }

        @Override
        public int compareTo(Order other) {
            // 1. 도착지(to) 오름차순
            if (this.to != other.to) {
                return this.to - other.to;
            }
            // 2. 도착지가 같다면 출발지(from) 오름차순
            return this.from - other.from;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            orders.add(new Order(start, end, num));
        }

        Collections.sort(orders);

        int[] capacity = new int[N + 1];
        int result = 0;

        for (Order order : orders) {
            int from = order.from;
            int to = order.to;
            int box = order.box;

            int maxLoadOnRoute = 0;
            for (int i = from; i < to; i++) {
                maxLoadOnRoute = Math.max(maxLoadOnRoute, capacity[i]);
            }

            int availableCapacity = C - maxLoadOnRoute;
            int loadAmount = Math.min(box, availableCapacity);
            if (loadAmount > 0) {
                result += loadAmount;
                for (int i = from; i < to; i++) {
                    capacity[i] += loadAmount;
                }
            }
        }
        System.out.print(result);
    }
}