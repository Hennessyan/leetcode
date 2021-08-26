package google;

import java.util.ArrayList;
import java.util.List;

// Pascal's Triangle II
public class Q119 {
    // DP : O((rowIndex+1)^2) O(rowIndex)
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        if(rowIndex < 0) {
            return list;
        }
        for(int i = 0; i <= rowIndex; i++) {
            list.add(1);
            for(int j = i - 1; j >= 1; j--) {       //  reverse order
                list.set(j, list.get(j) + list.get(j-1));
            }
        }
        return list;
    }
}
