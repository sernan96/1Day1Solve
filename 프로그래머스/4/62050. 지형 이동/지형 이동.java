import java.util.*;
class Solution {
    static int N, M;
    static int [] parents;
    static class connect{
        String groupInfo;
        int weight;
        public connect(String groupInfo, int weight){
            this.weight = weight;
            this.groupInfo = groupInfo;
        }
    }
    public int solution(int[][] land, int height) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        //군집 나누기
        int [][] group = new int[N][M];
        boolean [][] visited = new boolean[N][M];
        int groupNum =0;
        int [] dx = {1, 0, -1, 0};
        int [] dy = {0, -1, 0, 1};
        ArrayDeque<int []> outsiders = new ArrayDeque<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j]){
                    continue;
                }
                groupNum++;
                ArrayDeque<int[]> q = new ArrayDeque<>();
                q.add(new int []{i, j});
                visited[i][j] = true;
                while(!q.isEmpty()){
                    int [] cur = q.poll();
                    int x = cur[0];
                    int y = cur[1];
                    group[x][y] = groupNum;
                    for(int dir=0; dir<4; dir++){
                        int mx = x + dx[dir];
                        int my = y + dy[dir];
                        if(canGo(mx, my)&&!visited[mx][my]&&Math.abs(land[mx][my]-land[x][y])<=height){
                            q.add(new int[]{mx, my});
                            visited[mx][my] = true;
                        }
                        if(canGo(mx, my)&&!visited[mx][my]&&Math.abs(land[mx][my]-land[x][y])>height){
                            outsiders.add(new int []{x, y, mx, my, Math.abs(land[mx][my]-land[x][y])});
                        }       
                    }
                }   
            }
        }
        PriorityQueue<connect> pq = new PriorityQueue<>(Comparator.comparingInt((connect o1)-> o1.weight));
        for(int [] outsider:outsiders){
            int groupA = group[outsider[0]][outsider[1]];
            int groupB = group[outsider[2]][outsider[3]];
            if(groupA>groupB){
                pq.add(new connect(groupB+"_"+groupA, outsider[4]));
            }
            else if(groupA<groupB){
                pq.add(new connect(groupA+"_"+groupB, outsider[4]));
            }
        }
        int count =0;
        parents = new int [groupNum+1];
        for(int i=1; i<groupNum+1; i++){
            parents[i]  = i;
        }
        while(!pq.isEmpty()){
            connect cur = pq.poll();
            StringTokenizer st = new StringTokenizer(cur.groupInfo, "_");
            int groupA = Integer.parseInt(st.nextToken());
            int groupB = Integer.parseInt(st.nextToken());
            if(find(groupA)==find(groupB)){
                continue;
            }
            union(groupA, groupB);
            answer+=cur.weight;
            //System.out.println(groupA+", "+groupB+" : "+cur.weight);
        }
        return answer;
    }
    static int find(int x){
        if(x==parents[x]){
            return x;
        }
        return parents[x] = find(parents[x]);
    }
    static void union(int x, int y){
        int xP = find(x);
        int yP = find(y);
        if(xP<yP){
            parents[yP] = xP;
        }
        else if(xP>yP){
            parents[xP] = yP;
        }
    }
    static boolean canGo(int x, int y){
        return x>=0&&y>=0&&x<N&&y<M;
    }
}
/*
각 3이하로 이동할 수 있는 기준으로 군집을 나눔
군집끼리 연결하는 것의 최소 비용을 구하면 됨
연결하는 것을 어떻게 할지가 문제
경계부분의 차이를 우선순위큐로 하여 그 pq에 넣음
겹친 영역을 string으로 하여 어디와 어디가 연결되는 경계인지도 적음
그래서 일단 순서는 차이값이 작은 순서대로 하나씩 뽑아서 연결
만약 이미 연결된 경우는 continue
연결되었는지 안되었는지는 boolean 배열로 하여 체크
1. BFS돌면서 군집 나누기 
2. 경계부분일때는 우선순위큐에 정보 저장(근데 경계부분일때 상대 군집 번호가 지정되지 않은 경우가 있을 수 있다.
그렇다면 일단 큐에 경계부분을 저장해두고 마지막에 전부 돌면서 정보를 저장해준다.)
3. 저장된 우선순위큐를 뽑아내면서 전부 연결할 수 있도록 최소한의 비용으로 연결을 해준다.
*/