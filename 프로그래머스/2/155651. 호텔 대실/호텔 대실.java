import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, Comparator.comparing(o -> o[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;
        for (String[] book : book_time) {
            int enterTime = parseTime(book[0]);
            int outTime = parseTime(book[1]) + 10; 

            while (!pq.isEmpty() && pq.peek() <= enterTime) {
                pq.poll();
            }

            pq.add(outTime);

            answer = Math.max(answer, pq.size());
        }

        return answer;
    }

    private int parseTime(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}
