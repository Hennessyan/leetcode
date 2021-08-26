package facebook;

import com.sun.tools.javac.util.List;

import java.util.Map;
import java.util.Set;

// Valid Number
// todo : Q1411(H) Number of Ways to Paint N × 3 Grid
public class Q65 {

    public boolean isNumber0(String s) {
        if(s == null || s.length() == 0) {
            return false;
        }
        boolean numberSeen = false;
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberAfterE = false;
        s = s.trim();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c >= '0' && c <= '9') {
                numberSeen = true;
                numberAfterE = true;
            }else if(c == '.') {
                if(pointSeen || eSeen) {
                    return false;
                }
                pointSeen = true;
            }else if(c == 'e' || c == 'E') {
                if(!numberSeen || eSeen) {
                    return false;
                }
                eSeen = true;
                numberAfterE = false;
            }else if(c == '-' || c == '+') {
                if(i > 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            }else {
                return false;
            }
        }
        return numberSeen && numberAfterE;
    }
    // O(n) O(1)
    public boolean isNumber1(String s) {
        boolean seenDigit = false;
        boolean seenExponent = false;
        boolean seenDot = false;

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (Character.isDigit(curr)) {
                seenDigit = true;
            } else if (curr == '+' || curr == '-') {
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (curr == 'e' || curr == 'E') {
                if (seenExponent || !seenDigit) {
                    return false;
                }
                seenExponent = true;
                seenDigit = false;
            } else if (curr == '.') {
                if (seenDot || seenExponent) {
                    return false;
                }
                seenDot = true;
            } else {
                return false;
            }
        }

        return seenDigit;
    }
    // Java 9
//    private static final List<Map<String, Integer>> DFA = List.of(
//            Map.of("digit", 1, "sign", 2, "dot", 3),
//            Map.of("digit", 1, "dot", 4, "exponent", 5),
//            Map.of("digit", 1, "dot", 3),
//            Map.of("digit", 4),
//            Map.of("digit", 4, "exponent", 5),
//            Map.of("sign", 6, "digit", 7),
//            Map.of("digit", 7),
//            Map.of("digit", 7)
//    );
//    private static final Set<Integer> validStates = Set.of(1, 4, 7);
//    public boolean isNumber(String s) {
//        int state = 0;
//        String group = "";
//        for(char c : s.toCharArray()) {
//            if(Character.isDigit(c)) {
//                group = "digit";
//            } else if(c == '-' || c == '+') {
//                group = "sign";
//            } else if(c == 'e' || c == 'E') {
//                group = "exponent";
//            } else if(c == '.') {
//                group = "dot";
//            } else return false;
//
//            if(!DFA.get(state).containsKey(group)) {
//                return false;
//            }
//            state = DFA.get(state).get(group);
//        }
//        return validStates.contains(state);
//    }
}
