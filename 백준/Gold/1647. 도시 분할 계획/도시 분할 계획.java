import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int idx, cost;
        public Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }
    static List<ArrayList<Node>> graph = new ArrayList<>();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<Node>());
        }
        while (M--!=0){
            StringTokenizer input = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(input.nextToken());
            int end = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());
            graph.get(start).add(new Node(end, cost));
            graph.get(end).add(new Node(start, cost));
        }
        findWay();
    }
    static void findWay(){
        PriorityQueue <Node> pq = new PriorityQueue<>(Comparator.comparingInt(o1->o1.cost));
        pq.add(new Node(1, 0));
        boolean []visited = new boolean[N+1];
        int max = 0;
        int sum = 0;
        while (!pq.isEmpty()){
            Node current = pq.poll();
            if(visited[current.idx]){
               continue;
            }
            visited[current.idx]= true;
            max = Math.max(max, current.cost);
            sum+= current.cost;
            for(Node x: graph.get(current.idx)){
                if(!visited[x.idx]){
                    pq.add(x);
                }
            }
        }
        System.out.print(sum-max);
    }
}