package amazon;

import java.util.*;
// Zigzag Iterator
public class Q281 {

    public class ZigzagIterator {

        List<List<Integer>> lists = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            if(v1.size() > 0) lists.add(v1);
            if(v2.size() > 0) lists.add(v2);
            for(int i = 0; i < lists.size(); i++) {
                queue.add(new int[]{i, 0});
            }
        }

        public int next() {
            int[] cur = queue.poll();
            List<Integer> v = lists.get(cur[0]);
            if(cur[1] + 1 < v.size()) {
                queue.offer(new int[]{cur[0], cur[1] + 1});
            }
            return v.get(cur[1]);
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }

    public class ZigzagIterator1 {
        Queue<Iterator<Integer>> queue;
        public ZigzagIterator1(List<Integer> v1, List<Integer> v2) {
            queue = new LinkedList<>();
            if(v1.iterator().hasNext()) queue.add(v1.iterator());
            if(v2.iterator().hasNext()) queue.add(v2.iterator());
        }

        public int next() {
            Iterator<Integer> it = queue.poll();
            int val = it.next();
            if(it.hasNext()) queue.add(it);
            return val;
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
}
