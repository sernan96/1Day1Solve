
import java.io.*;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> st2 = new Stack<>();

        int n = Integer.parseInt(br.readLine());
    // 입력 값 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num;
        int []arr = new int[n];
        int[] list = new int[n];
        HashMap<Integer, Integer>map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            num= Integer.parseInt(st.nextToken());
            arr[i]=num;
        	if(!map.containsKey(num)) {// map 등록	
        		map.put(num, 1);
        		
        	}
        	else {
        		map.replace(num, map.get(num)+1);
        	}
        }
        for (int i = n-1; i >= 0; i--) {
        	while(!st2.empty()&&map.get(st2.peek())<=map.get(arr[i])) {
        		st2.pop();
        	}
        	list[i] = st2.empty() ? -1: st2.peek();
        	st2.push(arr[i]);
        }
        for (int x: list) {
        	bw.write(x+" ");

        }
        bw.flush();
        bw.close();

    }
}