import java.io.*;
import java.util.*;

public class Main {
    static int[][] waterCost;
    static int n;
    static HashSet<Node> waterAmount = new HashSet<>() ;

    static class Node{
        long cost, water;
        public Node(long cost, long water){
            this.cost= cost;
            this.water= water;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        waterCost = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            waterCost[i][0] = Integer.parseInt(st.nextToken());
            waterCost[i][1] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        makeNM(new ArrayList<>(), new boolean[n]);
        for (int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int waterTarget = Integer.parseInt(st.nextToken());
            int maxTime = Integer.parseInt(st.nextToken());
            long result = Long.MAX_VALUE;
            for(Node cur: waterAmount){
                if((waterTarget/cur.water<=maxTime&&waterTarget%cur.water==0)||waterTarget/cur.water+1<=maxTime&&waterTarget%cur.water!=0){
                    result=Math.min(result, cur.cost);
                }
            }
            String output = (result == Long.MAX_VALUE) ? "Case " + i + ": IMPOSSIBLE" : "Case " + i + ": " + result;
            sb.append(output).append("\n");
        }

        System.out.print(sb);
    }
    static void makeNM(ArrayList<Integer> arr, boolean []visited){
        if(!arr.isEmpty()){
            long water =0; long cost=0;
            for(int x: arr){
                water+=waterCost[x][0];
                cost+=waterCost[x][1];
            }
            waterAmount.add(new Node(cost, water));
            //System.out.println(water+" : "+ cost);
        }
        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                arr.add(i);
                makeNM(arr, visited);
                arr.remove((Integer) i);
                visited[i] = false;
            }
        }

    }
}
