import java.util.*;
class Solution {
    static ArrayList<String> result;
    static String [][] table;
    static int [][] parent;// i*50+j로 숫자로 부모 저장 -> union find할떄 편하게 하기위함
    public String[] solution(String[] commands) {
        result = new ArrayList<>();
        table = new String[50][50];
        parent = new int[50][50];
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                parent[i][j] = i*50+j;
                table[i][j] =null;
            }
        }
        for(String x : commands){
            String [] command = x.split(" ");
            if(command[0].equals("UPDATE")){
                if(command.length==4){ //좌표 - 값 update 1
                    int targetX = Integer.parseInt(command[1])-1;
                    int targetY = Integer.parseInt(command[2])-1;
                    update1(targetX, targetY, command[3]);
                }
                else{//값 - 값 update 2
                    update2(command[1], command[2]);
                }
            }
            else if(command[0].equals("MERGE")){
                int targetX = find(Integer.parseInt(command[1])-1,Integer.parseInt(command[2])-1);
                int targetY = find(Integer.parseInt(command[3])-1,Integer.parseInt(command[4])-1);
                if(targetX==targetY){
                    continue;
                }
                String temp = table[targetX/50][targetX%50];
                if(table[targetX/50][targetX%50]==null){
                    temp = table[targetY/50][targetY%50];
                    int tp = targetX;
                    targetX = targetY;
                    targetY = tp;
                }
                merge(targetX, targetY);
                int num = find(targetX/50, targetX%50);
                table[num/50][num%50] = temp;
            }
            else if(command[0].equals("UNMERGE")){
                int targetX = Integer.parseInt(command[1])-1;
                int targetY = Integer.parseInt(command[2])-1;
                String temp = print(targetX, targetY);
                unmerge(targetX, targetY);
                table[targetX][targetY] = temp;
            }
            else if(command[0].equals("PRINT")){
                int targetX = Integer.parseInt(command[1])-1;
                int targetY = Integer.parseInt(command[2])-1;
                String printString = print(targetX, targetY)==null? "EMPTY":print(targetX, targetY);
                result.add( printString);
            }
        }
        //마지막에 결과 반환
        String[] answer = new String[result.size()];
        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
    static int find(int x, int y){
        if(parent[x][y]==x*50+y){
            return x*50+y;
        }
        table[x][y] = null;
        return parent[x][y] = find(parent[x][y]/50, parent[x][y]%50);
    }
    static void merge(int x, int y){//i*50+j 형식으로 받음
        int xP = find(x/50, x%50);
        int yP = find(y/50, y%50);
        if(xP == yP){
            return;
        }
        else{
            parent[yP/50][yP%50] = xP;
        }
    }
    static void update1(int i, int j, String value){
        int num = find(i, j);
        table[num/50][num%50] = value;
    }
    static void update2(String target, String value){
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                if(table[i][j]!=null&&table[i][j].equals(target)&&parent[i][j]==i*50+j){
                    table[i][j] = value;
                }
            }
        }
    }
    static void unmerge(int x, int y){
        int group = find(x, y);
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                find(i,j);
            }
        }
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                if(find(i, j)==group){
                    parent[i][j] = i*50+j;
                    table[i][j]=null;
                }
            }
        }
    }
    static String print(int x, int y){
        int num = find(x, y);
        return table[num/50][num%50];
    }
}

//update(위치-값(총 명령문 포함 4개), 값-값 경우로 나뉨(총 3개))

//함수별 동작(update, merger, unmerge, print)
//update는 그냥 2차원 string배열에 넣어주면 됨
//merge를 했을때는 unionFind를 통하여 parent배열에 부모 저장
//unmerge했을때 해당 칸에 값 넣어주고 연결된 나머지는 다 비움 