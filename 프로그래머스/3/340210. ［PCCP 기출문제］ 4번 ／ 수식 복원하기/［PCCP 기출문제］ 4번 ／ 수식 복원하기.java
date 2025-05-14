import java.util.*;

class Solution {

    class Data{
        String A, B, C;
        boolean isAdd;

        Data(String expression){

            StringTokenizer st = new StringTokenizer(expression);

            A = st.nextToken();
            isAdd = st.nextToken().equals("+");
            B = st.nextToken();
            st.nextToken();
            C = st.nextToken();
        }

        String calculate(int radix, boolean check){
            int aa = Integer.parseInt(A, radix);
            int bb = Integer.parseInt(B, radix);

            if(!isAdd){
                bb *= -1;
            }

            int calculatedValue = aa + bb;
            if(!check || Integer.parseInt(C, radix) == calculatedValue){
                return Integer.toString(calculatedValue, radix).toUpperCase();
            }
            return null;
        }

    }

    public String[] solution(String[] expressions) {

        List<Data> dataList = new ArrayList<>();
        for(String exp : expressions){
            dataList.add(new Data(exp));
        }

        int minRadix = 2;

        for(String exp : expressions){
            for(int i = 0; i < exp.length(); i++){
                char c =  exp.charAt(i);
                if( '0' <= c && c <= '9'){
                    minRadix = Math.max(minRadix, c - '0' + 1);
                }
            }
        }

        boolean failed[] = new boolean[10];

        for(Data data : dataList){
            if(data.C.equals("X")){
                continue;
            }

            for(int i = minRadix; i <= 9; i++){
                if(failed[i]){
                    continue;
                }

                try {
                    if(data.calculate(i, true) == null){
                        failed[i] = true;
                    }
                } catch (NumberFormatException e) {
                    failed[i] = true;
                }
            }
        }

        List<String> answerList = new ArrayList<>();
        StringBuilder sb;

        for(Data data : dataList){
            if(!data.C.equals("X")){
                continue;
            }

            String temp = "";
            boolean flag = false;

            sb = new StringBuilder();
            sb.append(data.A).append(data.isAdd ? " + " : " - ").append(data.B).append(" = ");

            for(int i = minRadix; i <= 9; i++){
                if(failed[i]){
                    continue;
                }

                String calculated = null;
                try {
                    calculated = data.calculate(i, false);
                } catch (NumberFormatException e) {
                    // 해당 진법에서 숫자가 아닌 경우, calculated는 null 상태 유지
                }

                if (calculated != null) {
                    if (temp.length() == 0) {
                        temp = calculated;
                    } else if (!temp.equals(calculated)) {
                        flag = true;
                        break;
                    }
                }
            }

            if(flag || temp.length() == 0){
                sb.append("?");
            }else{
                sb.append(temp);
            }
            answerList.add(sb.toString());
        }

        String[] answer = new String[answerList.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}