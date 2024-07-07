import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [][] max = new int[N][3];
        int [][] min = new int[N][3];
        for(int i =0; i<N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                max[i][j] = Integer.parseInt(input.nextToken());
                min[i][j] = max[i][j];
            }
        }
        for(int i=1; i<N; i++){
            max[i][0]+=Math.max(max[i-1][0],max[i-1][1]);
            max[i][1]+=Math.max(max[i-1][0],Math.max(max[i-1][1], max[i-1][2]));
            max[i][2]+=Math.max(max[i-1][1], max[i-1][2]);
        }
        for(int i=1; i<N; i++){
            min[i][0]+=Math.min(min[i-1][0],min[i-1][1]);
            min[i][1]+=Math.min(min[i-1][0],Math.min(min[i-1][1], min[i-1][2]));
            min[i][2]+=Math.min(min[i-1][1], min[i-1][2]);
        }
        System.out.print(Math.max(max[N-1][0],Math.max(max[N-1][1],max[N-1][2]))+" "+Math.min(min[N-1][0],Math.min(min[N-1][1],min[N-1][2])));
    }
}
