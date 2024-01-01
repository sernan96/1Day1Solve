import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

public class Main {
    static int [] target= new int[2];
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int r = Integer.parseInt(str[1]);
        int c = Integer.parseInt(str[2]);
        int k= (int)Math.pow(2, n);
        target[1] = r;
        target[0] = c;
        Z(0, 0, k);
    }
    static int cnt =0;
    public static void Z(int x, int y, int leng){
        // 4개로 나누었을때 해당 사각형에서 또 Z를 돌리는 방식으로 진행 해당 좌표가 나눌수없는 최소의 사각형이 될때까지
        if(leng<=2){
            for(int i = y; i<y+2; i++){
                for(int j=x; j<x+2; j++){
                    cnt++;
                    //System.out.println(j+" "+i);
                    if(j==target[0] && i==target[1]){
                        System.out.print(cnt-1);
                        return;
                    }
                }
            }
        }
        if(leng/2==target[0] && leng/2 ==target[1]){
            cnt+=(int)Math.pow((double) leng /2, 2)*3;
            System.out.print(cnt);
            exit(0);
        }

        else if(leng!=0){
            if(target[0] <x+leng/2 && target[1] <y+leng/2){//첫 사각형
                //System.out.print("좌상 ");
                Z(x, y, leng/2);
            }
            else if(target[0] >=x+leng/2 && target[1] <y+leng/2){// 우측 상단 사각형
                //System.out.print("우상 ");
                cnt+=(int)Math.pow((double) leng /2, 2);
                Z(x+leng/2, y, leng/2);
            }
            else if(target[0] <x+leng/2 && target[1] >=y+leng/2){// 좌측 하단 사각형
                //System.out.print("좌하 ");
                cnt+=(int)Math.pow((double) leng /2, 2)*2;
                Z(x, y+leng/2, leng/2);
            }
            else if(target[0] >=x+leng/2 && target[1] >=y+leng/2){// 우측 하단 사각형
                //System.out.print("우하 ");
                cnt+=(int)Math.pow((double) leng /2, 2)*3;
                Z(x+leng/2, y+leng/2, leng/2);
            }
        }
    }
}