import java.util.*;
class Solution {
    static int result;
    static ArrayList<String> arr;
    public int solution(String begin, String target, String[] words) {
        result = Integer.MAX_VALUE;
        arr = new ArrayList<>(Arrays.asList(words));
        if (!arr.contains(target)) return 0; // target이 없으면 0 반환
        boolean[] visited = new boolean[arr.size()];
        findMin(begin, target, visited, 0);
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    static void findMin(String now, String target, boolean[] visited, int cnt) {
        if (now.equals(target)) {
            result = Math.min(result, cnt);
            return;
        }

        for (int i = 0; i < arr.size(); i++) {
            if (!visited[i] && check(now, arr.get(i))) {
                visited[i] = true;
                findMin(arr.get(i), target, visited, cnt + 1);
                visited[i] = false;
            }
        }
    }

    static boolean check(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff == 1;
    }
}
