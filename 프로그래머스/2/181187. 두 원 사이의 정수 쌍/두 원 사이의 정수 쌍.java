import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;

        // 1. 제1사분면(x > 0, y > 0) 위의 점 개수 계산
        for (int x = 1; x <= r2; x++) {
            // 큰 원(r2)을 기준으로 최대 y값 계산
            long y_max = (long) Math.floor(Math.sqrt((long)r2 * r2 - (long)x * x));

            // 작은 원(r1)을 기준으로 최소 y값 계산
            long y_min = 0;
            if (x < r1) {
                // x가 r1 내부에 있을 경우, y는 r1 경계선 위 또는 밖이어야 함
                y_min = (long) Math.ceil(Math.sqrt((long)r1 * r1 - (long)x * x));
            } else {
                // x가 r1 경계 또는 외부에 있을 경우, y의 하한선은 1부터 시작
                // (y=0은 축 위의 점이므로 여기서는 세지 않음)
                y_min = 1;
            }

            // 해당 x좌표에서 가능한 y의 개수를 더함
            if (y_max >= y_min) {
                answer += (y_max - y_min + 1);
            }
        }
        
        // 제1사분면의 점 개수를 4배하여 4개 사분면의 점들을 모두 더함
        answer *= 4;

        // 2. x축과 y축 위에 있는 점들의 개수 추가
        // 각 축의 양/음 방향마다 (r2 - r1 + 1)개의 점이 존재
        long onAxisPoints = (long)(r2 - r1 + 1) * 4;
        answer += onAxisPoints;

        return answer;
    }
}