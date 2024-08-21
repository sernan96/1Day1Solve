import java.io.*;
import java.util.*;

public class Main{
    static class pair{
        int x, y;
        public pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static ArrayList<pair> zero = new ArrayList<>();
    static ArrayList<pair> two = new ArrayList<>();
    static PriorityQueue<Integer> result = new PriorityQueue<>(Comparator.reverseOrder());
    static  int [][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NM = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(NM.nextToken());
        int M = Integer.parseInt(NM.nextToken());
        map = new int [N][M];
        for(int i=0; i<N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int xy = Integer.parseInt(input.nextToken());
                if(xy==0){
                    zero.add(new pair(i, j));
                }
                else if(xy==2){
                    two.add(new pair(i, j));
                }
                map[i][j] = xy;
            }
        }
        makeWall();

        System.out.print(result.poll());
    }
    static void makeWall(){
        int [][] copyMap = map;
        for(int i=0; i<zero.size(); i++){
            for(int j=i+1; j<zero.size(); j++){
                for(int k=j+1; k<zero.size(); k++){
                    copyMap[zero.get(i).x][zero.get(i).y] = 1;
                    copyMap[zero.get(j).x][zero.get(j).y] = 1;
                    copyMap[zero.get(k).x][zero.get(k).y] = 1;
                    virus(deepCopy(copyMap));
                    copyMap[zero.get(i).x][zero.get(i).y] = 0;
                    copyMap[zero.get(j).x][zero.get(j).y] = 0;
                    copyMap[zero.get(k).x][zero.get(k).y] = 0;
                }
            }
        }
    }
    static int [][] deepCopy(int [][] original){
        int [][] deep = new int[original.length][original[0].length];
        for(int i=0; i< deep.length; i++){
            for(int j=0; j< deep[0].length; j++){
                deep[i][j] =original[i][j];
            }
        }
        return deep;
    }
    static int [] mx = {0, 1, 0, -1};
    static int [] my = {1, 0, -1, 0};
    static void virus(int [][] copyMap){
        for(pair p: two){
            Queue<pair> go = new LinkedList<>();
            go.add(p);
            while(!go.isEmpty()){
                pair current = go.poll();
                for(int i=0; i<4; i++){
                    int dx = current.x + mx[i];
                    int dy = current.y + my[i];
                    if(dx >= 0 && dx < copyMap.length && dy >= 0 && dy < copyMap[0].length && copyMap[dx][dy] == 0){
                        go.add(new pair(dx, dy));
                        copyMap[dx][dy] = 2;
                    }
                }
            }
        }
        int cnt = 0;
        for(int i=0; i<copyMap.length; i++){
            for(int j=0; j< copyMap[0].length; j++){
                if(copyMap[i][j]==0){
                    cnt++;
                }
            }
        }
        result.add(cnt);
    }
}