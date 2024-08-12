import java.io.*;
import java.util.*;

public class Main{
    static int [][] map;
    static int N, area;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+2][N+2];
        StringBuilder sb = new StringBuilder();
        //입력부
        for(int i=1; i<=N; i++){
            String input = br.readLine();
            for(int j=1; j<=N; j++){
                if(input.charAt(j-1)=='1'){
                    map[i][j]++;
                }
            }
        }
        //탐색부
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(map[i][j]>0){
                    danzi(i, j);
                }
                if(area!=0){
                    pq.add(area);
                    area =0;
                }
            }
        }
        // 출력부
        sb.append(pq.size()+"\n");
        while (!pq.isEmpty()){
            sb.append(pq.poll()+"\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
    static void danzi(int x, int y){
        if(x<1 || y<1 || x>N || y>N ||map[x][y]==0){
            return;
        }
        area++;
        map[x][y]=0;
        danzi(x+1, y);
        danzi(x, y+1);
        danzi(x-1, y);
        danzi(x, y-1);
    }
}
