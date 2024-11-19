import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<fish> fishs;
    static shark baby;
    static int[][] map;

    static class fish {
        public int x, y, size;
        public boolean live;

        public fish(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.live = true;
        }

        public void ate() {
            this.live = false;
        }
    }

    static class shark {
        public int x, y, size, time, record;
        public boolean done = false;

        public shark(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.time = 0;
            this.record = 0;
        }

        public void move(int dx, int dy) {
            this.x = dx;
            this.y = dy;
        }

        public void eat(ArrayList<fish> fishs) {
            Map<fish, Integer> distances = new HashMap<>();

            for (fish f : fishs) {
                if (f.live && f.size < this.size) {
                    int dist = BFS(f);
                    if (dist != Integer.MAX_VALUE) {
                        distances.put(f, dist);
                    }
                }
            }

            PriorityQueue<fish> candidate = new PriorityQueue<>((o1, o2) -> {
                int d1 = distances.get(o1);
                int d2 = distances.get(o2);
                if (d1 == d2) {
                    if (o1.x == o2.x) {
                        return o1.y - o2.y;
                    }
                    return o1.x - o2.x;
                }
                return d1 - d2;
            });

            candidate.addAll(distances.keySet());

            if (candidate.isEmpty()) {
                System.out.println(this.time);
                this.done = true;
                return;
            }

            fish ateFish = candidate.poll();
            ateFish.ate();
            map[ateFish.x][ateFish.y] = 0; // 맵 상태 갱신
            this.record++;

            if (this.record == this.size) {
                this.size++;
                this.record = 0;
            }

            this.time += distances.get(ateFish); // 시간 누적
            move(ateFish.x, ateFish.y); // 상어 이동
        }
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int BFS(fish target) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new int[]{baby.x, baby.y, 0});
        visited[baby.x][baby.y] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];

            if (x == target.x && y == target.y) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] > baby.size) continue;
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, dist + 1});
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        fishs = new ArrayList<>();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine().replaceAll(" ", "");
            for (int j = 0; j < N; j++) {
                int num = input.charAt(j) - '0';
                map[i][j] = num != 9 ? num : 0;
                if (num == 9) {
                    baby = new shark(i, j, 2);
                } else if (num != 0) {
                    fishs.add(new fish(i, j, num));
                }
            }
        }

        while (!baby.done) {
            baby.eat(fishs);
        }
    }
}