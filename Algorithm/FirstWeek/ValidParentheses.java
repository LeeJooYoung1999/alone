package FirstWeek;
import java.util.*;
public class ValidParentheses {
    public boolean isValid(String s) {
        // 괄호 짝 저장 (여는 괄호 기준)
        Map<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put('(', ')');
        bracketMap.put('{', '}');
        bracketMap.put('[', ']');

        // 스택 생성
        Stack<Character> stack = new Stack<>();

        // 문자열s를 왼쪽부터 오른쪽으로 순회
        for (char c : s.toCharArray()) {
            if (bracketMap.containsKey(c)) {
                // 감지된것이 여는 괄호면 스택에 push
                stack.push(c);
            } else {
                // 감지된것이 닫는 괄호인데 스택이 비었으면 false
                if (stack.isEmpty()) return false;

                // 짝이 맞는지 확인
                char open = stack.pop();
                if (bracketMap.get(open) != c) {
                    return false;
                }//if
            }//else
        }//향상된 for

        // 다 끝났을 때 스택이 비어 있어야 유효한 문자열
        return stack.isEmpty();
    }//boolean
}//클래스
