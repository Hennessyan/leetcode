package google;

import java.util.ArrayList;
import java.util.List;

// Pascal's Triangle
public class Q118 {
    // DP : O(numRows^2) O(numRows^2)
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows <= 0) {
            return res;
        }
        for(int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i) {
                    list.add(1);
                } else {
                    int a = res.get(i - 1).get(j - 1);
                    int b = res.get(i - 1).get(j);
                    list.add(a + b);
                }
            }
            res.add(list);
        }
        return res;
    }
}
