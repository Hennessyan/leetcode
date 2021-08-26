package google;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Rank Transform of an Array
public class Q1331 {

    public static void main(String[] args) {
        Q1331 q = new Q1331();
        int[] arr = {37,12,28,9,100,56,80,5,12};
        System.out.println(Arrays.toString(q.arrayRankTransform(arr))); //5,3,4,2,8,6,7,1,3
    }

    public int[] arrayRankTransform(int[] arr) {
        int[] tmp = Arrays.copyOf(arr, arr.length);
        Arrays.sort(tmp);
        int index = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int t : tmp) {
            if(!map.containsKey(t)) {   // index will increase if use putIfAbsent, wrong !
                map.put(t, index++);
            }
        }
        for(int i = 0; i < tmp.length; i++) {
            tmp[i] = map.get(arr[i]);
        }
        return tmp;
    }
}
