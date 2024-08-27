import java.io.*;
import java.util.*;

public class Main {
    static int [] attackNum = {9,3,1};
    static Integer [] hp;
    static List<int []> attack = new ArrayList<>();
    static class scv{
        int s1, s2, s3, cnt;
        public scv(int s1, int s2, int s3, int cnt){
            this.cnt = cnt;
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer scvs = new StringTokenizer(br.readLine());
        hp = new Integer[3];
        for(int i=0; i<3; i++){
            if(i<N){
                hp[i] = Integer.parseInt(scvs.nextToken());
            }
            else{
                hp[i] = Integer.valueOf(0);
            }
        }
        Arrays.sort(hp, Comparator.reverseOrder());// 내림차순 정렬
        makeAttack(new ArrayList<>());
        BFS();
    }
    static void makeAttack(List<Integer> list){
        if(list.size()==3){
            int [] temp = new int[3];
            for(int i=0; i<3; i++){
                temp[i] = list.get(i);
            }
            attack.add(temp);
            return;
        }
        for(int i=0; i<3; i++){
            if(!list.contains(attackNum[i])){
                list.add(attackNum[i]);
                makeAttack(list);
                list.remove((Integer) attackNum[i]);
            }
        }
    }
    static void BFS(){
        Queue<scv> q = new LinkedList<>();
        q.add(new scv(0,0,0, 1));
        boolean visited [][][] = new boolean[61][61][61];
        while (!q.isEmpty()){
            scv current = q.poll();
            for(int []x: attack){
                int S1 = Math.min(60, current.s1 + x[0]);
                int S2 = Math.min(60, current.s2 + x[1]);
                int S3 = Math.min(60, current.s3 + x[2]);
                if(visited[S1][S2][S3]){
                   continue;
                }
                else {
                    visited[S1][S2][S3]=true;
                }
                if(S1>=hp[0] && S2>=hp[1] && S3>=hp[2]){
                    System.out.print(current.cnt);
                    return;
                }
                q.add(new scv(S1, S2, S3, current.cnt+1));
            }
        }
    }
}