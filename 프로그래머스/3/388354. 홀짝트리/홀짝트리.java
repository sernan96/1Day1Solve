import java.util.*;

class Solution {

    private int[] inDegree;
    private int[] parent;

    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = {};

        int lastNode = 0;
        for(int node : nodes) {
            lastNode = Math.max(lastNode, node);
        }

        inDegree = new int[lastNode + 1];
        parent = new int[lastNode + 1];
        for(int i = 1; i <= lastNode; i++) {
            parent[i] = i;
        }

        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            inDegree[a]++;
            inDegree[b]++;
            merge(a, b);
        }

        Map<Integer, TreeInfo> MAP = new HashMap();
        for(int node : nodes) {
            int group = find(node);

            TreeInfo t = MAP.getOrDefault(group, new TreeInfo());

            if((node % 2 == 0) && (inDegree[node] % 2 == 0)) {
                t.evenNode++;
            } else if((node % 2 == 1) && (inDegree[node] % 2 == 1)) {
                t.oddNode++;
            } else if((node % 2 == 0) && (inDegree[node] % 2 == 1)) {
                t.reverseEvenNode++;
            } else if((node % 2 == 1) && (inDegree[node] % 2 == 0)) {
                t.reverseOddNode++;
            }

            MAP.put(group, t);
        }

        int tree = 0;
        int rTree = 0;
        for(TreeInfo treeInfo : MAP.values()) {
            if(treeInfo.isTree()) {
                tree++;
            }

            if(treeInfo.isReverseTree()) {
                rTree++;
            }    
        }

        return new int[]{tree, rTree};
    }

    public class TreeInfo {
        public int oddNode;
        public int evenNode;
        public int reverseOddNode;
        public int reverseEvenNode;

        public TreeInfo() {
            this.oddNode = 0;
            this.evenNode = 0;
            this.reverseOddNode = 0;
            this.reverseEvenNode = 0;
        }

        public boolean isTree() {
            if((oddNode == 1 && evenNode == 0) || (oddNode == 0 && evenNode == 1)) {
                return true;
            }

            return false;
        }

        public boolean isReverseTree() {
            if((reverseOddNode == 1 && reverseEvenNode == 0) || (reverseOddNode == 0 && reverseEvenNode == 1)) {
                return true;
            }

            return false;
        }
    }

    public int find(int num) {
        if(parent[num] == num) return num;
        return parent[num] = find(parent[num]);
    }

    public void merge(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            parent[b] = a;
        }
    }
}