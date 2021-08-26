package amazon;
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
