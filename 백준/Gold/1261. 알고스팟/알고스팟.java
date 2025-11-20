import java.io.*;
import java.util.*;
public class Main {
	static int N, M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int [][] map = new int[M][N];
        int [][] visited = new int[M][N];
        for(int i=0; i<M; i++) {
        	String input = br.readLine();
        	for(int j=0; j<N; j++) {
        		map[i][j] = input.charAt(j)-'0';
        		visited[i][j] = Integer.MAX_VALUE;
        	}
        }
        
        int [] dx = {1, 0, -1, 0};
        int [] dy = {0, 1, 0, -1};
        PriorityQueue<int[]> pq =new PriorityQueue<>(Comparator.comparingInt(o1->o1[2]));
        visited[0][0] = 0;
        pq.add(new int [] {0, 0, 0});
        while(!pq.isEmpty()) {
        	int [] cur = pq.poll();
        	int x = cur[0];
        	int y = cur[1];
        	int cost = cur[2];
        	for(int dir = 0; dir<4; dir++) {
        		int mx = x+dx[dir];
        		int my = y+dy[dir];
        		if(!canGo(mx, my)) {
        			continue;
        		}
        		int mCost = cost+map[mx][my];
        		if(visited[mx][my]>mCost) {
        			pq.add(new int[] {mx, my, mCost});
        			visited[mx][my] = mCost;
        		}
        	}
        }
        System.out.print(visited[M-1][N-1]);
    }
    static boolean canGo(int x, int y) {
    	return x>=0 && y>=0 && x<M && y<N; 
    }
}