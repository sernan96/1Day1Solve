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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for(int i=0; i<=V; i++){
            graph.add(new ArrayList<Node>());
        }
        for(int i =0; i<E; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(input.nextToken());
            int end = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());
            graph.get(start).add(new Node(end, cost));
        }
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparing(o1 ->o1.cost));
        q.offer(new Node(startNode,0));
        int [] distance = new int[V+1];
        for(int i=0; i<=V; i++){
            distance[i]=Integer.MAX_VALUE;
        }
        distance[startNode]=0;
        while(!q.isEmpty()){
            Node current = q.poll();
            if(current.cost>distance[current.idx]){
                continue;
            }
            for(int i=0; i<graph.get(current.idx).size(); i++){
                Node next = graph.get(current.idx).get(i);
                if(distance[next.idx]>next.cost+current.cost){
                    distance[next.idx] = next.cost+current.cost;
                    q.add(new Node(next.idx, distance[next.idx]));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=V; i++){
            if(distance[i]==Integer.MAX_VALUE){
                sb.append("INF"+"\n");
            }
            else{
                sb.append(distance[i]+"\n");
            }
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}
