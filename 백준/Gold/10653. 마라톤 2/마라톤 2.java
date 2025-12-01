import java.io.*;
import java.util.*;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int [][] points = new int[N][2];
        int [][] visited = new int[N][K+1];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        int result = Integer.MAX_VALUE;
        Arrays.fill(visited[0], 0);
        for(int i=0; i<N; i++){
            for(int j=0; j<=K; j++){//j는 뛴 횟수
                //뛸 수 있는 범위 만큼 전부 최신화 해줘야함
                for(int move=1; move+j-1<=K&&i+move<N; move++){//기본으로 1칸은 이동해야함
                    int gap = Math.abs(points[i][0]-points[i+move][0])+Math.abs(points[i][1]-points[i+move][1]);
                    visited[i+move][j+move-1] = Math.min(visited[i+move][j+move-1], visited[i][j]+gap);
                }
            }
        }
        for (int x: visited[N-1]){
            result = Math.min(result, x);
        }
        System.out.print(result);
    }
}