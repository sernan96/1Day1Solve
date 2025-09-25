import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] heights = new long[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Long.parseLong(br.readLine());
        }

        Stack<Long> stack = new Stack<>();
        long answer = 0;

        for (int i = 0; i < N; i++) {
            long h = heights[i];

            // 현재 건물보다 작거나 같은 건물은 볼 수 없으므로 pop
            while (!stack.isEmpty() && stack.peek() <= h) {
                stack.pop();
            }

            // 스택에 남아있는 건물 수 만큼 현재 건물이 볼 수 있음
            answer += stack.size();

            // 현재 건물 push
            stack.push(h);
        }

        System.out.println(answer);
    }
}
