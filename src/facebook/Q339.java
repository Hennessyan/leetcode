package facebook;

import java.util.List;

// Nested List Weight Sum
public class Q339 {
    /* O(n) O(n)
    public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        return depthSum(nestedList, 1);
    }
    private int depthSum(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for(NestedInteger val : nestedList) {
            if(val.isInteger()) {
                sum += depth * val.getInteger();
            } else {
                sum += depthSum(val.getList(), depth + 1);
            }
        }
        return sum;
    }
    */

    /*
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);

        int depth = 1;
        int total = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger nested = queue.poll();
                if (nested.isInteger()) {
                    total += nested.getInteger() * depth;
                } else {
                    queue.addAll(nested.getList());
                }
            }
            depth++;
        }
        return total;
    }
    */
}
//interface NestedInteger {
//    // Constructor initializes an empty nested list.
//    public NestedInteger();
//
//    // Constructor initializes a single integer.
//    public NestedInteger(int value);
//
//    // @return true if this NestedInteger holds a single integer, rather than a nested list.
//    public boolean isInteger();
//
//    // @return the single integer that this NestedInteger holds, if it holds a single integer
//    // Return null if this NestedInteger holds a nested list
//    public Integer getInteger();
//
//    // Set this NestedInteger to hold a single integer.
//    public void setInteger(int value);
//
//    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
//    public void add(NestedInteger ni);
//
//    // @return the nested list that this NestedInteger holds, if it holds a nested list
//    // Return empty list if this NestedInteger holds a single integer
//    public List<NestedInteger> getList();
//}
