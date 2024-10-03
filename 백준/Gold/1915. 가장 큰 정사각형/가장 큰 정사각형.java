import java.io.*;
import java.util.*;
public class Main {
    static int n,m, max=0;
    static int [][] sum, map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sum = new int[n+1][m+1];
        map = new int[n+1][m+1];
        for(int i=1; i<=n; i++){
            String str = br.readLine();
            for(int j=1; j<=m; j++){
                map[i][j] = str.charAt(j-1) =='0'? 0:1;
            }
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + map[i][j];
            }
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(map[i][j]==1){
                    check(i,j);
                }
            }
        }
        System.out.print(max);
    }
    static void check(int x, int y){
        int len = 1;
        int res = 1;
        while (x+len<=n && y+len<=m){
            int dx = x+len;
            int dy = y+len;
            int area = sum[dx][dy]-sum[x-1][dy]-sum[dx][y-1]+sum[x-1][y-1];

            len++;

            if(area != len * len){
                break;
            }
            res = len * len;
        }
        max=Math.max(max, res);
    }

}
