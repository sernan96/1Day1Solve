import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    public static void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] line = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            line[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch(line));
    }

    static String binarySearch(int[] line) {
        String answer = "";
        int min = 1;
        int max = N;

        while (min <= max) {
            int mid = (min + max) / 2;
            String result = possible(line, mid);

            if(result.isEmpty()) {
                max = mid - 1;
            } else {
                min = mid + 1;
                answer = result;
            }
        }

        return answer;
    }

    static String possible(int[] line, int std) {
        int cout = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        cout += 1;
        int before = line[0];
        for (int i = 1; i < line.length; i++) {
            if (cout == M) {
                sb.append(0);
            } else {
                if (line[i] - before >= std) {
                    sb.append(1);
                    cout += 1;
                    before = line[i];
                } else {
                    sb.append(0);
                }
            }
        }
        if (cout == M) {
            return sb.toString();
        }
        return "";
    }
}