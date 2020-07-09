package challenge.june;

import java.util.List;

// Maximum Distance in Arrays
public class Q624 {

    public int maxDistance(List<List<Integer>> arrays) {
        int res = 0, min = arrays.get(0).get(0);
        int max = arrays.get(0).get(arrays.get(0).size() - 1);
        for(int i = 1; i < arrays.size(); i++) {
            int l = arrays.get(i).get(0);
            int r = arrays.get(i).get(arrays.get(i).size() - 1);
            // guarantee we will not calculate res from same raw:
            res = Math.max(res, Math.max(Math.abs(r - min), Math.abs(max - l)));
            min = Math.min(min, l);
            max = Math.max(max, r);
        }
        return res;
    }
}
