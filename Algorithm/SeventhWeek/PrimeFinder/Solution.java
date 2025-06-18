package SeventhWeek.PrimeFinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int solution(String numbers) {
        int answer = 0;
        // 문자열 numbers => 문자배열 nums로 변환.
        int[] nums = new int[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
            nums[i] = numbers.charAt(i);  //참고: 숫자형태의 문자에서 '0'을빼면 정수로 변환됨.
        }
        Arrays.sort(nums); // 배열 오름차순 정렬  //필요한지모름. 필요없어지면 지울거.
        boolean[] v = new boolean[nums.length];
        backtrack(nums, v, answer, new ArrayList<>());
        return answer;
    }
    public boolean isPrime(int k){
        if(k<2) return false;
        for(int i=2; i*i<k; i++){
            if(k%i == 0) return false;
        }
        return true;
    }
    public int CharArrToString(char[] nums){
        String str = new String(nums);
        int cnt=0;
        for(int i=0; i<nums.length; i++){
            if(str.charAt(i)=='0'){cnt++;}
            if(str.charAt(i)!='0'){
                String str2 = str.substring(cnt-1,nums.length-1);
                break;
            }
        }
        int intNum = Integer.parseInt(str2);
        return intNum;
    }
    public void backtrack(int[] nums, boolean[] v, int answer, List<int[]> curr) {
        if(curr.size() == nums.length) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!v[i]) {
                v[i] = true;
                curr.add(new int[]{nums[i]});

            }
        }
    }
}
