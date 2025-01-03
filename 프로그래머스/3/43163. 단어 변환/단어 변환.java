import java.util.*;

class Solution {
    static int result;
    public int solution(String begin, String target, String[] words) {
        result = Integer.MAX_VALUE;
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(words));
        if (!arr.contains(target)) return 0; // target이 없으면 0 반환
        boolean[] visited = new boolean[arr.size()];
        findMin(begin, target, arr, visited, 0);
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    static void findMin(String now, String target, ArrayList<String> arr, boolean[] visited, int cnt) {
        if (now.equals(target)) {
            result = Math.min(result, cnt);
            return;
        }

        for (int i = 0; i < arr.size(); i++) {
            if (!visited[i] && isConvertible(now, arr.get(i))) {
                visited[i] = true;
                findMin(arr.get(i), target, arr, visited, cnt + 1);
                visited[i] = false;
            }
        }
    }

    static boolean isConvertible(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff == 1;
    }
}
