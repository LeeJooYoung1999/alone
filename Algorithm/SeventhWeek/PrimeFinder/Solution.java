package SeventhWeek.PrimeFinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int solution(String numbers) {
        int answer = 0;
        // 문자열 numbers => 정수배열 curr로 변환.
        int[] curr = new int[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
            curr[i] = numbers.charAt(i) - '0';  //참고: 숫자형태의 문자에서 '0'을빼면 정수로 변환됨.
        }
        Arrays.sort(curr); // 배열 오름차순 정렬  //필요한지모름. 필요없어지면 지울거.
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length(); i++) {
            
        }


        return answer;
    }
    public boolean isPrime(int k){
        if(k<2) return false;
        for(int i=2; i*i<k; i++){
            if(k%i == 0) return false;
        }
        return true;
    }

}
