import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] top = new int[H + 1];
        int[] bottom = new int[H + 1];
        
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if (i % 2 == 0) { // 석순
                bottom[input]++;
            } else { // 종유석
                top[input]++;
            }
        }
        
        for (int i = H - 1; i > 0; i--) {
            bottom[i] += bottom[i + 1];
            top[i] += top[i + 1];
        }
        
        int min = N;
        int cnt = 0;
        
        for (int i = 1; i <= H; i++) {
            int total = bottom[i] + top[H - i + 1];
            if (total < min) {
                min = total;
                cnt = 1;
            } else if (total == min) {
                cnt++;
            }
        }
        
        System.out.println(min + " " + cnt);
    }
}
