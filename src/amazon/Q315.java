package amazon;

import java.util.*;

// Count of Smaller Numbers After Self
public class Q315 {
    // TLE -> O(n^2) O(n)
    public List<Integer> countSmaller0(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return list;
        }
        int len = nums.length;
        list.add(0, 0);
        Node root = new Node(nums[len - 1]);
        for(int i = len - 2; i >= 0; i--) {
            list.add(0, insert(root, nums[i]));
        }
        return list;
    }

    private int insert(Node root, int val) {
        // if(root == null) {
        //     return 0;
        // }
        if(root.val == val) {
            root.count++;
            return root.left_min;
        }else if(root.val < val) {
            if(root.right == null) {
                root.right = new Node(val);
                return root.count + root.left_min;
            }else {
                return root.count + root.left_min + insert(root.right, val);
            }
        }else {
            root.left_min++;
            if(root.left == null) {
                root.left = new Node(val);
                return 0;
            }else {
                return insert(root.left, val);
            }
        }
    }

    class Node {
        int val;
        int count;
        int left_min;
        Node left, right;
        public Node(int val) {
            this.val = val;
            this.count = 1;
            this.left_min = 0;
        }
    }

    //https://zxi.mytechroad.com/blog/algorithms/array/leetcode-315-count-of-smaller-numbers-after-self/
    //O(nlgn) O(n)
    public List<Integer> countSmaller1(int[] nums) {
        List<Integer> list = new LinkedList<>();
        if(nums == null || nums.length == 0) {
            return list;
        }
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        for(int i = 0; i < sorted.length; i++) {
            if(i == 0 || sorted[i] != sorted[i - 1]) {
                ranks.put(sorted[i], ++rank);
            }
        }
        BinaryIndexTree tree = new BinaryIndexTree(ranks.size());
        for(int i = nums.length - 1; i >= 0; i--) {
            list.add(0, tree.query(ranks.get(nums[i]) - 1));
            tree.update(ranks.get(nums[i]), 1);
        }
        return list;
    }
    class BinaryIndexTree {
        int[] sums;
        public BinaryIndexTree(int n) {
            sums = new int[n + 1];
        }
        public int query(int i) {
            int sum = 0;
            while(i > 0) {
                sum += sums[i];
                i -= lowBit(i);
            }
            return sum;
        }
        public void update(int i, int delta) {
            while(i < sums.length) {
                sums[i] += delta;
                i += lowBit(i);
            }
        }
        private int lowBit(int x) {
            return x & (-x);
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        SegmentTree st = new SegmentTree(2 * (max - min + 1));
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for(int i = n - 1; i >= 0; i--) {
            res.add(st.query(0, nums[i] - min - 1));
            st.update(nums[i] - min, 1);
        }
        Collections.reverse(res);
        return res;
    }
    class SegmentTree {
        int n;
        int[] arr;
        public SegmentTree(int n) {
            this.n = n;
            this.arr = new int[2 * n];
        }
        public int query(int l, int r) {
            int sum = 0;
            l += n;
            r += n;
            while(l <= r) {
                if(l % 2 == 1) {
                    sum += arr[l++];
                }
                if(r % 2 == 0) {
                    sum += arr[r--];
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }
        public void update(int i, int diff) {
            i += n;
            while(i >= 1) {
                arr[i] += diff;
                i /= 2;
            }
        }
    }
    // merge sort
    public List<Integer> countSmaller3(int[] nums) {
        int n = nums.length;
        int[] index = new int[n];
        int[] result = new int[n];
        for(int i = 0; i < n; i++) {
            index[i] = i;
        }
        sort(nums, index, result, 0, n - 1);
        List<Integer> res = new ArrayList<>();
        for(int val : result) {
            res.add(val);
        }
        return res;
    }
    private void sort(int[] nums, int[] index, int[] result, int l, int r) {
        if(l >= r) return;
        int m = l + (r - l) / 2;
        sort(nums, index, result, l, m);
        sort(nums, index, result, m + 1, r);
        merge(nums, index, result, l, m, r);
    }
    private void merge(int[] nums, int[] index, int[] result, int l, int m, int r) {
        List<Integer> tmp = new ArrayList<>();
        int i = l, j = m + 1;
        while(i <= m && j <= r) {
            if(nums[index[i]] <= nums[index[j]]) {
                result[index[i]] += j - (m + 1);
                tmp.add(index[i++]);
            } else {
                tmp.add(index[j++]);
            }
        }
        while(i <= m) {
            result[index[i]] += j - (m + 1);
            tmp.add(index[i++]);
        }
        while(j <= r) {
            tmp.add(index[j++]);
        }
        for(int val : tmp) {
            index[l++] = val;
        }
    }
}
