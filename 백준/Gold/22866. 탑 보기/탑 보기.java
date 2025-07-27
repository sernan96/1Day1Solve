import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] arr = new int [N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        ArrayDeque<int []> stack = new ArrayDeque<>();
        int [] cnt = new int[N+1];
        //좌등큰수
        int [] left = new int [N+2];
        stack.add(new int[]{1, arr[1]});//번호 높이
        for (int i=2; i<=N; i++){
            if(stack.peekLast()[1]>arr[i]){
                left[i] = stack.peekLast()[0];
                cnt[i]+= stack.size();
            }
            else{
                while(!stack.isEmpty()){
                    if(stack.peekLast()[1]>arr[i]){
                        left[i] = stack.peekLast()[0];
                        cnt[i]+= stack.size();
                        break;
                    }
                    else{
                        stack.pollLast();
                    }
                }
            }
            stack.add(new int[]{i, arr[i]});
        }
        // 오등큰수
        stack.clear();
        int [] right = new int [N+1];
        stack.add(new int[]{N, arr[N]});//번호 높이
        for (int i=N; i>=1; i--){
            if(stack.peekLast()[1]>arr[i]){
                right[i] = stack.peekLast()[0];
                cnt[i]+= stack.size();
            }
            else{
                while(!stack.isEmpty()){
                    if(stack.peekLast()[1]>arr[i]){
                        right[i] = stack.peekLast()[0];
                        cnt[i]+= stack.size();
                        break;
                    }
                    else{
                        stack.pollLast();
                    }
                }
            }
            stack.add(new int[]{i, arr[i]});
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            if(left[i]+right[i]==0){
                sb.append("0");
            }
            else if(left[i]==0||right[i]==0) {
                sb.append(cnt[i]+" "+Math.max(left[i], right[i]));
            }
            else{
                // 더 거리가 가까운 건물의 번호가 필요
                int nearNum =0;
                if(Math.abs(left[i]-i)<= Math.abs(right[i]-i)){
                    nearNum=left[i];
                }
                else {
                    nearNum=right[i];
                }
                sb.append(cnt[i]+" "+nearNum);
            }
            if(i!=N){
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
/*
좌등큰 수 오등큰 수를 배열에 결과를 각 각 구해서
두 결과 중 작은 건물의 번호를 넣고 마지막에 출력해주면 된다.
*/