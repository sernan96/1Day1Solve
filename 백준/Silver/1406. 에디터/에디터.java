
import java.util.Scanner;
import java.io.*;
import java.util.Stack;
public class Main {
	public static void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character>stl = new Stack<>();
		Stack<Character>str = new Stack<>();
		char[] s = br.readLine().toCharArray();
		int stl_size=0, str_size=0;
		for(char x:s) {//stl 스택저장
			stl.push(x);
			stl_size++;
		}

		int n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			char[] menu = br.readLine().toCharArray();
			if(menu[0]=='L' && stl_size != 0) {
			    str.push(stl.peek());
			    stl.pop();
			    stl_size--;
			    str_size++;
			} else if(menu[0]=='D' && str_size != 0) {
			    stl.push(str.peek());
			    str.pop();
			    stl_size++;
			    str_size--;
			} else if(menu[0]=='B' && stl_size != 0) {
			    stl.pop();
			    stl_size--;
			} else if(menu[0]=='P') {
			    stl.push(menu[2]);
			    stl_size++;
			}	
		}
		StringBuilder sb = new StringBuilder(stl_size + str_size);
		if(stl_size!=0) {
			for(int i1=0; i1<stl_size; i1++) {
				sb.append(stl.peek());
				stl.pop();
			}
			sb = sb.reverse();
		}
		if(str_size!=0) {
			for(int i1=0; i1<str_size; i1++) {
				sb.append(str.peek());
				str.pop();
			}
		}
		System.out.println(sb);

	}
}
