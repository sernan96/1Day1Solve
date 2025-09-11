import java.util.*;

class Solution {
    static int N, M;
    static int [] dx = {0, 0, 1, -1};
    static int [] dy = {1, -1, 0, 0};
    static int [] preLocation (int max, int start, int end, int move){
        int startPoint = (start==0 && move>0)? 0 : start + move;
        int endPoint = (end==max-1 && move<0)? max-1 : end + move;
        if((startPoint<0||startPoint>=max)&&(endPoint<0||endPoint>=max)){
            // 둘다 범위밖
            return new int[]{-1, -1};
        }
        else if((startPoint<0||startPoint>=max)&&(endPoint>=0&&endPoint<max)){
            // 시작점만 범위밖
            return new int[]{0, endPoint};
        }else if((endPoint<0||endPoint>=max)&&(startPoint>=0&&startPoint<max)){
            // 끝점만 범위밖
            return new int[]{startPoint, max-1};
        }
        else{
            return new int[]{startPoint, endPoint};
        }
    }
    public long solution(int n, int m, int x, int y, int[][] queries) {
        N = n;
        M = m;
        int sX=x, eX=x, sY=y, eY=y; 
        for(int i=queries.length-1; i>=0; i--){
            int dir = queries[i][0];
            int move = queries[i][1];
            if(dir<2){
                int [] result = preLocation(m, sY, eY, move*dy[dir]);
                if(result[0]==-1) return 0;
                sY = result[0];
                eY = result[1];
            }
            else{
                int [] result = preLocation(n, sX, eX, move*dx[dir]);
                if(result[0]==-1) return 0;
                sX = result[0];
                eX = result[1];
            }
        }
        return (long)(eX-sX+1)*(eY-sY+1);
    }
}