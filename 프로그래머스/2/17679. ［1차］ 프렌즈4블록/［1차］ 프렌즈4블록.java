import java.util.*;
class Solution {
    public int solution(int m, int n, String[] board) {
        int N = board.length;
        int M = board[0].length();
        char [][] map = new char[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = board[i].charAt(j);
            }
        }
        int count = 1;
        
        while(count!=0){
            count =0;
            HashSet<String> remove = new HashSet<>();
            for(int i=0; i<N-1; i++){
                for(int j=0; j<M-1; j++){
                    char x = map[i][j];
                    if(x!='.'&&x==map[i][j+1]&&x==map[i+1][j]&&x==map[i+1][j+1]){
                        remove.add(i+" "+j);
                    }
                }
            }
            //삭제 로직
            for(String rm : remove){
                count++;
                StringTokenizer st = new StringTokenizer(rm);
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y]='.';
                map[x+1][y]='.';
                map[x][y+1]='.';
                map[x+1][y+1]='.';
            }
            // 빈칸 줄이기 로직
            for(int j=0; j<M; j++){
                ArrayList<Character> temp = new ArrayList<>();
                for(int i=N-1; i>=0; i--){
                    if(map[i][j]!='.'){
                        temp.add(map[i][j]);
                    }
                    map[i][j]='.';
                }
                for(int i=0; i<temp.size(); i++){
                    map[N-i-1][j] = temp.get(i);
                }
            }
        }
        int total=0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j]=='.'){
                    total++;
                }
            }
        }
        return total;
    }
}
// 2곱 2짜리 칸들이 삭제되고 나서 위의 알파벳들을 내려준다. 
// 지워줄 칸들을 remove HashSet<Node>에 넣어주고 마지막에 한꺼번에 삭제해주고 빈칸들을 줄여준다.
//빈칸을 지우는 방법은 이렇다 밑에서부터 남은 블럭들을
//ArrayList에 넣어주고 개수만큼 밑에서 순회하며 넣어준다.
//더이상 줄어드는 경우가 없을경우 return해준다.
//이번 동작으로 인해 삭제된 블록이 없다면? -> 조건으로 종료