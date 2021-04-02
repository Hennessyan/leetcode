package google;

import javafx.util.Pair;

import java.util.*;

// Time Based Key-Value Store
public class Q981 {

//    class TimeMap {
//        Map<String, TreeMap<Integer, String>> map;
//
//        public TimeMap() {
//            map = new HashMap<>();
//        }
//        // O(1)
//        public void set(String key, String value, int timestamp) {
//            map.computeIfAbsent(key, x -> new TreeMap<>()).put(timestamp, value);
//        }
//        // O(lgN)
//        public String get(String key, int timestamp) {
//            if(map.containsKey(key)) {
//                TreeMap<Integer, String> tmp = map.get(key);
//                Integer tkey = tmp.floorKey(timestamp);
//                return tkey == null ? "" : tmp.get(tkey);
//            }
//            return "";
//        }
//    }

    class TimeMap {
        Map<String, List<Pair<Integer, String>>> M;

        public TimeMap() {
            M = new HashMap();
        }

        public void set(String key, String value, int timestamp) {
            if (!M.containsKey(key))
                M.put(key, new ArrayList<Pair<Integer, String>>());

            M.get(key).add(new Pair(timestamp, value));
        }

        public String get(String key, int timestamp) {
            if (!M.containsKey(key)) return "";

            List<Pair<Integer, String>> A = M.get(key);
            int i = Collections.binarySearch(A, new Pair<Integer, String>(timestamp, "{"),
                    (a, b) -> Integer.compare(a.getKey(), b.getKey()));

            if (i >= 0)
                return A.get(i).getValue();
            else if (i == -1)
                return "";
            else
                return A.get(-i-2).getValue();
        }
    }
}
