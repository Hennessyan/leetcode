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
}
