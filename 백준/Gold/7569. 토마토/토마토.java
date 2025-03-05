//일단 익은 토마토를 저장(x, y)하는 HashSet 두기
//그래서 익은 토마토들을 하루마다 전부 큐에 넣고 BFS를 돌아줌
//전체 반복문의 조건은 Set의 크기가 N * M * H보다 작을때
//그러나 불가능한 상황도 있다. 그럴땐 전과 이후의 익은 토마토 개수가 같을때 -1 출력해주고 종료

import java.io.*;
import java.util.*;
class Main{
    static HashSet<Node> oldTomato, tempTomato;
    static int N, M, H;
    static class Node{
        int x, y, h;
        public Node(int x, int y, int h){
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer MNH = new StringTokenizer(br.readLine()); //난 세로 X 가로가 편해서 임의로 변경
        M = Integer.parseInt(MNH.nextToken());
        N = Integer.parseInt(MNH.nextToken());
        H = Integer.parseInt(MNH.nextToken());
        int [][][] map = new int [N][M][H];
        oldTomato = new HashSet<>();
        tempTomato = new HashSet<>();
        int empty = 0;
        for(int height=0; height<H; height++){
            for(int i=0; i<N; i++){
                StringTokenizer input = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++){
                    int x = Integer.parseInt(input.nextToken());
                    if(x==1){//익은토마토 저장
                        tempTomato.add(new Node(i, j, height));
                        oldTomato.add(new Node(i,j,height));
                    }
                    else if(x==-1){
                        empty++;
                    }
                    map[i][j][height] = x;
                }
            }
        }
        if(N*M*H-empty==tempTomato.size()){
            System.out.print(0);
            return;
        }
        int time =0;
        int [] dx = {1, 0, -1, 0};
        int [] dy = {0, 1, 0, -1};
        int [] dh = {1, 0, -1}; //대각선으로 이동하는거 조심 dh가 0이 아닐때는 dx, dy가 0이어야함
        //tempTomato size가 0인 경우는 2가지
        //1. 전부 다익음 while문 종료후 result 출력
        //2. 더 못익힘 while문 종료 안하고 -1출력 후 return
        while(N*M*H>oldTomato.size()+empty){
            if(tempTomato.isEmpty()){
                System.out.print(-1);
                
                return;
            }
            time++;
            ArrayDeque<Node> q = new ArrayDeque<>();
            for(Node tomato:tempTomato){
                q.add(new Node(tomato.x, tomato.y, tomato.h));
            }
            tempTomato.clear();
            while(!q.isEmpty()){
                Node cur = q.poll();
                int x = cur.x;
                int y = cur.y;
                int h = cur.h;
                for(int i = 0; i<3; i++){
                    if(dh[i]==0){
                        for(int j=0; j<4; j++){
                            int mx = x + dx[j];
                            int my = y + dy[j];
                            if(canGo(mx, my, h)&& map[mx][my][h]==0){
                                tempTomato.add(new Node(mx, my, h));
                                map[mx][my][h]=1;
                            }
                        }
                    }
                    else{
                        int mh = h + dh[i];
                        if(canGo(x, y, mh)&&map[x][y][mh]==0){
                            tempTomato.add(new Node(x, y, mh));
                            map[x][y][mh]=1;
                        }
                    }
                }
            }
            for(Node temp : tempTomato){
                oldTomato.add(new Node(temp.x, temp.y, temp.h));
            }
        }
        System.out.print(time);
    }
    static boolean canGo(int x, int y, int h){
        return x>=0 && y>=0 && h>=0 && x<N && y<M && h<H;
    }
}