import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [][]triangle = new int[n][n];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<i+1;j++){
                triangle[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        System.out.print(solution(triangle));
    }
    static int solution(int[][] triangle) {
        int answer = 0;
        int height = triangle.length;
        int [][]dp = new int[height][height];
        dp[0][0]= triangle[0][0];
        for(int i=0; i<height-1; i++){
            for(int j=0; j<=i; j++){
                dp[i+1][j] =Math.max(dp[i+1][j], triangle[i+1][j]+dp[i][j]);
                dp[i+1][j+1] =Math.max(dp[i+1][j+1], triangle[i+1][j+1]+dp[i][j]);
            }
        }
        for(int x:dp[height-1]){
            answer = Math.max(x,answer);
        }
        return answer;
    }
}
