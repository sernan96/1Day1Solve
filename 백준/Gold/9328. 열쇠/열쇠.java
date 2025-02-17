import java.io.*;
import java.util.*;

class Main {
    static int H,W;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int [] dx = {1, 0, -1, 0};
        int [] dy = {0, 1, 0, -1};
        StringBuilder sb = new StringBuilder ();
        while(T--!=0){
            StringTokenizer hw = new StringTokenizer(br.readLine());
            H = Integer.parseInt(hw.nextToken());
            W = Integer.parseInt(hw.nextToken());
            char [][] map = new char[H+2][W+2];

            for(int i=0; i<H+2; i++){
                Arrays.fill(map[i], '.');
            }
            for(int i=1; i<H+1; i++){
                String input = br.readLine();
                for(int j=1; j<W+1; j++){
                    map[i][j] = input.charAt(j-1);
                }
            }
            String keyList = br.readLine();
            HashSet<Integer> keys = new HashSet<>();
            for(char x: keyList.toCharArray()){
                keys.add(x-'a');
            }
            boolean [][] visited = new boolean[H+2][W+2];
            ArrayDeque <int[]> q = new ArrayDeque<>();
            visited[0][0] = true;
            int dollar = 0;
            q.add(new int[]{0,0});
            while(!q.isEmpty()){
                int [] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                if(map[x][y]>='A'&&map[x][y]<='Z'&&!keys.contains(map[x][y]-'A')){//열쇠없는 문에 도달
                    continue;
                }
                else if(map[x][y]>='a'&&map[x][y]<='z'&&!keys.contains(map[x][y]-'a')){//새로운 열쇠
                    keys.add(map[x][y]-'a');
                    map[x][y]='.';
                    visited = new boolean [H+2][W+2];
                    q.add(new int[]{0,0});
                    visited[0][0]= true;
                    continue;
                }
                else if(map[x][y]=='$'){
                    dollar++;
                    map[x][y]='.';
                }
                for(int i=0; i<4; i++){
                    int mx = x+ dx[i];
                    int my = y+ dy[i];
                    if(canGo(mx, my)&&!visited[mx][my]&& map[x][y]!='*'){
                        visited[mx][my] = true;
                        q.add(new int []{mx, my});
                    }
                }
            }
            sb.append(dollar).append("\n");
        }
        System.out.print(sb);
    }
    static boolean canGo(int x, int y){
        return x>=0&&y>=0&&x<H+2&&y<W+2;
    }
}