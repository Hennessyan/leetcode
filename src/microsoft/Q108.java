package microsoft;

import common.TreeNode;

// Convert Sorted Array to Binary Search Tree
public class Q108 {
    // O(n) O(n) / O(lgn)
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }
        return toBST(nums, 0, nums.length - 1);
    }
    private TreeNode toBST(int[] nums, int l, int r) {
        if(l > r) {
            return null;
        }
        int m = l + (r - l) / 2;    // choose left middle as root

//        if((l + r) % 2 == 1) {
//            m += 1;                 // choose right middle as root
//            //m += (int) (Math.random() * 2); // choose root randomly.
//        }


        TreeNode node = new TreeNode(nums[m]);
        node.left = toBST(nums, l, m - 1);
        node.right = toBST(nums, m + 1, r);
        return node;
    }
}
