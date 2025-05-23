package FourthWeek.InfectedFolder;
import java.util.*;
public class Solution {
    public String solution (String[][]folders, String p, String q){
        String answer = "";

        return answer;
    }//solution
    public class treeNode {
        String data;
        List<treeNode> children; //List 자료구조를 사용하여 자식노드목록을 저장할것.
        public treeNode(String data) {
            this.data = data;
            this.children = new LinkedList<>();
        }
        public void addChild(treeNode child) {
            this.children.add(child);
        }
        public List<treeNode> getChildren() {
            return this.children;
        }


    }
}//class
