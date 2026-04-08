class Solution {
    static int [][] tri;
    static int N;
    static int [] dx = {1, 0, -1};
    static int [] dy = {0, 1, -1};
    
    public int[] solution(int n) {
        N = n;
        tri = new int[N][N];
        
        int [] answer = new int [N*(N+1)/2];
        makeTri(0, 0, 0, 1);
        //삼각형 채운거 출력
        int index = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<=i; j++){
                answer[index++]  = tri[i][j];
            }
        }
        return answer;
    }
    
    static boolean canGo(int x, int y){
        return x>=0 && y>=0 && x<N && y<N && tri[x][y]==0;
    }
    
    void makeTri(int x, int y, int dir, int num){
        //해당 방향으로 갈 수 있는 만큼 가기
        while(canGo(x, y)){        
            tri[x][y] = num++;
            int mx = x + dx[dir];
            int my = y + dy[dir];
            if(!canGo(mx, my)){
                break;
            }
            x = mx;
            y = my;
        }
        int newDir = (dir + 1) % 3;
        int newDirMx = x + dx[newDir];
        int newDirMy = y + dy[newDir];
        if(canGo(newDirMx, newDirMy)){
            makeTri(newDirMx, newDirMy, newDir, num);
        }
    }
    
}
/*
사실상 제일 마지막 숫자만 알면 이에 대해서 순서대로 배열에 넣어서 출력하면 되는 문제임
그렇다는건 1~1000까지 합은
505000

단순 구현으로 충분함
이를 구현하기 위해 필요한 것은 
- 가장 마지막 번호
- 규칙
2차원 n*n 배열을 만들고
재귀로 하 우 상좌를 반복함(dir = 0, 1, 2)
1. 하로 갈때는 밑에 0이 아닌 숫자가 있거나 끝인 경우에 우 호출
2. 우로 갈때는 마찬가지로 0이 아는 숫자가 있거나 끝인 경우에 상좌 호출
3. 상좌로 갈때는 상좌에 다른 숫자가 있으면 하 호출
1
2 9
3 10 8
4 5  6 7

*/