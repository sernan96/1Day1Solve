import java.util.*;
class Solution {
    static boolean [][] visited;
    static String [] place;
    static boolean flag;
    static int room, N, M;
    public int[] solution(String[][] places) {
        room = places.length;
        N = places[0].length;
        M = places[0][0].length();
        int[] answer = new int [room];
        Arrays.fill(answer, 1);
        for(int i=0; i<room; i++){
            place = places[i]; 
            flag = false;
            //map 배열 생성
            for(int j=0; j<N; j++){
                if(flag){
                    break;
                }
                for(int k=0; k<M; k++){
                    if(flag){
                        break;
                    }
                    if(places[i][j].charAt(k)=='P'){
                        visited = new boolean[N][M];
                        visited[j][k] = true;
                        DFS(j, k, 3, i);
                    }
                }
            }
            if(flag){
                answer[i] =0;
            }
        }
        return answer;//
    }
    static int [] dx = {1, 0, -1, 0};
    static int [] dy = {0, 1, 0, -1};
    static void DFS(int x, int y, int depth, int roomNum){
        if(flag||depth<=0||x<0||y<0||x>=N||y>=M){
            return;
        }
        if(depth!=3&&place[x].charAt(y)=='P'){
            flag = true;
            return;
        }
        for(int i=0; i<4; i++){
            int mx =x+dx[i];
            int my =y+dy[i];
            if(mx<0||my<0||mx>=N||my>=M||place[mx].charAt(my)=='X'){
                continue;
            }
            if(!visited[mx][my]){
                visited[mx][my] = true;
                DFS(mx, my, depth-1,roomNum);
                visited[mx][my] = false;
            }
        }
    }
}
// 총 대기실을 순회할 반복문
// 처음에 visited 방문처리 배열 만들어줌
//  2중 for문으로 하나씩 돌면서 P를 찾으면 백트레킹 탐색
// 거기서 부터 2칸 이내로 갈수 있는 모든 칸을 탐색하며 P를 찾으면 
// 해당 대기실 종료 후 해당 배열 0으로 (기본은 1로 되어있음)