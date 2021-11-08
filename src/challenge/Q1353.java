package challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

// Maximum Number of Events That Can Be Attended
// Q1751(H)
public class Q1353 {
    // 根据end date从小打到排序，因为我们肯定是deadline越近的先去做.
    // 并且我们肯定是越早做越好,才能避免后来一些未知的情况.
    /* 如果根据start date排序,下边这个问题就无法做第三个event【3，3】
            1 2
            1 2
                3
            1 2 3 4 5
            1 2 3 4 5
     */
    // 使用ARRAY来记录是否已经使用就好,SET / HASH TABLE太慢.
    // O(1e10) O(1e5) - TLE
    public int maxEvents0(int[][] events) {
        Arrays.sort(events, (e1, e2) -> e1[1] - e2[1]);
        int[] seen = new int[100001];
        int ans = 0;
        for(int[] e : events) {
            for(int i = e[0]; i <= e[1]; i++) {
                if(seen[i] == 1) {
                    continue;
                }
                seen[i]++;
                ans++;
                break;
            }
        }
        return ans;
    }
    // O(1e5 * lg 1e5) O(1e5)
    //【【1，2】，【1，2】，【1，6】，【1，2】】 =》 RETURN 3
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (e1, e2) -> e1[1] - e2[1]);
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int[] e : events) {
            min = Math.min(min, e[0]);
            max = Math.max(max, e[1]);
        }
        // 如果不使用temp直接去给TREESET中加,就会O(nlgn) 现在只是O(n)
        List<Integer> temp = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            temp.add(i);
        }
        TreeSet<Integer> set = new TreeSet<>(temp);
        int ans = 0;

        for(int[] e : events) {
            Integer firstAvailableDay = set.ceiling(e[0]);
            if (firstAvailableDay != null && firstAvailableDay <= e[1]) {   //注意firstAvailableDay <= e[1]
                ans++;
                set.remove(firstAvailableDay);
            }
        }
        return ans;
    }
    //
    class SegmentTreeNode {

        int start, end;
        SegmentTreeNode left, right;
        int val;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            left = null;
            right = null;
            val = 0;
        }
    }

    SegmentTreeNode root;
    public int maxEvents2(int[][] events) {
        if (events == null || events.length == 0)
            return 0;

        Arrays.sort(events, (a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        int lastDay = events[events.length-1][1];
        int firstDay = Integer.MAX_VALUE;
        for (int i = 0; i < events.length; i++) {
            firstDay = Math.min(firstDay, events[i][0]);
        }

        root = buildSegmentTree(firstDay, lastDay);

        int count = 0;
        for (int[] event: events) {
            int earliestDay = query(root, event[0], event[1]);
            if (earliestDay != Integer.MAX_VALUE) {
                count++;
                update(root, earliestDay);
            }

        }
        return count;
    }

    private SegmentTreeNode buildSegmentTree(int start, int end) {
        if (start > end)
            return null;
        SegmentTreeNode node = new SegmentTreeNode(start, end);
        node.val = start;
        if (start != end) {         // don't forget this if condition !!!
            int mid = start + (end - start)/2;
            node.left = buildSegmentTree(start, mid);
            node.right = buildSegmentTree(mid+1, end);

        }
        return node;
    }

    private void update(SegmentTreeNode curr, int lastDay) {
        if (curr.start == curr.end) {
            curr.val = Integer.MAX_VALUE;
        }
        else {
            int mid = curr.start + (curr.end - curr.start)/2;

            if (mid >= lastDay) {
                update(curr.left, lastDay);
            }
            else {
                update(curr.right, lastDay);
            }
            curr.val = Math.min(curr.left.val, curr.right.val); //有可能LEFT全被用过了,这时MIN是在RIGHT的.
        }
    }


    private int query(SegmentTreeNode curr, int left, int right) {
        if (curr.start == left && curr.end == right) {
            return curr.val;
        }

        int mid = curr.start + (curr.end - curr.start)/2;

        if (mid >= right) {
            return query(curr.left, left, right);
        }
        else if (mid < left) {
            return query(curr.right, left, right);
        }
        else
            return Math.min(query(curr.left, left, mid), query(curr.right, mid+1, right));
    }
}
