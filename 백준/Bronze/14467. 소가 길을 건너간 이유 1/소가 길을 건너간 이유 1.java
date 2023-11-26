import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int cnt = 0;
		boolean[] arr = new boolean[10];
		int[] arr_location = new int[10];
		for (int i = 0; i < n; i++) {
			String[] cow = br.readLine().split(" ");
			int c = Integer.parseInt(cow[0]) - 1;
			int l = Integer.parseInt(cow[1]);
			if (arr[c]) {
				if (l != arr_location[c]) {
					cnt++;
					arr_location[c] = l;

				}
			} else {
				arr[c] = true;
				arr_location[c] = l;
			}
		}
		System.out.println(cnt);
	}
}