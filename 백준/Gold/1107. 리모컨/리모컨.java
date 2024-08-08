    import java.io.*;
    import java.util.*;

    public class Main {
        static int min=Integer.MAX_VALUE;
        static String N;
        static ArrayList<Integer> button = new ArrayList<>();//멀쩡버튼 리스트
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = br.readLine();
            int M = Integer.parseInt(br.readLine());
            if(M==10){
                System.out.print(Math.abs(Integer.parseInt(N)-100));
                return;
            }
            for(int i=0; i<10; i++){
                button.add(i);
            }
            if(M!=0){
                StringTokenizer st = new StringTokenizer(br.readLine());
                while (M--!=0){
                    button.set(Integer.parseInt(st.nextToken()), -1);
                }
            }
            min = Math.min(Math.abs(Integer.parseInt(N)-100), min);
            int lenN=N.length();
            if(lenN>1){
                makeNum(lenN-1, new StringBuilder());
            }
            makeNum(lenN, new StringBuilder());
            makeNum(lenN+1, new StringBuilder());
            System.out.print(min);
        }
        static void makeNum(int len, StringBuilder sb){
            if(sb.length()==len){
                min = Math.min(min, len+Math.abs(Integer.parseInt(N)-Integer.parseInt(sb.toString())));
                return;
            }
            for(int x: button){
                if (x>=0) {
                    sb.append(x);
                    makeNum(len, sb);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }