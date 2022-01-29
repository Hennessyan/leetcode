package linkedin;
// Valid Parenthesis String
public class Q678 {
    // Greedy : O(n) O(1)
    public boolean checkValidString(String s) {
        if(s == null || s.length() == 0) return true;
        int low = 0;    // least # of ( until now
        int high = 0;   // biggest # of ( until now
        for(char c : s.toCharArray()) {
            low += c == '(' ? 1 : -1;
            high += c != ')' ? 1 : -1;
            if(high < 0) return false;
            low = Math.max(low, 0);
        }
        return low == 0;
    }
}
