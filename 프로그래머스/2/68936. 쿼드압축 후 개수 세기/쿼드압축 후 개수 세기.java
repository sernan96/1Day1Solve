class Solution {
    static int one =0, zero =0;
    static int square[][];
    public int[] solution(int[][] arr) {
        square = arr;
        cutting(0, 0, arr.length);
        int[] answer = {zero, one};
        return answer;
    }
    void cutting(int start_x, int start_y, int length){
        int sum=0;
        for(int i =start_x; i<length+ start_x; i++){
            for(int j=start_y; j<length+ start_y; j++){
                sum+=square[i][j];
            }
        }
        if(sum==length*length){
            one++;
            return;
        }
        else if(sum==0){
            zero++;
            return;
        }
        else{
            cutting(start_x, start_y, length/2);
            cutting(start_x+length/2, start_y, length/2);
            cutting(start_x, start_y+length/2, length/2);
            cutting(start_x+length/2, start_y+length/2, length/2);
        }
        return;
    }
}