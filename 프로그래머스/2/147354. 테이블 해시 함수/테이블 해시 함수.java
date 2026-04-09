import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        ArrayList<int []> database = new ArrayList<>();
        for(int [] x: data){
            database.add(x);
        }
        //정렬
        Collections.sort(database, Comparator.comparingInt((int [] o1)-> o1[col-1]).thenComparingInt(o1->o1[0]*-1));
        //row_begin과 row_end사이의 S_i값에 대해 XOR연산
        for(int i=row_begin - 1; i<=row_end - 1; i++){
            //초기 S_i 설정
            if(i == row_begin-1){
                for(int x: database.get(row_begin-1)){
                    answer+= x%(i+1);
                }
            }
            //XOR 연산 진행
            else{
                int S_i = 0;
                for(int x: database.get(i)){
                    S_i+= x%(i+1);
                }
                //XOR 연산하기 
                answer ^= S_i;
            }
        }
        return answer;
    }
}
/*
col번째 컬럼 기준 오름차순 정렬 
만약 같으면 기본키의  첫 번째 컬럼의 값을 기준으로 내림차순 정렬(O)
각 행의 값에 대해서 해당 행의 순서인 i로 mod 연산한 값의 합을 S_i로 구하기()
그럼 row_begin과 row_end의 사이의 S_i값을 전부 XOR연산()
*/