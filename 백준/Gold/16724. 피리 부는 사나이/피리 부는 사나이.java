import java.io.*;
import java.util.*;
public class Main {
    static int [][] map;
    static int [][]visited;
    static int N,M, cnt=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map= new int[N][M];
        visited = new int[N][M];
        for(int i =0; i<N; i++){
            String str = br.readLine();
            for(int j=0;j<M; j++){
                switch (str.charAt(j)){ // 하 0 좌 1 상 2 우 3
                    case 'D':
                        map[i][j]=0;
                        break;
                    case 'L':
                        map[i][j]=1;
                        break;
                    case 'U':
                        map[i][j]=2;
                        break;
                    case 'R':
                        map[i][j]=3;
                        break;
                }
            }
        }
        int time = 1;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j]==0){
                    DFS(i, j, time++);
                }
            }
        }
        System.out.print(cnt);

//        for(int i=0; i<N; i++){
//            for(int j=0; j<M; j++){
//                System.out.print(visited[i][j]+" ");
//            }
//            System.out.println();
//        }
        //DFS로 방문처리를 하면서 가다가 더이상 갈곳이 없을때 그곳을 safe존으로 하기
    }
    static int [] dx={1, 0, -1, 0}; //하좌상우
    static int [] dy={0, -1, 0, 1};
    static void DFS(int x, int y, int time){
        if(map[x][y]==-1 || (visited[x][y]!=0 && visited[x][y]<time)){
            return;
        }
        visited[x][y] = time;
        int dir = map[x][y];
        int mx = x+ dx[dir];
        int my = y+ dy[dir];
        if(mx<0|| my<0|| mx>=N || my >=M){ //좌표가 아예 밖일 경우
            cnt++;
            map[x][y]=-1;
            return;
        }
        else if(map[mx][my]==-1){
            return;
        } else if (visited[mx][my]==time) {// 같은 기수의 탐색에서 방문한 것이 아니라면 return만 하면 된다만 그게 아니라 같은 기수라면 safe존 처리
            cnt++;
            map[x][y]=-1;
            return;
        }
        DFS(mx, my, time);
    }
}
