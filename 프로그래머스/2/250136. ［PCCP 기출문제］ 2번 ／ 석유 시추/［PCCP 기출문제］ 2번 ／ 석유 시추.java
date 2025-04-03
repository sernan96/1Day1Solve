import java.util.*;
class Solution {
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int [] hole;
    static int [][] map;
    static boolean [][] visited;
    static int N, M;
    public int solution(int[][] land) {
        map = land;
        N = land.length;
        M = land[0].length;
        hole = new int[M];
        visited = new boolean[N][M];
        for(int j=0; j<M; j++){        
            int oil = 0;
            for(int i=0; i<N; i++){
                if(!visited[i][j] && land[i][j]==1){
                    BFS(i, j);
                }
            }
        }
        Arrays.sort(hole);
        return hole[M-1];
    }
    static int [] dx = {0, 1, -1, 0};
    static int [] dy = {1, 0, 0, -1};
    static void BFS(int i, int j){
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(i, j));
        visited[i][j] = true;
        int oilCnt =0;
        HashSet<Integer> set = new HashSet<>();
        while(!q.isEmpty()){
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            set.add(y);// 걸쳐있는 열번호 저장
            oilCnt++;
            for(int dir=0; dir<4; dir++){
                int mx = x + dx[dir];
                int my = y + dy[dir];
                if(canGo(mx, my)&& !visited[mx][my]&& map[mx][my]==1){
                    visited[mx][my] = true;
                    q.add(new Node(mx, my));
                }
            }
            
        }
        for(int num: set){
            //System.out.println(num+"번에 석유 "+oilCnt);
            hole[num] +=oilCnt;
        }
    }
    static boolean canGo(int x, int y){
        return x>=0&&y>=0&&x<N&&y<M;
    }
}
// 일단 전부 BFS를 돈다고 했을때도 충분할 것 같긴함
// 최대 25000번 BFS를 돎 근데 BFS에서 한번에 최대 25000개의 블록을 방문할때
// 25000^2 = 625000000 10초내로 가능
// 일단 열마다 시추가 끝나고 최대값 비교
// 매 열마다 시추관을 팔때마다 방문처리 배열 초기화
// 만약 석유가 나오면 BFS 돌입
// 석유는 BFS를 돌때마다 +1씩
// 아쉽게도 시간초과
// 새로이 생각해낸 방법
// 석유덩어리를 돌면서 나오는 열 번호에 전부 저장해주고 visited는 초기화할 필요없게함
// 그리고 마지막에 열번호 돌면서 열마다 + 해줌