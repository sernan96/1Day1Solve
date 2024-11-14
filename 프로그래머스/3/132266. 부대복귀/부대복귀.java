import java.util.*;

class Solution {
    static class Node {
        int cost, dir;
        Node(int dir, int cost) {
            this.dir = dir;
            this.cost = cost;
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] x : roads) {
            int start = x[0];
            int end = x[1];
            graph.get(start).add(new Node(end, 1));
            graph.get(end).add(new Node(start, 1));
        }

        // 다익스트라 초기화
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[destination] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.cost));
        pq.offer(new Node(destination, 0));
        boolean[] visited = new boolean[n + 1];

        // 다익스트라 알고리즘
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (visited[current.dir]) {
                continue;
            }
            visited[current.dir] = true;
            for (Node next : graph.get(current.dir)) {
                if (dist[next.dir] > dist[current.dir] + 1) {
                    dist[next.dir] = dist[current.dir] + 1;
                    pq.add(new Node(next.dir, dist[next.dir]));
                }
            }
        }

        // 결과 배열 생성
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]] != Integer.MAX_VALUE ? dist[sources[i]] : -1;
        }

        return answer;
    }
}
