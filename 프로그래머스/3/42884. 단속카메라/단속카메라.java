import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (a, b) -> Integer.compare(a[0],b[0]));
        int end_point = -30001;
        for(int [] car : routes){
            if(car[0]>end_point){
                answer++;
                end_point = car[1];
            }
            end_point = Math.min(car[1], end_point);
        }
        return answer;
    }
}