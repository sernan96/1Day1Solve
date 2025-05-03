import java.io.*;
import java.util.*;

public class Main {
    static int N, start, end, answer = Integer.MIN_VALUE;
    static List<Edge>[] graph;
    static boolean[] visited;

    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
            low = Math.min(low, weight);
            high = Math.max(high, weight);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        while (low <= high) {
            int mid = (low + high) / 2;
            visited = new boolean[N + 1];
            if (bfs(mid)) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(answer);
    }

    static boolean bfs(int minWeight) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == end) return true;

            for (Edge edge : graph[curr]) {
                if (!visited[edge.to] && edge.weight >= minWeight) {
                    visited[edge.to] = true;
                    queue.offer(edge.to);
                }
            }
        }

        return false;
    }
}
