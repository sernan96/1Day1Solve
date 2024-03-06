import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer, int[]> cnts = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            if (!cnts.containsKey(a)) {
                cnts.put(a, new int[2]);
            }
            if (!cnts.containsKey(b)) {
                cnts.put(b, new int[2]);
            }

            cnts.get(a)[0] += 1;
            cnts.get(b)[1] += 1;
        }

        for (Map.Entry<Integer, int[]> entry : cnts.entrySet()) {
            int key = entry.getKey();
            int[] exchangeCnt = entry.getValue();

            if (exchangeCnt[0] >= 2 && exchangeCnt[1] == 0) {
                answer[0] = key;
            } else if (exchangeCnt[0] == 0 && exchangeCnt[1] > 0) {
                answer[2] += 1;
            } else if (exchangeCnt[0] >= 2 && exchangeCnt[1] >= 2) {
                answer[3] += 1;
            }
        }

        answer[1] = cnts.get(answer[0])[0] - answer[2] - answer[3];

        return answer;
    }
}