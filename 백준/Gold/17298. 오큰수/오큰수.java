import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> st = new Stack<>();

        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            bw.write("-1");
            bw.close();
            return;
        }

        // 입력 값 저장
        String[] s = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        // 결과를 계산
        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!st.empty() && st.peek() <= arr[i]) {
                st.pop();
            }
            
            output[i] = st.empty() ? -1 : st.peek();
            st.push(arr[i]);
        }

        // 결과 출력
        for (int value : output) {
            bw.write(value + " ");
        }
        bw.close();
    }
}