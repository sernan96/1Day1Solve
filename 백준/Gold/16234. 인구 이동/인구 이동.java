import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0}; // x축 이동: 상, 하, 좌, 우
    static int[] dy = {0, 0, 1, -1}; // y축 이동: 상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;
        while (true) {
            visited = new boolean[N][N]; // 방문 여부 초기화
            boolean isMoved = false; // 인구 이동 여부 확인

            // 모든 좌표를 확인하여 연합 형성
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j)) {
                            isMoved = true; // 인구 이동 발생
                        }
                    }
                }
            }

            if (!isMoved) { // 인구 이동이 더 이상 없으면 종료
                break;
            }
            days++; // 하루 경과
        }
        System.out.println(days);
    }

    // BFS로 연합 형성 및 인구 이동 처리
    static boolean bfs(int x, int y) {
        Queue<pair> queue = new LinkedList<>();
        ArrayList<pair> union = new ArrayList<>();
        queue.add(new pair(x, y));
        union.add(new pair(x, y));
        visited[x][y] = true;

        int totalPopulation = map[x][y];
        int unionCount = 1;

        while (!queue.isEmpty()) {
            pair current = queue.poll();

            for (int i = 0; i < 4; i++) { // 상하좌우 탐색
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    int populationDiff = Math.abs(map[current.x][current.y] - map[nx][ny]);
                    if (populationDiff >= L && populationDiff <= R) {
                        queue.add(new pair(nx, ny));
                        union.add(new pair(nx, ny));
                        visited[nx][ny] = true;
                        totalPopulation += map[nx][ny];
                        unionCount++;
                    }
                }
            }
        }

        // 연합을 형성한 국가가 2개 이상이라면 인구 이동 발생
        if (unionCount > 1) {
            int newPopulation = totalPopulation / unionCount;
            for (pair p : union) {
                map[p.x][p.y] = newPopulation; // 새로운 인구 배치
            }
            return true; // 인구 이동이 발생함
        }
        return false; // 인구 이동이 발생하지 않음
    }

    // 좌표 저장을 위한 클래스
    static class pair {
        int x, y;

        public pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
