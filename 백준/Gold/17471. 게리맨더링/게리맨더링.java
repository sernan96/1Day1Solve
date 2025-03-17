//N개의 노드가 두 선거구로 나뉘면 된다.
//최대 2^10이므로 브루트포스로 선거구나눠서 ArrayList<String>에 저장
//그리고 갈 수 있는지 없는지 graph 확인 하며 BFS돌아줌
//(같은 선거구끼리 연결되어 있는지 확인하기 위함)
//만약 2번 돌았는데 덜 간곳이 있다면 연결이 안되어 있다는 뜻이므로 인구차이 계산 X
//1번 2번의 대표 노드는 선거구 저장된 String에서 indexOf로 한다.

import java.io.*;
import java.util.*;
class Main{
    static ArrayList<String> arr;
    static int N;
    public static void main(String [] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        //인구 수 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] population = new int[N];
        for(int i=0; i<N; i++){
            population[i] = Integer.parseInt(st.nextToken());
        }
        //그래프 생성
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i<N; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(input.nextToken());
            for(int j=0; j<num; j++){
                int end = Integer.parseInt(input.nextToken())-1;
                graph.get(i).add(end);
                graph.get(end).add(i);
            }
        }
        //브루트 포스로 모든 경우의 수 탐색해서 arr에 저장
        arr = new ArrayList<>();
        makeNM(new ArrayList<>());
        //이제 만든 문자열을 돌면서 검사
        //indexOf로 1선거구 2선거구 대표 뽑아내고
        //1선거구 2선거구 대표를 기준으로 같은 선거구 BFS로 탐색
        //(같은 visited배열 사용해도 ㄱㅊ)
        int result = Integer.MAX_VALUE;
        char [] oneTwo = {'0', '1', '2'};
        for(String x: arr){
            //BFS 총 2번 돎
            boolean [] visited = new boolean[N];
            for(int i=1; i<=2; i++){
                int target = x.indexOf(oneTwo[i]);
                ArrayDeque<Integer> q = new ArrayDeque<>();
                q.add(target);
                visited[target] = true;
                while(!q.isEmpty()){
                    int cur = q.poll();
                    for(int next: graph.get(cur)){
                        if(visited[next]||x.charAt(next)!=oneTwo[i]){
                            continue;
                        }
                        q.add(next);
                        visited[next] = true;
                    }
                }
            }
            boolean flag = false;
            for(boolean check: visited){
                if(!check){// 같은 선거구끼리 연결안된 경우 존재
                    flag = true;
                    break;
                }
            }
            if(flag){
                continue;
            }
            int [] sum = new int [3];
            for(int i=0; i<N; i++){
                sum[x.charAt(i)-'0']+=population[i];
            }
            result = Math.min(Math.abs(sum[1]-sum[2]), result);
        }
        System.out.print(result==Integer.MAX_VALUE?-1:result);
    }
    static void makeNM(ArrayList<Integer> temp){
        if(temp.size() ==N){
            //한지역의 선거구밖에 없는 경우는 바로 종료
            if(!temp.contains(1) || !temp.contains(2)){
                return;
            }
            StringBuilder sb = new StringBuilder();
            for(int x: temp){
                sb.append(x);
            }
            arr.add(sb.toString());
            return;
        }
        for(int i=1; i<=2; i++){
            temp.add(i);
            makeNM(temp);
            temp.remove(temp.size()-1);
        }
    }
}