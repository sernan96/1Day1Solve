import java.io.*;
import java.util.*;

public class Main {
    static int [] parent;
    static Set <Integer> knowPeople= new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        StringTokenizer knowlist = new StringTokenizer(br.readLine()); //아는 사람 입력
        int K = Integer.parseInt(knowlist.nextToken());
        parent = new int[N+1];// 부모노드를 저장해줄 배열
        for(int i =1; i<=N; i++){
            parent[i] = i;// union find 에선 제일 처음 부모배열은 자기 자신으로 초기화 해줌
        }
        if(K!=0){
            for(int i =0; i<K; i++){
                int knowNum = Integer.parseInt(knowlist.nextToken());
                knowPeople.add(knowNum);//거짓말임을 알고있는 사람들의 집합 생성
            }
        }
        else{
            System.out.print(M);
            return;
        }
        List<Integer>[] partyList = new ArrayList[M];
        for(int i =0; i<M; i++){
            partyList[i]=new ArrayList<>();
        }
        for(int i =0; i<M; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            int ppn = Integer.parseInt(input.nextToken());//party people number 파티 참석 숫자
            int standard = Integer.parseInt(input.nextToken());// 다른 그래프일 경우 연결 시켜줄 기준
            partyList[i].add(standard);
            for(int j =1; j<ppn; j++){
                int num = Integer.parseInt(input.nextToken());
                union(standard, num);
                partyList[i].add(num);//나중에 파티참석 리스트와 비교하기 위해
            }
        }
        int cnt=0;
        for(int i=0; i<M; i++){
            boolean canGo = true;
            for(int x: partyList[i]){
                if(knowPeople.contains(find(x))){
                    canGo=false;
                    break;
                }
            }
            if(canGo) {
                cnt++;
            }
        }
        System.out.print(cnt);
    }
    static void union(int x, int y){
        int px= find(x);
        int py= find(y);
        if(knowPeople.contains(py)){
            parent[px]=py;//거짓말임을 알고있는 사람을 parent로 바꿔줌
        }
        else{
            parent[py]=px;
        }
    }
    static int find (int x){
        if(parent[x]==x){
            return x;
        }
        else{
            return find(parent[x]);
        }
    }

}
