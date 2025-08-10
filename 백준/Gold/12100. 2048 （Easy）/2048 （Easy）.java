import java.io.*;
import java.util.*;
public class Main {
    static int result, N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int [][] map = new int[N][N];
        result = 0;
        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<4; i++){//각 방향별로 시작
            DFS(push(map, i), 0);
        }
        System.out.print(result);
    }
    static void DFS(int[][] map, int depth){
        if(depth==4){
            check(map);
            return;
        }
        for(int i=0; i<4; i++){//각 방향별로 시작
            DFS(push(map, i), depth+1);
        }
    }
    // 0:북 1:동 2:남 3:서
    static int[][] push (int [][] map, int dir){
        int [][] temp = new int[N][N];
        //동 또는 서 가로방향
        if(dir%2==1){
            for(int i=0; i<N; i++){
                ArrayDeque<Integer> q = new ArrayDeque<>();
                if(dir==3){// 서일 때는 좌측에서부터
                    for(int j=0; j<N; j++){
                        int block = map[i][j];
                        if(block!=0){
                            q.add(block);
                        }
                    }
                }else {// 동일 때는 우측에서부터 넣어주기
                    for(int j=N-1; j>=0; j--){
                        int block = map[i][j];
                        if(block!=0){
                            q.add(block);
                        }
                    }
                }
                int index = dir==3?0:N-1;
                while (!q.isEmpty()){
                    int cur = q.poll();
                    if(!q.isEmpty()&&q.peek()==cur){//지금과 이다음 값이 같다면 합치기
                        cur+=q.poll();
                    }
                    if(dir==3){
                        temp[i][index++] = cur;
                    }
                    else{
                        temp[i][index--] = cur;
                    }
                }
            }
        }
        //북 또는 남 세로방향
        else{
            for(int j=0; j<N; j++){
                ArrayDeque<Integer> q = new ArrayDeque<>();
                if(dir==2){// 남일 때는 우측에서부터
                    for(int i=N-1; i>=0; i--){
                        int block = map[i][j];
                        if(block!=0){
                            q.add(block);
                        }
                    }
                }else {// 북일 때는 밑에서부터 넣어주기
                    for(int i=0; i<N; i++){
                        int block = map[i][j];
                        if(block!=0){
                            q.add(block);
                        }
                    }
                }
                int index = dir==0?0:N-1;
                while (!q.isEmpty()){
                    int cur = q.poll();
                    if(!q.isEmpty() && q.peek()==cur){//지금과 이다음 값이 같다면 합치기
                        cur+=q.poll();
                    }
                    if(dir==0){
                        temp[index++][j] = cur;
                    }
                    else{
                        temp[index--][j] = cur;
                    }
                }
            }
        }
        return temp;
    }
    static void check (int [][]map){
        for (int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                result = Math.max(result, map[i][j]);
            }
        }
    }
}
/*
일단 이동하려는 방향에서부터 인접한 같은수를 합치기 시작하고
최대 5번 움직였을때 최대 수를 가진 블록의 수를 출력
일단 생각할 동작
1. 블록 밀기
2. 블록 합치기
일단 해당 방향으로 민다. 큐를 이용해서 밀 방향으로 숫자를 0제외 전부 넣어준다.
그럼 만약 4 4 0 8 8이 있다면 순서대로 들어가게되고 한번에
큐에서 나오는 순서대로 마지막으로 나온 수와 다음에 나오는 수가 동일하다면 합쳐주고 그다음수는 버려준다.
이 행동을 백트레킹을 통해서 진행하는데 했던 행동을 계속 되돌리면서 진행할 수는 없기에
깊은 복사를 통해 map을 복제해주며 진행해주겠다.그렇게 5번째 이동을 끝냈을때 최대의 수를 출력해준다.
*/