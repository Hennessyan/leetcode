package linkedin;
// Nested List Weight Sum
public class Q339 {
    // DFS : O(N) O(D) -
    // N : total # of nested elements [[[[[2]]]],1] -> 6
    // D : max depth of nested integer
    /*
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }
    private int depthSum(List<NestedInteger> nestedList, int d) {
        int sum = 0;
        for(NestedInteger ni : nestedList) {
            if(ni.isInteger()) {
                sum += d * ni.getInteger();
            } else {
                sum += depthSum(ni.getList(), d + 1);
            }
        }
        return sum;
    }
    */
    // BFS : O(N) O(N)
    /*
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        int sum = 0, d = 1;
        queue.addAll(nestedList);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                NestedInteger ni = queue.poll();
                if(ni.isInteger()) {
                    sum += d * ni.getInteger();
                } else {
                    queue.addAll(ni.getList());
                }
            }
            d++;
        }
        return sum;
    }
    */
}
