package SecondWeek;
import java.util.*;
public class StringTransform {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        //변환 불가능한 경우(target이 words에 없는경우.)
        if (!Arrays.asList(words).contains(target)) return 0;

        //방문여부 확인하여, 중복확인방지
        boolean[] visited = new boolean[words.length];
        //BFS구동용 Queue정의
        Queue<WordStep> queue = new LinkedList<>();
        queue.offer(new WordStep(begin, 0));

        //BFS탐색
        while (!queue.isEmpty()) {
            WordStep current = queue.poll();

            if (current.word.equals(target)) { //current.word가 target과 동일해지면 즉시 반환
                return current.step;
            }//if

            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && canTransform(current.word, words[i])) {
                    visited[i] = true;
                    queue.offer(new WordStep(words[i], current.step + 1));
                }//if
            }//for
        }//while

        return 0;
    }//sol
    // 두 단어가 한 글자만 다른지 판별하는 메소드 정의
    private boolean canTransform(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }

    // 큐에 넣을 단어와 단계 저장용 클래스
    static class WordStep {
        String word;
        int step;

        WordStep(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }
}//class Solution
