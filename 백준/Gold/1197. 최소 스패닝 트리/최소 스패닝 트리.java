import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int idx, cost;
        Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }
    static List<Node>[] graph;
    static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(o1->o1.cost));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer VE = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(VE.nextToken());
        int E = Integer.parseInt(VE.nextToken());
        graph = new ArrayList[V+1];
        for(int i =1; i<=V; i++){
            graph[i] = new ArrayList<Node>();
        }
        for(int i=0; i<E; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(input.nextToken());
            int end = Integer.parseInt(input.nextToken());
            int weight = Integer.parseInt(input.nextToken());
            graph[start].add(new Node(end, weight));
            graph[end].add(new Node(start, weight));
        }
        int result = 0;
        boolean []visited = new boolean[V+1];
        pq.offer(new Node(1, 0));
        while(!pq.isEmpty()){
            Node current = pq.poll();
            int ci =current.idx;
            if(visited[ci]){
                continue;
            }
            visited[ci]=true;
            result+= current.cost;
            for(Node x: graph[ci]){
                if(!visited[x.idx]){
                    pq.add(x);
                }
            }
        }
        System.out.print(result);
    }
}