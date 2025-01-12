import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static ArrayList<Node> teachers;
    static int [][] map;
    static boolean find;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        teachers = new ArrayList<>();
        map = new int[N][N];
        find = true;
        for(int i=0; i<N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                String str = input.nextToken();
                if(str.charAt(0)=='T'){
                    teachers.add(new Node(i,j));
                    map[i][j] = 1;
                } else if (str.charAt(0)=='S') {
                    map[i][j] = 2;
                }
            }
        }
        makeWall(0);
        if(find){
            System.out.print("NO");
        }
    }
    static void makeWall(int count){
        if(count==3){
            //여기서 테스트해보고 결과 출력
            if(find&&!check()){
                find=false;
                System.out.print("YES");
            }
            return;
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j]==0){
                    map[i][j]=-1;
                    makeWall(count+1);
                    map[i][j]=0;
                }
            }
        }
    }
    static boolean check(){
        int [] dx = {1, 0, -1, 0};
        int [] dy = {0, 1, 0, -1};
        for(Node x: teachers){
            for(int i=0; i<4; i++){
                int mx = x.x+dx[i];
                int my = x.y+dy[i];
                while (!cantGo(mx, my)){//동서남북 순서대로 끝까지
                    if(map[mx][my]==2){//학생 발견
                        return true;
                    }
                    mx+=dx[i];
                    my+=dy[i];
                }
            }
        }
        return false;//안걸렸음
    }
    static boolean cantGo(int start_x, int start_y){
        return start_x<0||start_y<0||start_x>=N||start_y>=N||map[start_x][start_y]==-1;
    }
}
