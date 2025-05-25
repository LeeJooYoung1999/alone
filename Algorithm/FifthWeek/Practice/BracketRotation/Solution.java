package FifthWeek.Practice.BracketRotation;

import java.util.*;

public class Solution {
    public int solution(String s) {
        int count = 0;
        int len = s.length();
        //3.for 반복문을 이용한 유효Case 카운팅 코드
        for (int i = 0; i < len; i++) {
            String sRotated = rotate(s, i);
            if (isValid(sRotated)) {
                count++;
            }//if
        }//for
        return count;
    }//solution

    // 1.문자열s 회전코드
    private String rotate(String s, int x) {
        return s.substring(x) + s.substring(0, x);
    }//rotate

    // 2.괄호 유효성 검사코드
    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (!isMatching(top, c)) return false;
            }//if-else
        }//for
        return stack.isEmpty();  // 모든 열린 괄호가 닫혔는지 확인
    }//isValid

    // 괄호 짝 확인
    private boolean isMatching(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }//isMatching
}//class

