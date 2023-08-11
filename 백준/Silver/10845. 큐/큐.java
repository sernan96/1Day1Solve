import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String []args) throws IOException {
		Deque<Integer> q= new ArrayDeque<Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String temp =  br.readLine();
		int n =  Integer.parseInt(temp); //int로 형변환
		int size =0;
		for(int i=0; i<n; i++) {
			String menu =  br.readLine();
			String arr[] = menu.split(" ");
			if(arr[0].equals("push")) { //push
				q.add(Integer.parseInt(arr[1]));
				size++;
			}
			else if(arr[0].equals("pop") && size>0) { //pop
				size--;
				bw.write(q.pollFirst()+"\n");
				bw.flush();
			}
			else if(arr[0].equals("size")) { //size
				bw.write(size+"\n");
				bw.flush();
			}
			else if(arr[0].equals("empty")) { //empty
				if(size==0) {
					bw.write("1"+"\n");
					bw.flush();
				}
				else {
					bw.write("0"+"\n");
					bw.flush();
				}
			}
			else if(arr[0].equals("front")&& size>0) { //front
				bw.write(q.getFirst()+"\n");
				bw.flush();
			}
			else if(arr[0].equals("back")&& size>0) { //back
				bw.write(q.getLast()+"\n");
				bw.flush();
			}
			else { //큐가 비었을때
				bw.write("-1"+"\n");
				bw.flush();
			}
		}
		bw.close();
	}
}