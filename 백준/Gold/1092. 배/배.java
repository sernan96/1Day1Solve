import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] cranes = new int[N];
        for(int i=0; i<N; i++){
            cranes[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int [] boxes = new int[M];
        for(int i=0; i<M; i++){
            boxes[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(boxes);
        Arrays.sort(cranes);
        int time = 0, cnt = 0;
        while (cnt!=M){
            time++;
            boolean [] used = new boolean[N];
            int usedCraneCnt = 0;
            for(int i=M-1; i>=0; i--){
                int left = 0;
                int right = N-1;
                int mid= (left+right)/2;
                int thisRound = mid;
                boolean flag= false;
                if(usedCraneCnt>=M){
                    break;
                }
                if(boxes[i]==-1){
                    continue;
                }
                while (left<=right){
                    mid = (left+right)/2;
                    if(cranes[mid]>=boxes[i]){
                        if(!used[mid]){
                            left = mid+1;
                            flag = true;
                            thisRound = mid;
                            //System.out.println("발견! "+thisRound);
                        }
                        else{
                            right = mid-1;
                        }
                    }
                    else{
                        left = mid+1;
                    }
                }
                if(flag){
                    //System.out.println(time+": "+cranes[thisRound]+" 크레인이 "+boxes[i]);
                    used[thisRound] = true;
                    boxes[i] = -1;
                    cnt++;
                    usedCraneCnt++;
                }
            }
            if(usedCraneCnt==0){
                System.out.print(-1);
                return;
            }
        }
        System.out.print(time);
    }
}
/*
이분탐색으로 어떤 크레인을 사용해서 박스를 옮길지 선택한다.
가장 가까운 값의 크레인을 선택해서 사용한다.
큰 상자부터 사용
만약에 젤큰 상자인데 불가능하다면 -1 출력 후 return
큰 순서대로 뽑고 뽑으면 -1로 변경
뽑을 수 없으면 다음턴으로 넘기기
*/