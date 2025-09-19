import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static int N;
    static int [] amounts;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        amounts = new int[N+1];
        graph = new ArrayList<>();
        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=2; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean isSheep = st.nextToken().equals("S");
            int amount = Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            amount *= isSheep?1:-1;// 양이면 양수 늑대면 음수처리
            graph.get(parent).add(i);
            amounts[i] = amount;
        }
        System.out.print(DFS(1));
    }
    static long DFS(int node){
        if(graph.get(node).isEmpty()){//리프노드라면
            //System.out.println(node+" 리프노드임: "+Math.max(amounts[node], 0));
            return Math.max(amounts[node], 0);
        }
        long child= amounts[node];
        for(int x:graph.get(node)){
            child += DFS(x);
        }
        //System.out.println("child 반환 "+node+"에서 "+(Math.max(0, child)));
        return Math.max(0, child);
    }
}
/*
리프 노드에서 부터 시작해서 1까지 최상위 노드인 노드 1 까지 도달한다.
경로는 유일하기 때문에 단방향 그래프로 저장을 해준다.
그리고 리프노드임은 처음에 그래프 입력받을때 연결된 엣지가 하나라면
1을 제외한 모든 엣지가 1인 노드는 리프노드이기 때문에 리프노드로 저장해준다.
그런데 밑에서부터 올라가면 중간에 중복되는 지점에서 중복하여 더해지기 때문에
위에서 부터 밑으로 내려가는 순서가 좋을듯하다.
DFS를 통해 리프노드로 간다. 그다음 거기서부터 하위 노드들의 양 수를 재귀로 더해가며
*/
