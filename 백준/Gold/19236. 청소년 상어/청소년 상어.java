import java.util.*;
//1:34:26 화장실+53분
import java.io.*;
public class Main {
	static int [] dx = {0, -1, -1, 0,  1,  1, 1, 0, -1};//북, 서북, 서, 남서, 남, 동남, 동, 북동 (인덱스0은 그냥 빈값) 
	static int [] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static char [] arrow = {'0','↑', '↖', '←', '↙', '↓', '↘', '→', '↗'};
	static int result;
	public static void main(String [] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int [][] fish = new int[17][4];// 0에는 생존여부, 1에는 dir, 2,3에는 위치
		int [][] map = new int [4][4];
		result = 0;
		for(int i=0; i<4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				int fishNum = Integer.parseInt(st.nextToken());
				int fishDir = Integer.parseInt(st.nextToken());
				map[i][j] = fishNum;
				fish[fishNum][0] = 1;//살아있음
				fish[fishNum][1] = fishDir;
				fish[fishNum][2] = i;
				fish[fishNum][3] = j;
			}
		}
		shark(0,0, map, fish, 0);
		System.out.print (result);
	}
	static void shark(int x, int y, int[][] map, int[][] fish, int ate) {
		//분기생성전에 미리 보낼수있는지 없는지 확인하고 보냈음
		// 1. 상어입장
		int eatenFish = map[x][y];
		int sharkDir = fish[eatenFish][1];//방향 흡수
		map[x][y] = -1; //물고기 번호 지우고 상어 표시
		fish[eatenFish][0] = 0;//죽음 처리
		// 2. 물고기 이동
		moveFish(map, fish);
		// 3. 상어가 이동할 위치 가능한곳 전부 분기처리
		int mx = x+dx[sharkDir];
		int my = y+dy[sharkDir];
		map[x][y] = 0;
		while(canGo(mx, my)) {
			if(map[mx][my]==0) {

				mx+=dx[sharkDir];
				my+=dy[sharkDir];
				continue;
			}
			shark(mx, my, deepCopy(map), deepCopy(fish), ate+eatenFish);
			mx+=dx[sharkDir];
			my+=dy[sharkDir];
		}
		result = Math.max(result, ate+eatenFish);

	}
	static int[][] deepCopy(int [][] arr){
		int [][]temp = new int[arr.length][arr[0].length];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				temp[i][j]= arr[i][j];
			}
		}
		return temp;
	}
	static void moveFish(int [][] map, int [][] fish) {
		for(int i=1; i<=16; i++) {
			if(fish[i][0]==0) {
				continue;
			}
			
			int dir = fish[i][1];
			int x = fish[i][2];
			int y = fish[i][3];
			for(int j = 0; j<8; j++) {//방향 45도씩
				int mDir = (dir+j)%9==0?1:(dir+j)%9;
				int mx = x+dx[mDir];
				int my = y+dy[mDir];
				if(canGo(mx, my) && map[mx][my]!=-1) {
					// 빈칸이던 물고기가 있던 스왑해주면 된다.
					fish[i][1]=mDir;
					if(map[mx][my]==0) {//그냥 이동만 하면됨
						map[mx][my] = i;
						map[x][y] = 0;
						fish[i][2] = mx;
						fish[i][3] = my;
					}
					else {
						int changeFish = map[mx][my];//바꿀 물고기 번호
						int [] tempInfo = {fish[i][2], fish[i][3]};//원래 물고기 위치 임시저장
						fish[i][2]= fish[changeFish][2];
						fish[i][3]= fish[changeFish][3];
						fish[changeFish][2] = tempInfo[0];
						fish[changeFish][3] = tempInfo[1];
						map[mx][my] = i;
						map[x][y] = changeFish;
					}
					break;
				}
			}
		}
	}
	static boolean canGo(int x, int y) {
		return x>=0&&y>=0&&x<4&&y<4;
	}
}