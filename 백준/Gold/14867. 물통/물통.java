import java.io.*;
import java.util.*;

public class Main {
    static int a, b, c, d;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        ArrayDeque<int []> q = new ArrayDeque<>();
        HashMap<String, Integer> visited = new HashMap<>();
        q.add(new int[]{0,0});
        visited.put(0+" "+0, 0);
        while (!q.isEmpty()){
            int [] cur = q.poll();
            int nowA = cur[0];
            int nowB = cur[1];
            int cnt = visited.get(nowA+" "+nowB);
            if(nowA==c&&nowB==d) {
                System.out.print(cnt);
                return;
            }
            //Fill x
            String fillAKey = a+" "+nowB;
            String fillBKey = nowA+" "+b;
            //a만 채우기
            if(!visited.containsKey(fillAKey)||visited.get(fillAKey)>cnt+1){
                q.add(new int[]{a, nowB, cnt+1});
                visited.put(fillAKey, cnt+1);

                //System.out.println(fillAKey+ " Fill A : "+ cnt+1);
            }
            //b만 채우기
            if(!visited.containsKey(fillBKey)||visited.get(fillBKey)>cnt+1){
                q.add(new int[]{nowA, b, cnt+1});
                visited.put(fillBKey, cnt+1);

                //System.out.println(fillBKey+ " Fill B : "+ cnt+1);
            }
            //Empty x
            String eraseAKey = 0+" "+nowB;
            String eraseBKey = nowA+" "+0;
            //a만 비우기
            if(!visited.containsKey(eraseAKey)||visited.get(eraseAKey)>cnt+1){
                q.add(new int[]{0, nowB, cnt+1});
                visited.put(eraseAKey, cnt+1);

                //System.out.println(eraseAKey+ " Erase A : "+ cnt+1);
            }
            //b만 비우기
            if(!visited.containsKey(eraseBKey)||visited.get(eraseBKey)>cnt+1){
                q.add(new int[]{nowA, 0, cnt+1});
                visited.put(eraseBKey, cnt+1);

                //System.out.println(eraseBKey+ " Erase B : "+ cnt+1);
            }
            //M(x, y)
            int [] moveXToY = moveWater(nowA, nowB, true);
            int [] moveYToX = moveWater(nowA, nowB, false);
            String moveFromAKey = moveXToY[0]+" "+moveXToY[1];
            String moveFromBKey = moveYToX[0]+" "+moveYToX[1];
            // x->y
            if(!visited.containsKey(moveFromAKey)||visited.get(moveFromAKey)>cnt+1){
                q.add(new int[]{moveXToY[0], moveXToY[1], cnt+1});
                visited.put(moveFromAKey, cnt+1);

                //System.out.println(eraseAKey+ " Move A->B : "+ cnt+1);
            }
            // y->x
            if(!visited.containsKey(moveFromBKey)||visited.get(moveFromBKey)>cnt+1){
                q.add(new int[]{moveYToX[0], moveYToX[1], cnt+1});
                visited.put(moveFromBKey, cnt+1);

                //System.out.println(eraseBKey+ " Move B->A : "+ cnt+1);
            }
        }
        System.out.print(-1);
    }
    static int [] moveWater(int x, int y, boolean naturalOrder){
        // a->b
        if(naturalOrder){
            int temp = y;
            y = Math.min(y+x, b);
            x -= y-temp;
            return new int []{x, y};
        }
        // b->a
        else{
            int temp = x;
            x = Math.min(x+y, a);
            y -= x-temp;
            return new int []{x, y};
        }
    }
}
/*
BFS를 통해서 원하는 곳에 도달하면 종료
방문처리는 <String, Integer>쌍의 hashmap으로 저장
 */