import java.io.*;
import java.util.*;

class Main {
    static  char [][]board;
    static int max=-1, R, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R+2][C+2];
        for(int i =1; i<R+1; i++){
            String input = br.readLine();
            for(int j=1; j<C+1; j++){
                board[i][j]=input.charAt(j-1);
            }
        }
        boolean [] visited = new boolean['Z'+1];
        move(1, 1, visited, 0);
        System.out.print(max);
    }
    static void move (int x, int y, boolean []visited, int cnt){
        if(visited[board[x][y]]||(x<1||y<1||x>R||y>C)){

            max = Math.max(max, cnt);
            return;
        }
        visited[board[x][y]]=true;
        move(x-1, y, Arrays.copyOf(visited, visited.length), cnt+1);
        move(x+1, y, Arrays.copyOf(visited, visited.length), cnt+1);
        move(x, y-1, Arrays.copyOf(visited, visited.length), cnt+1);
        move(x, y+1, Arrays.copyOf(visited, visited.length), cnt+1);
    }

}
