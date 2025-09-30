import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long L = Long.parseLong(st.nextToken());

        int[][] seg = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken()); // [s, e) 그대로!
            seg[i][0] = s;
            seg[i][1] = e;
        }

        Arrays.sort(seg, Comparator.comparingInt(a -> a[0]));

        long pos = 0;   // 다음에 덮을 위치(미포함 개념)
        long ans = 0;

        for (int i = 0; i < N; i++) {
            long s = seg[i][0];
            long e = seg[i][1];

            if (pos < s) pos = s;      // 아직 안 덮였으면 시작점으로 점프
            if (pos >= e) continue;    // 이미 전부 덮였으면 패스

            long need = (e - pos + L - 1) / L; // 올림
            ans += need;
            pos += need * L; // pos는 항상 "다음에 덮을 위치"
        }

        System.out.println(ans);
    }
}
