import java.io.*;
import java.util.*;
public class Main {// 3시 40분 시작
    static ArrayList<enemy> enemies_origin;
    static int N, M, D;
    static int attacked_enemy;
    static class enemy {
        int x, y;
        boolean alive;
        public enemy (int x, int y){
            this.x= x;
            this.y= y;
            this.alive = true;
        }
        public void move(){
            this.x++;
            if(this.x >= N){
                this.alive=false;
            }
        }
        public void attacked(){
            this.alive=false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NMD = new StringTokenizer(br.readLine());
        N = Integer.parseInt(NMD.nextToken());
        M = Integer.parseInt(NMD.nextToken());
        D = Integer.parseInt(NMD.nextToken());
        enemies_origin = new ArrayList<>();
        attacked_enemy =-1;
        // enemy 입력 받기
        for(int i=0; i<N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                if(input.nextToken().charAt(0)=='1'){
                    enemies_origin.add(new enemy(i, j));
                }
            }
        }
        // 궁수 브루트포스로 위치 지정시켜주기
        archersMake = new ArrayList<>();
        makeArchers(new ArrayList<>());
        gamePlay();
        System.out.print(attacked_enemy);
    }
    static ArrayList<ArrayList<Integer>> archersMake;
    static void makeArchers(ArrayList<Integer> archers){
        if(archers.size()==3){
            archersMake.add(new ArrayList<>(archers));
            return;
        }
        for(int i=0; i<M; i++){
            if(archers.isEmpty() || archers.get(archers.size()-1)<i){
                archers.add(i);
                makeArchers(archers);
                archers.remove((Integer) i);
            }
        }
    }
    static void gamePlay(){
        for(ArrayList<Integer> archer: archersMake){
            ArrayList<enemy> enemies = new ArrayList<>();
            for (enemy e : enemies_origin) {
                enemies.add(new enemy(e.x, e.y)); // 새로 객체를 만들어서 추가
            }
            int countAttack = 0;
            while(keepGoing(enemies)){
                PriorityQueue<enemy> archer1 = new PriorityQueue<>((o1, o2) -> {
                    int o1D = Math.abs(o1.x-N)+Math.abs(o1.y-archer.get(0));
                    int o2D = Math.abs(o2.x-N)+Math.abs(o2.y-archer.get(0));
                    return o1D==o2D?o1.y-o2.y:o1D-o2D;
                });PriorityQueue<enemy> archer2 = new PriorityQueue<>((o1, o2) -> {
                    int o1D = Math.abs(o1.x-N)+Math.abs(o1.y-archer.get(1));
                    int o2D = Math.abs(o2.x-N)+Math.abs(o2.y-archer.get(1));
                    return o1D==o2D?o1.y-o2.y:o1D-o2D;
                });PriorityQueue<enemy> archer3 = new PriorityQueue<>((o1, o2) -> {
                    int o1D = Math.abs(o1.x-N)+Math.abs(o1.y-archer.get(2));
                    int o2D = Math.abs(o2.x-N)+Math.abs(o2.y-archer.get(2));
                    return o1D==o2D?o1.y-o2.y:o1D-o2D;
                });
                Set<enemy> attacked = new HashSet<>();
                for(enemy e: enemies){
                    if(e.alive){
                        if(Math.abs(e.x-N)+Math.abs(e.y-archer.get(0))<=D){
                            archer1.add(e);
                        }
                        if(Math.abs(e.x-N)+Math.abs(e.y-archer.get(1))<=D){
                            archer2.add(e);
                        }
                        if(Math.abs(e.x-N)+Math.abs(e.y-archer.get(2))<=D){
                            archer3.add(e);
                        }
                    }
                }
                // 공격 대상 set에 넣어서 동시에 공격받은 적들 문제없이 처리
                if(!archer1.isEmpty()){
                    attacked.add(archer1.poll());
                }
                if(!archer2.isEmpty()){
                    attacked.add(archer2.poll());
                }
                if(!archer3.isEmpty()){
                    attacked.add(archer3.poll());
                }
                for(enemy de : attacked){ //공격처리
                    for(enemy adjustE: enemies){
                        if(adjustE.alive&&de.x==adjustE.x && de.y==adjustE.y){
                            adjustE.attacked();
                            countAttack++;
                        }
                    }
                }
                for(enemy moveE: enemies){
                    if(moveE.alive){
                         moveE.move();
                    }
                }
            }
            attacked_enemy=Math.max(attacked_enemy, countAttack);
        }
    }
    static boolean keepGoing(ArrayList<enemy> enemies){//생존 적 확인 함수
        for(enemy x: enemies){
            if(x.alive){
                return true;
            }
        }
        return false;
    }
}
