import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static int [][] map, groups;
    static boolean [][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NM = new StringTokenizer(br.readLine());
        N = Integer.parseInt(NM.nextToken());
        M = Integer.parseInt(NM.nextToken());
        map = new int[M][N];
        groups = new int[M][N];
        visited = new boolean[M][N];
        HashMap<Integer, Integer> areaMap = new HashMap<>();
        HashMap<Integer, Set<Integer>> nearMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int group = 1;
        // 1.2. 각 구분된 영역 구해서 최대 넓이와 개수 구하기
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o1->(-1)*o1));
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(!visited[i][j]){
                    int area = BFS(i, j, group);
                    pq.add(area);
                    areaMap.put(group, area);
                }
                group++;
            }
        }
        sb.append(pq.size()+"\n"+pq.poll()+"\n");
        pq.clear();
        //3.벽을 하나씩 제거해보고 가장 커지는 값을 구해서 마지막에 한꺼번에 출력 -> 너무 비효율적
        // 아니면 그래프로 만들어서 이어져있는 영역을 합치는 방법이 가장 효과적으로 보임
        // 그렇다면 해줄것 -> 3-1. 해당 영역을 표시한 map하나 추가
        // 3-2. 영역 번호가 적힌 맵에서 인접한 영역을 순회하며 연결
        // 3-3. 영역 별로 인접한 영역 너비 합 pq에 넣기(초기화 필수)
        // 마지막에 뽑아서 StringBuilder에 추가
        boolean [] checked = new boolean[group];
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                //상하좌우 인접한 다른 그룹 검 사
                int groupA =groups[i][j];
                for(int dir = 0; dir<4; dir++){
                    int xB = i+dx[dir];
                    int yB = j+dy[dir];
                    if(!canGo(xB, yB)){
                        continue;
                    }
                    int groupB = groups[xB][yB];
                    if(groupA==groupB ||(checked[groupA] && checked[groupB])) {
                        continue;
                    }
                    int sumArea = areaMap.get(groupA) + areaMap.get(groupB);
                    pq.add(sumArea);
                }
            }
        }
        sb.append(pq.poll());
        System.out.print(sb);
    }
    static int [] wallDir = {1, 2, 4, 8};//서 북 동 남
    static int [] dx = {0, -1, 0, 1};
    static int [] dy = {-1, 0, 1, 0};
    static int BFS(int i, int j, int group){
        int cnt = 0;
        groups[i][j] = group;
        ArrayDeque<int []> q = new ArrayDeque<>();
        q.add(new int []{i, j});
        visited[i][j] = true;
        while (!q.isEmpty()){
            int [] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            cnt++;
            for(int dir=0; dir<4; dir++){
                int mx = x+dx[dir];
                int my = y+dy[dir];
                if(canGo(mx, my)&&!visited[mx][my]&&(map[x][y] & wallDir[dir])==0){
                    visited[mx][my] = true;
                    groups[mx][my] = group;
                    q.add(new int []{mx, my});
                }
            }
        }
        return cnt;
    }
    static boolean canGo(int x, int y){
        return x>=0&&y>=0&&x<M&&y<N;
    }
}
/*
1.2. BFS로 각 방의 개수 파악 및 가장 넓은 방의 넓이 구하기 -> 우선순위큐로
3. 벽을 하나씩 제거해보고 가장 커지는 값을 구해서 마지막에 한꺼번에 출력
*/