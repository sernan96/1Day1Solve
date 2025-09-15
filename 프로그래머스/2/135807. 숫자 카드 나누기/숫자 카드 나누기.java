import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int gcdA = arrayA[0];
        int gcdB= arrayB[0];
        for(int i=1; i<arrayA.length; i++){
            gcdA= getGcd(arrayA[i], gcdA);
        }
        for(int i=1; i<arrayB.length; i++){
            gcdB= getGcd(arrayB[i], gcdB);
        }
        if(!canDivide(arrayA, gcdB)){
            answer = Math.max(answer, gcdB);
        }
        if(!canDivide(arrayB, gcdA)){
            answer = Math.max(answer, gcdA);
        }
        return answer;
    }
    static int getGcd(int a, int b){
        if(a%b==0){
            return b;
        }
        return getGcd(b, a%b);
    }
    static boolean canDivide(int [] arr, int a){
        if(a==0){
            return false;
        }
        for(int x: arr){
            if(x%a==0){
                return true;
            }
        }
        return false;
    }
}