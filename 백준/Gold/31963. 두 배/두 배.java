import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        long cnt = 0;
        while (st.hasMoreTokens()){
            arr.add(Integer.parseInt(st.nextToken()));
        }
        if(N==1){
            System.out.print(cnt);
            return;
        }

        long[] cnt_arr = new long[N];
        for(int i=1; i<N; i++){
            double ratio = Math.ceil(Math.log(arr.get(i - 1) / (double)arr.get(i) )/ Math.log(2)) + cnt_arr[i-1];
            if (ratio > 0) {
                cnt_arr[i] = Math.max(0, (long)ratio);
                cnt += cnt_arr[i];
            }
        }
        System.out.print(cnt);
    }
}