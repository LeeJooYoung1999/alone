package FifthWeek.Practice.ReportResult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length; //총 유저수 n
        int[] result = new int[n]; //처리결과메일을 받은횟수가 담긴 배열 result
        //Set<String> reportId = new HashSet<>();  //한 유저가 같은 유저를 여러번 신고하여도 신고횟수1회로 처리하기 위해, 중복방지용 자료구조 Set이용.
        Map<String, Set<String> > Reporter = new HashMap<>();
        Map<String, Integer> Reported = new HashMap<>();
        //1.0~(n-1)까지 n회 반복하여, id_list의 원소인 userId를 HashMap인 Reporter와 Reported의 key값으로 설정.
        for (int i = 0; i < n; i++) {
            Reporter.put(id_list[i], new HashSet<>());
            Reported.put(id_list[i],0); //신고받은 횟수인 Reported의 value기본값은 0으로 설정.
        }
        //2. report["신고자 피신고자", "신고자 피신고자", ...]를 Reporter로 순회하며, 신고자와 동일한 key를 갖는 entry의 value값으로 피신고자 추가하기.
        for (String reportCase : report) {
            String[] parts = reportCase.split(" "); //공백을 기준으로 report의 원소를 분리
            String shingoja = parts[0];
            String Pshingoja = parts[1];
            Reporter.get(shingoja).add(Pshingoja);
        }
        //3. 중복방지처리가 된 Reporter를 Reported로 순회하며 Reporter의 value값인 hashset에 해당 key값과 같은 문자열이 포함된걸 발견할때마다 해당 reported객체의 value값에 1 더해주어, id별로 신고된 횟수를 카운팅해준다.
        for (Set<String> reportedUsers : Reporter.values()){ //Reporter의 value인 HashSet(= 모든 신고자들이 신고한 피신고자 목록)을 하나하나 순회하며
            for (String reportedUser : reportedUsers){  //한 유저가 신고한 모든 피신고자를 순회하며
                Reported.put(reportedUser,Reported.get(reportedUser)+1);// 각 피신고자 한명당 자기가 몇명에게 신고당했는지 기록.(reported의 value값)
            }
        }
        //4. 반환해야하는 result는 자기가 신고한 사람중에서 실제 정지된 사람이 몇명인지가 저장된 배열이므로,
        // 이때, 실제 정지되려면 신고횟수가 k이상이어야(= k명 이상의 각기 다른유저에게 신고받아야)하므로,

        //이제 모든유저를 위에서 만들어낸 검사도구로 검사한다.
        for (int i=0; i<n; i++) { //모든유저를 검사해야 하므로, n번 반복
            String user = id_list[i]; //현재검사중인 유저의 이름
            Set<String> reportedByMe = Reporter.get(user); //지금 검사중인 유저가 신고한 목록 가져오기
            for (String reportedUser : reportedByMe){ //이 유저가 신고한 각 피신고자를 순회
                if (Reported.get(reportedUser) >=k){  //그 피신고자가 만약 k번 이상 신고받았다?
                result[i]++; //지금 검사중인 유저가 받게되는 결과메일수++
                }
            }
        }
        return result;
    }//solution
}//class
