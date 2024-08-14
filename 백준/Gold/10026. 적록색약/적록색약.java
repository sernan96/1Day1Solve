import java.io.*;
import java.util.*;

public class Main{

    static int [][] normalMap;
    static int [][] abnormalMap;
    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int [] normalArea ={0,0,0};
        int [] abnormalArea ={0,0};
        normalMap = new int[N+2][N+2];
        abnormalMap = new int[N+2][N+2];
        for(int i=1; i<=N; i++){
            String input = br.readLine();
            for(int j=1; j<=N; j++){
                if(input.charAt(j-1)=='R'){
                    normalMap[i][j]=1;
                    abnormalMap[i][j]=1;
                }
                else if(input.charAt(j-1)=='B'){
                    normalMap[i][j]=2;
                    abnormalMap[i][j]=2;
                }
                else {
                    normalMap[i][j]=3;
                    abnormalMap[i][j]=1;
                }
            }
        }
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if( normalMap[i][j]>0){
                    normalArea[normalMap[i][j]-1]++;
                    ndfs(i, j, normalMap[i][j]);
                }
                if( abnormalMap[i][j]>0){
                    abnormalArea[abnormalMap[i][j]-1]++;
                    adfs(i, j, abnormalMap[i][j]);
                }
            }
        }
        System.out.print((normalArea[0]+normalArea[1]+normalArea[2])+" "+ (abnormalArea[0]+abnormalArea[1]));
    }
    static void ndfs(int x, int y, int target){
        if(normalMap[x][y]!=target || x<1 || x>N || y<1 || y>N){
            return;
        }
        normalMap[x][y]=0;
        ndfs(x+1, y, target);
        ndfs(x, y+1, target);
        ndfs(x-1, y, target);
        ndfs(x, y-1, target);
    }
    static void adfs(int x, int y, int target){
        if(abnormalMap[x][y]!=target || x<1 || x>N || y<1 || y>N){
            return;
        }
        abnormalMap[x][y]=0;
        adfs(x+1, y, target);
        adfs(x, y+1, target);
        adfs(x-1, y, target);
        adfs(x, y-1, target);
    }
}
