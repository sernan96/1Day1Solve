import java.io.*;
import java.util.*;
public class Solution {
	static int N;
	static boolean [][] visited;
	static int [][] map;
	static boolean [] checked;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t= 1; t<=T; t++) {
        	N = Integer.parseInt(br.readLine());
        	map = new int [N][N];
        	visited = new boolean [N][N];
        	checked = new boolean[N*N+1];
        	sb.append("#"+t+"\n");
        	visited[0][0] = true;
        	checked[1] = true;
        	makeSnail(0,0, 1, 0);
        	for(int i=0; i<N; i++) {
        		for(int j=0; j<N; j++) {
        			sb.append(map[i][j]+" ");
        		}
        		sb.append("\n");
        	}
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
    static int [] dx = {0, 1, 0, -1};
    static int [] dy = {1, 0, -1, 0};
    static void makeSnail(int x,  int y, int num, int dir) {
    	map[x][y] = num;
    	int mx = x+dx[dir];
    	int my = y+dy[dir];
    	
    	if(canGo(mx, my) && num+1<=N*N &&!checked[num+1]&& !visited[mx][my] ) {
	    	visited[mx][my] = true;
	    	checked[num+1]= true;
			makeSnail(mx, my, num+1, dir);
			return;
		}
    	//방향 전환
    	int changeDir = (dir+1)%4;
    	mx = x+dx[changeDir];
    	my = y+dy[changeDir];
    	if(canGo(mx, my) && num+1<=N*N &&!checked[num+1]&& !visited[mx][my] ) {
	    	visited[mx][my] = true;
	    	checked[num+1]= true;
			makeSnail(mx, my, num+1, changeDir);
		}
    }
    static boolean canGo(int x, int y) {
    	return x>=0&& y>=0 && x<N && y<N;
    }
}
/*
DFS로 우 하 좌 상 순서로 num을 작성한다.
만약 이동할 방향이 방문한 곳이라면 return;
 
*/