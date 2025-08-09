import java.util.*;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long longD = d;

        for (long x = 0; x <= d; x += k) {
            double maxY = Math.sqrt(longD * longD - x * x);

            answer += (long) (maxY / k) + 1;
        }
        return answer;
    }
}