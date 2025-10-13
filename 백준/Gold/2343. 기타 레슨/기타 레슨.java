import java.io.*;
import java.util.*;
public class Main {
    static int N, M, result;
    static int [] lectures;
    public static void main(String[] args) throws Exception {
        // 입력 및 변수 선언 부분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        lectures = new int[N];
        int sum = 0;
        result = 0;
        for (int i=0; i<N; i++){
            lectures[i] = Integer.parseInt(st.nextToken());
            sum+=lectures[i];
        }

        // 이분 탐색으로 블루레이 수 찾기
        int left = 1;
        int right = sum;
        int mid=0;
        while (left<=right){
            mid = (left+right)/2;
            //가능하다면 더 작은 수로 해보기
            if(findLen(mid)){
                right = mid -1;
            }
            //안되면 큰 수로
            else{
                left = mid+1;
            }
        }
        System.out.print(result);
    }
    // 해당 길이로 가능한지
    static boolean findLen(int target){
        int cnt =0;
        int temp = 0;
        for(int i=0; i<N; i++){
            if(target<lectures[i]){
               return false;
            }
            if(temp==0|| temp+lectures[i]>target){
                cnt++;
                temp=lectures[i];
            }
            else{
                temp+=lectures[i];
            }
        }
        result = cnt<=M? target:result;
        return cnt<=M;
    }
}
/*
블루레이 크기를 이분탐색으로 찾는다
검증할때는 해당 블루레이 크기로 순서대로 강의를 넣어 가능하다면 true return 아니면 false return

*/