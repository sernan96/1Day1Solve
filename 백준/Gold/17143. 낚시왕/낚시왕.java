import java.io.*;
import java.util.*;

class Main{
	static int R, C;
	static int [][] map;
	static shark []sharks; 
	static class shark{
		public boolean alive;
		public int x, y, size, move, dir;
		public shark(int x, int y, int move, int dir, int size, boolean alive) {
			this.x = x;
			this.y = y;
			this.move = move;
			this.dir = dir;
			this.size = size;
			this.alive = alive;
		}
	}
    public static void main(String [] args)throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	R = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	map = new int[R+1][C+1];
    	int M = Integer.parseInt(st.nextToken());
    	sharks = new shark[M+1];
    	for(int i=1; i<=M; i++) {
    		StringTokenizer sharkInfo = new StringTokenizer(br.readLine());
    		int sX = Integer.parseInt(sharkInfo.nextToken());
    		int sY = Integer.parseInt(sharkInfo.nextToken());
    		int move = Integer.parseInt(sharkInfo.nextToken());
    		int dir = Integer.parseInt(sharkInfo.nextToken());
    		int size = Integer.parseInt(sharkInfo.nextToken());
    		sharks[i] = new shark(sX, sY, move, dir, size, true);
    		map[sX][sY] = i;
    	}
    	int result = 0;
    	for(int king=1; king<=C; king++) {
    		//낚시왕의 낚시 시작
    		for(int i=1; i<=R; i++) {
    			if(map[i][king]!=0) {//물고기 잡는 경우
    				sharks[map[i][king]].alive = false;
    				result += sharks[map[i][king]].size;
    				//System.out.println(king+", "+i+" : "+sharks[map[i][king]].size+" 포획 ");
    				break;
    			}
    		}
    		// 상어 이동
    		map = new int[R+1][C+1];
    		for(int i=1; i<=M; i++) {
    			if(sharks[i].alive) {
    				DFS(i, sharks[i].move, sharks[i].x, sharks[i].y);
    			}
    		}
    		/*System.out.println(king+"위치");
        	for(int i=1; i<=R; i++){
        		for(int j=1; j<=C; j++) {
        			System.out.print(map[i][j]+" ");
        		}
    			System.out.print("\n");
        	}*/
    	}
    	System.out.print(result);
    }
    static void DFS(int sharkNum, int remainMove, int x, int y) {
    	int dir = sharks[sharkNum].dir;
    	if(dir==1) {//위
    		int gap = x-1;
    		if(gap<remainMove) {
    			sharks[sharkNum].dir = 2;
    			DFS(sharkNum, remainMove-gap, 1, y);
				//System.out.println(sharkNum+" 턴 "+1+", "+sharks[sharkNum].y+" dir:"+dir);
    		}
    		else {
    			sharks[sharkNum].x = x-remainMove;
    			int existShark = map[sharks[sharkNum].x][sharks[sharkNum].y];
				//System.out.println(sharkNum+" 이동끝"+sharks[sharkNum].x+", "+sharks[sharkNum].y);
    			if(existShark!=0) {
    				if(sharks[existShark].size<sharks[sharkNum].size) {
    					sharks[existShark].alive = false;// 기존 있던 상어 잡아먹기
    					map[sharks[sharkNum].x][sharks[sharkNum].y] = sharkNum;
    				}else {
    					sharks[sharkNum].alive = false;
    				}
    			}
    			else {
    				map[sharks[sharkNum].x][sharks[sharkNum].y] = sharkNum;
    			}
    		}
    	}
    	else if(dir==2) {//아래
    		int gap = R-x;
    		if(gap<remainMove) {
    			sharks[sharkNum].dir = 1;
    			DFS(sharkNum, remainMove-gap, R, y);
				//System.out.println(sharkNum+" 턴 "+R+", "+sharks[sharkNum].y+" dir:"+dir);
    		}
    		else {
    			sharks[sharkNum].x = x+remainMove;
    			int existShark = map[sharks[sharkNum].x][sharks[sharkNum].y];
				//System.out.println(sharkNum+" 이동끝"+sharks[sharkNum].x+", "+sharks[sharkNum].y);
    			if(existShark!=0) {
    				if(sharks[existShark].size<sharks[sharkNum].size) {
    					sharks[existShark].alive = false;// 기존 있던 상어 잡아먹기
    					map[sharks[sharkNum].x][sharks[sharkNum].y] = sharkNum;
    				}else {
    					sharks[sharkNum].alive = false;
    				}
    			}
    			else {
    				map[sharks[sharkNum].x][sharks[sharkNum].y] = sharkNum;
    			}
    		}
    	}
    	else if(dir==3) {//오른쪽
    		int gap = C-y;
    		if(gap<remainMove) {
    			sharks[sharkNum].dir = 4;
    			DFS(sharkNum, remainMove-gap, x, C);
				//System.out.println(sharkNum+" 턴 "+sharks[sharkNum].x+", "+C+" dir:"+dir);
    		}
    		else {
    			sharks[sharkNum].y = y+remainMove;
    			int existShark = map[sharks[sharkNum].x][sharks[sharkNum].y];
				//System.out.println(sharkNum+" 이동끝"+sharks[sharkNum].x+", "+sharks[sharkNum].y);
    			if(existShark!=0) {
    				if(sharks[existShark].size<sharks[sharkNum].size) {
    					sharks[existShark].alive = false;// 기존 있던 상어 잡아먹기
    					map[sharks[sharkNum].x][sharks[sharkNum].y] = sharkNum;
    				}else {
    					sharks[sharkNum].alive = false;
    				}
    			}
    			else {
    				map[sharks[sharkNum].x][sharks[sharkNum].y] = sharkNum;
    			}
    		}
    	}
    	else if(dir==4) {//왼쪽
    		int gap = y-1;
    		if(gap<remainMove) {
    			sharks[sharkNum].dir = 3;
    			DFS(sharkNum, remainMove-gap, x, 1);
				//System.out.println(sharkNum+" 턴 "+sharks[sharkNum].x+", "+1+" dir:"+dir);
    		}
    		else {
    			sharks[sharkNum].y = y-remainMove;
    			int existShark = map[sharks[sharkNum].x][sharks[sharkNum].y];
				//System.out.println(sharkNum+" 이동끝"+sharks[sharkNum].x+", "+sharks[sharkNum].y);
    			if(existShark!=0) {
    				if(sharks[existShark].size<sharks[sharkNum].size) {
    					sharks[existShark].alive = false;// 기존 있던 상어 잡아먹기
    					map[sharks[sharkNum].x][sharks[sharkNum].y] = sharkNum;
    				}else {
    					sharks[sharkNum].alive = false;
    				}
    			}
    			else {
    				map[sharks[sharkNum].x][sharks[sharkNum].y] = sharkNum;
    			}
    		}
    	}
    }
}
// 전체 반복은 run으로 돎
// 낚시왕의 포획 다음 상어들이 움직임
// 상어는 최대 10000마리인데 각 최대속력이 1000이다
// 그럼 진짜로 상어를 전부 시뮬레이션한다면 10억회다 최대
// 그렇다면 모듈러연산을 통해 상어의 움직이고 나서의 위치를 잘 파악해야겠다.
// 상어를 어떻게 저장 + 관리해줄지가 고민
// HashMap에서 key는 좌표위치, value는 상어번호로 저장
// 상어번호는 상어 class배열의 index임
// 상어가 이동할때마다 새로운 map에 넣어줌 상어 인덱스 입력
// 만약에 있으면 크기가 큰 것으로 남아있게함
// 작은 것은 잡아먹히니 상어 사망처리
// 문제는 이동
/*
상어의 이동
DFS로 생각했음. 남은 s가 방향에 최대 길이 (N,M)보다 크다면
해당 방향의 끝으로 이동
그리고 방향은 전환해주고 s에서 지금 이동한 만큼 빼줌
만약 s가 방향전환없이 이동해도 될 만큼 남았다면 더해주고 위치 반영해주고 종료
아니라면 그다음 DFS 호출 
 */