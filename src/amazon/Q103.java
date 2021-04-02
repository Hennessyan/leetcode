package amazon;

import common.TreeNode;

import java.util.*;

// Binary Tree Zigzag Level Order Traversal
public class Q103 {
    // DFS
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        dfs(root, res, 0);
        return res;
    }
    private void dfs(TreeNode root, List<List<Integer>> res , int i) {
        if(root == null) {
            return;
        }
        if(res.size() == i) {
            res.add(new LinkedList<>());
        }
        if(i % 2 == 0) {
            res.get(i).add(root.val);
        }else {
            res.get(i).add(0, root.val);
        }
        dfs(root.left, res, i + 1);
        dfs(root.right, res, i + 1);
    }

    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean forward = true;
        List<Integer> list;
        while(!queue.isEmpty()) {
            int size = queue.size();
            list = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(forward) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            forward = !forward;
            res.add(list);  // no need copy because of L45.
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> ans =new ArrayList<List<Integer>>();
        if(root==null)
        {
            return ans;
        }
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        q.add(root);
        int count=1,t=0,i=0;
        while(!q.isEmpty())
        {
            List<Integer> l=new ArrayList<Integer>();
            while(count!=0)
            {
                TreeNode curr =q.poll();
                l.add(curr.val);
                if(curr.left!=null)
                {
                    t++;
                    q.add(curr.left);
                }
                if(curr.right!=null)
                {
                    t++;
                    q.add(curr.right);
                }
                count--;
            }
            count+=t;
            t=0;
            if(i%2!=0)
                Collections.reverse(l);
            ans.add(l);
            i++;
        }
        return ans;
    }
}
