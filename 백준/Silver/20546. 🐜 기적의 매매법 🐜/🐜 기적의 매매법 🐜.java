import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class money {
	public int remain;// 남은 돈
	public int count;// 주식 수

	money(int remain, int count) {
		this.remain = remain;
		this.count = count;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// Stack <Boolean> inc = new Stack<>();
		// Stack <Boolean> dec = new Stack<>();
		int cash = Integer.parseInt(br.readLine());
		StringTokenizer tk = new StringTokenizer(br.readLine());
		int[] days = new int[14];
		for (int i = 0; i < 14; i++) {
			days[i] = Integer.parseInt(tk.nextToken());
		}
		boolean first = true;
		money Jun = new money(cash, 0);
		money Sung = new money(cash, 0);
		for (int x : days) {
			if (Jun.remain >= x) {
				Jun.count += Jun.remain / x;
				Jun.remain -= x * (Jun.remain / x);
			}
		}
		int inc_cnt = 0;
		int dec_cnt = 0;
		Jun.remain = Jun.remain + Jun.count * days[days.length - 1];
		for (int i = 0; i < 14; i++) {
			if (!first) {
				if (days[i - 1] > days[i]) {
					inc_cnt = 0;
					dec_cnt++;
				} else if (days[i - 1] < days[i]) {
					dec_cnt = 0;
					inc_cnt++;
				} else if (days[i - 1] == days[i]) {
					inc_cnt = 0;
					dec_cnt = 0;
				}
			}
			if (i != 13) {
				if (dec_cnt >= 2 && Sung.remain > 0) {// buy
					Sung.count += Sung.remain / days[i + 1];
					Sung.remain = (Sung.remain % days[i + 1]);
					// System.out.println("buying: " + Sung.remain + " " + (i + 2) + "DAY");
				}
				if (inc_cnt >= 2 && Sung.count > 0) {// sell
					Sung.remain += days[i + 1] * Sung.count;
					Sung.count = 0;

					// System.out.println("selling: " + Sung.remain + " " + (i + 2) + "DAY");
				}
			}

			first = false;
		}
		if (Sung.count > 0) {
			Sung.remain = Sung.remain + Sung.count * days[days.length - 1];
		}
		if (Sung.remain > Jun.remain) {
			System.out.println("TIMING");
			// System.out.println("Jun: " + Jun.remain + " Sung: " + Sung.remain);
		} else if (Sung.remain < Jun.remain) {
			System.out.println("BNP");
			// System.out.println("Jun: " + Jun.remain + " Sung: " + Sung.remain);
		} else {
			System.out.println("SAMESAME");
		}
	}
}