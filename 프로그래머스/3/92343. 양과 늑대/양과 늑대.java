import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> graph;
    static int[] infos;
    static int result;

    public int solution(int[] info, int[][] edges) {
        graph = new ArrayList<>();
        infos = info;
        result = 0;

        for (int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] x : edges) {
            graph.get(x[0]).add(x[1]);
        }

        ArrayList<Integer> next = new ArrayList<>();
        DFS(0, next, 0, 0);
        return result;
    }

    static void DFS(int node, ArrayList<Integer> next, int sheep, int wolf) {
        if (infos[node] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if (wolf >= sheep) {
            return;
        }

        result = Math.max(result, sheep);

        ArrayList<Integer> temp = new ArrayList<>(next);
        temp.remove(Integer.valueOf(node));
        if (!graph.get(node).isEmpty()) {
            temp.addAll(graph.get(node));
        }
        temp.remove(Integer.valueOf(node));
        for (int x : temp) {
            DFS(x, new ArrayList<>(temp), sheep, wolf);
        }
    }
}
