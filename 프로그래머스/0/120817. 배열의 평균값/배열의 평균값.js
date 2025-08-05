function solution(numbers) {
    var answer = 0;
    for(const number of numbers){
        answer+= number;
    }
    
    return answer/numbers.length;
}