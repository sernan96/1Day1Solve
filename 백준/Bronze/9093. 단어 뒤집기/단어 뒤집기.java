import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String sen = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(sen, " ");

            StringBuilder sb2 = new StringBuilder(); // Use StringBuilder instead of StringBuffer
            while (st.hasMoreTokens()) {
                StringBuilder sb = new StringBuilder(st.nextToken()); // Use StringBuilder instead of StringBuffer
                sb.reverse();
                sb2.append(sb).append(" "); // Append sb without converting to string
            }
            System.out.println(sb2);
        }
        scanner.close();
    }
}