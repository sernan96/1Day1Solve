import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[] current, target;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        current = br.readLine().toCharArray();
        target = br.readLine().toCharArray();

        int res1 = simulate(false); // 1번 스위치 안 누른 경우
        int res2 = simulate(true);  // 1번 스위치 누른 경우

        if (res1 == -1 && res2 == -1) {
            System.out.println(-1);
        } else if (res1 == -1) {
            System.out.println(res2);
        } else if (res2 == -1) {
            System.out.println(res1);
        } else {
            System.out.println(Math.min(res1, res2));
        }
    }

    // 스위치 누르기 시뮬레이션
    static int simulate(boolean pressFirst) {
        char[] temp = current.clone();
        int count = 0;

        if (pressFirst) {
            press(temp, 0); // 0번 인덱스: 1번 스위치
            count++;
        }

        for (int i = 1; i < N; i++) {
            if (temp[i - 1] != target[i - 1]) {
                press(temp, i);
                count++;
            }
        }

        // 마지막 상태 비교
        for (int i = 0; i < N; i++) {
            if (temp[i] != target[i]) return -1;
        }

        return count;
    }

    // i번째 스위치 누르기: i-1, i, i+1 전구 토글
    static void press(char[] arr, int i) {
        for (int j = i - 1; j <= i + 1; j++) {
            if (j >= 0 && j < N) {
                arr[j] = (arr[j] == '0') ? '1' : '0';
            }
        }
    }
}
