import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        boolean [][] apple = new boolean[N][N];
        boolean [][] bodyCheck = new boolean[N][N];
        StringTokenizer st;
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            apple[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
        }
        HashMap<Integer, Boolean> changeDir = new HashMap<>();
        int L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++){
            st = new StringTokenizer(br.readLine());
            changeDir.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0) == 'L');
        }
        int [] dx = {-1, 0, 1, 0};
        int [] dy = {0, 1, 0, -1};
        int [] head = {0,0};
        int dir = 1;
        ArrayDeque<int []> bodySave = new ArrayDeque<>();
        for(int time = 1; ; time++){
            int mx = dx[dir]+head[0];
            int my = dy[dir]+head[1];
            //게임 끝
            if(!canGo(mx, my)||bodyCheck[mx][my]){
                System.out.print(time);
                return;
            }
            
            //일단 늘여주기
            bodySave.add(new int[]{head[0], head[1]});
            bodyCheck[head[0]][head[1]] = true;
            head[0] = mx;
            head[1] = my;
            //사과 존재 유무
            if(!apple[mx][my]) {
                //사과 없었으면 꼬리 비워주기
                int [] eraseBody = bodySave.pollFirst();
                bodyCheck[eraseBody[0]][eraseBody[1]] = false;
            }
            else{
                apple[mx][my] = false;
            }
            if(changeDir.containsKey(time)){
                dir = turn(dir, changeDir.get(time));
            }
        }
    }
    static int turn(int dir, boolean isLeft){
        if(isLeft){
            return dir==0? 3:dir-1;
        }
        else {
            return (dir+1)%4;
        }
    }
    static boolean canGo(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }
}
/*
for 로 매초 게임을 진행해준다.
뱀의 몸은 큐로 저장해주고
머리의 위치를 저장하는 int형 배열 int[]로 저장해둔다.
방향 변환해주는 함수 존재해야함

또한 매초마다 진행할때 이것을 반복한다.
뱀 머리 이동하고 게임이 끝났는지 파악
-> 사과가 있다면 머리가 있던 곳 몸 큐에 추가
-> 사과가 없다면 큐에서 몸하나빼기 q.pollFirst()
그러고 마지막에 (HashMap으로 해당 초에 방향전환 있는지 파악)
*/
