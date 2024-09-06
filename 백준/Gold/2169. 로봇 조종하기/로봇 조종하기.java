import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [][] map = new int [N][M];
        for(int i=0; i<N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        int [] dp = new int [M];
        dp[0] = map[0][0];
        for(int i=1; i<M; i++){
            dp[i]+=dp[i-1]+map[0][i];
        }
        for(int i=1; i<N; i++){
            int []goLeft = new int[M];//왼쪽으로 갈때는 여기에 위꺼 vs 오른쪽꺼
            int []goRight = new int[M];//오른쪽으로 갈때는 위꺼 vs 왼쪽꺼
            for(int j=0; j<M; j++){//왼쪽
                if(j==0){//제일 가장자리이면 j==0일때 왼쪽꺼 비교 불가능임
                    goLeft[j] = dp[j] + map[i][j];
                }
                else{
                    goLeft[j]=Math.max(dp[j], goLeft[j-1])+ map[i][j];
                }
            }
            for(int j=M-1; j>=0; j--){//오른쪽
                if(j==M-1){//제일 가장자리이면 j==M-1일때 오른쪽꺼 비교 불가능임
                    goRight[j] = dp[j] + map[i][j];
                }
                else{
                    goRight[j]=Math.max(dp[j], goRight[j+1])+ map[i][j];
                }
            }
            for(int j=0; j<M; j++){
                dp[j] =Math.max(goLeft[j], goRight[j]);
            }
        }
        System.out.print(dp[M-1]);
    }
}
