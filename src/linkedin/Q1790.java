package linkedin;

import java.util.ArrayList;
import java.util.List;

// Check if One String Swap Can Make Strings Equal
public class Q1790 {

    public boolean areAlmostEqual(String s1, String s2) {
        if(s1.equals(s2)) return true;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            if(c1 != c2) {
                list.add(i);
                if(list.size() > 2) return false;
            }
        }
        int size = list.size();
        return size == 0 || (size == 2 && s1.charAt(list.get(0)) == s2.charAt(list.get(1))
                && s1.charAt(list.get(1)) == s2.charAt(list.get(0)));
    }
}
