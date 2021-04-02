package facebook;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Average of Levels in Binary Tree
public class Q637 {
    // O(n) O(n)
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> sum = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        average(root, 0, sum, count);
        for(int i = 0; i < sum.size(); i++) {
            sum.set(i, sum.get(i) / count.get(i));
        }
        return sum;
    }
    private void average(TreeNode root, int index, List<Double> sum, List<Integer> count){
        if(root != null) {
            if(sum.size() == index) {
                sum.add(root.val * 1.0);
                count.add(1);
            } else {
                sum.set(index, sum.get(index) + root.val);
                count.set(index, count.get(index) + 1);
            }
            average(root.left, index + 1, sum, count);
            average(root.right, index + 1, sum, count);

        }
    }

    public List < Double > averageOfLevels1(TreeNode root) {
        List < Double > res = new ArrayList < > ();
        Queue< TreeNode > queue = new LinkedList< >();
        queue.add(root);
        while (!queue.isEmpty()) {
            long sum = 0, count = 0;    // use long for sum to avoid outbound
            Queue < TreeNode > temp = new LinkedList < > ();
            while (!queue.isEmpty()) {
                TreeNode n = queue.remove();
                sum += n.val;
                count++;
                if (n.left != null)
                    temp.add(n.left);
                if (n.right != null)
                    temp.add(n.right);
            }
            queue = temp;
            res.add(sum * 1.0 / count);
        }
        return res;
    }
}
