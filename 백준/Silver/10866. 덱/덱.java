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
			switch(arr[0]) {
				case("push_front"):
					q.addFirst(Integer.parseInt(arr[1]));
					size++;
					break;
				case("push_back"):
					q.addLast(Integer.parseInt(arr[1]));
					size++;
					break;
				case("pop_front"):
					if(size==0)
					{
						bw.write("-1"+"\n");
						bw.flush();
						break;
					}
					size--;
					bw.write(q.pollFirst()+"\n");
					bw.flush();
					break;
				case("pop_back"):
					if(size==0)
					{
						bw.write("-1"+"\n");
						bw.flush();
						break;
					}
					size--;
					bw.write(q.pollLast()+"\n");
					bw.flush();
					break;
				case("size"):
					bw.write(size+"\n");
					bw.flush();
					break;
				case("empty"):
					if(size==0) {
						bw.write("1"+"\n");
						bw.flush();
					}
					else {
						bw.write("0"+"\n");
						bw.flush();
					}
					break;
				case("front"):
					if(size==0)
					{
						bw.write("-1"+"\n");
						bw.flush();
						break;
					}
					bw.write(q.getFirst()+"\n");
					bw.flush();
					break;
				case("back"):
					if(size==0)
					{
						bw.write("-1"+"\n");
						bw.flush();
						break;
					}
					bw.write(q.getLast()+"\n");
					bw.flush();
					break;
			}
		}
		bw.close();
	}
}