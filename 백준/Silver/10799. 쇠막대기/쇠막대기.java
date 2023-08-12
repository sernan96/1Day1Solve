import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int openCount = 0;  // 열린 괄호 '('의 개수를 저장
        int totalPieces = 0;  // 잘린 쇠막대기 조각의 총 개수를 저장

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == '(') {
                openCount++;  // 열린 괄호 개수 증가
            } else if (currentChar == ')') {
                openCount--;  // 열린 괄호 개수 감소

                // 바로 이전에 열린 괄호가 없는 경우 (레이저인 경우)
                if (input.charAt(i - 1) == '(') {
                    totalPieces += openCount;  // 열린 괄호 개수만큼 쇠막대기 조각 추가
                } else {
                    totalPieces++;  // 쇠막대기 끝점인 경우 하나의 조각 추가
                }
            }
        }

        System.out.println(totalPieces);
    }
}