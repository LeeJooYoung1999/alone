package FifthWeek.Example.NewIdSuggestion;

import java.util.ArrayList;

public class Solution {
    public static String solution(String new_id) {
        // 1단계: 대문자 → 소문자
        new_id = new_id.toLowerCase();

        // 2단계: 허용된 문자만 남기기 (a-z, 0-9, '-', '_', '.')
        ArrayList<Character> validChars = new ArrayList<>();
        for (char c : new_id.toCharArray()) {
            if ((c >= 'a' && c <= 'z') ||
                    (c >= '0' && c <= '9') ||
                    c == '-' || c == '_' || c == '.') {
                validChars.add(c);
            }
        }

        // 3단계: 연속된 마침표를 하나로
        ArrayList<Character> noDoubleDots = new ArrayList<>();
        for (int i = 0; i < validChars.size(); i++) {
            char current = validChars.get(i);
            if (current == '.' && i > 0 && validChars.get(i - 1) == '.') {
                continue;
            }
            noDoubleDots.add(current);
        }

        // 4단계: 처음이나 끝의 마침표 제거
        if (!noDoubleDots.isEmpty() && noDoubleDots.get(0) == '.') {
            noDoubleDots.remove(0);
        }
        if (!noDoubleDots.isEmpty() && noDoubleDots.get(noDoubleDots.size() - 1) == '.') {
            noDoubleDots.remove(noDoubleDots.size() - 1);
        }

        // 5단계: 빈 문자열이면 "a" 대입
        if (noDoubleDots.isEmpty()) {
            noDoubleDots.add('a');
        }

        // 6단계: 16자 이상이면 앞에서 15자만 남기고, 끝 마침표 제거
        if (noDoubleDots.size() > 15) {
            noDoubleDots = new ArrayList<>(noDoubleDots.subList(0, 15));
            if (noDoubleDots.get(noDoubleDots.size() - 1) == '.') {
                noDoubleDots.remove(noDoubleDots.size() - 1);
            }
        }

        // 7단계: 길이가 2 이하면, 마지막 문자를 길이 3 될 때까지 반복
        while (noDoubleDots.size() < 3) {
            noDoubleDots.add(noDoubleDots.get(noDoubleDots.size() - 1));
        }

        // 문자 리스트 → 문자열 변환
        StringBuilder result = new StringBuilder();
        for (char c : noDoubleDots) {
            result.append(c);
        }

        return result.toString();
    }
}

