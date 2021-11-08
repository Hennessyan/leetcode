package amazon;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// Construct Binary Tree from Preorder and Postorder Traversal
// Q105, Q106
public class Q889 {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int len = pre.length;
        int m = 0, n = 0;
        TreeNode root = new TreeNode(pre[m++]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prev = null, child = null;
        while(m < len) {
            while(!stack.isEmpty() && stack.peek().val == post[n]) {
                n++;
                prev = stack.pop();
            }
            child = new TreeNode(pre[m++]);
            if(prev == null) {
                stack.peek().left = child;
            } else {
                stack.peek().right = child;
            }
            stack.push(child);
            prev = null;
        }
        return root;
    }

    Map<Integer, Integer> map;
    int index = 0;
    public TreeNode constructFromPrePost1(int[] pre, int[] post) {
        map = new HashMap<>();
        int n = post.length;
        for(int i = 0; i < n; i++) {
            map.put(post[i], i);
        }
        return build(pre, 0, n - 1);
    }
    private TreeNode build(int[] pre, int l, int r) {
        if(l > r) return null;
        TreeNode node = new TreeNode(pre[index++]);
        if(l == r) return node;
        int tmp = map.get(pre[index]);
        node.left = build(pre, l, tmp);
        node.right = build(pre, tmp + 1, r - 1);
        return node;
    }
    // 2 1 3
    // 1 3 2
}
