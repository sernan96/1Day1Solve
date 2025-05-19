import java.io.*;
import java.util.*;
public class Main {
    static int[] house;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        house = new int[N];
        for(int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);
        int left = 1;
        int right = house[N - 1] - house[0] + 1;
        while(left < right) {
            int mid = (right + left) / 2;

            if(canInstall(mid) < M) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        System.out.print(left - 1);
    }

    public static int canInstall(int distance) {

        int count = 1;
        int lastLocate = house[0];

        for(int i = 1; i < house.length; i++) {
            int locate = house[i];
            if(locate - lastLocate >= distance) {
                count++;
                lastLocate = locate;
            }
        }
        return count;
    }
}