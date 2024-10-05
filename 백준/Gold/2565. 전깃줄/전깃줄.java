import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int []dp = new int[N];
        int [][] w = new int[N][2];
        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w[i][0] = Integer.parseInt(st.nextToken());
            w[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(w, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        for(int i=0; i<N; i++){
            dp[i]=1;
            for(int j=0; j<i; j++){
                if(w[i][1]>w[j][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int max =1;
        for (int x: dp){
            max = Math.max(max, x);
        }
        System.out.print(N-max);
    }
}
