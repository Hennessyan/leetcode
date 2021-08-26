package amazon;

import common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

// Serialize and Deserialize BST
public class Q449 {

    //https://leetcode.com/problems/serialize-and-deserialize-bst/solution/
    //O(n) O(n)
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            postorder(root, sb);
            return sb.toString();
        }

        private void postorder(TreeNode root, StringBuilder sb) {
            if(root != null) {
                postorder(root.left, sb);
                postorder(root.right, sb);
                sb.append(intToString(root.val));
            }
        }
        private String intToString(int val) {
            char[] chs = new char[4];
            for(int i = 3; i >= 0; --i) {						//https://www.jianshu.com/p/524dfe838cf1
                chs[3 - i] = (char) (val >> (i * 8) & 0xff);	//0xff必须有,保证byte跟int转换的data consistency
            }													//00000000 00000000 00000000 11111111
            return String.valueOf(chs);
        }
        private int stringToInt(String s) {
            int res = 0;
            for(char c : s.toCharArray()) {
                res = (res << 8) + (int) c;	    //注意括号!!!   并且先左移,再 + 或者 |
            }
            return res;
        }
        private TreeNode helper(int min, int max, Deque<Integer> deque) {
            if(deque.isEmpty()) {
                return null;
            }
            int val = deque.getLast();
            if(val < min || val > max) {
                return null;
            }
            deque.removeLast();                     // 符合条件才删
            TreeNode node = new TreeNode(val);
            node.right = helper(val, max, deque);	//先right后left
            node.left = helper(min, val, deque);
            return node;
        }
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Deque<Integer> deque = new LinkedList<>();
            int len = data.length();
            for(int i = 0; i < len / 4; i++) {
                deque.add(stringToInt(data.substring(4 * i, 4 * i + 4)));
            }
            return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, deque);
        }
    }
}
