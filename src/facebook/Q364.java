package facebook;
// Nested List Weight Sum II
public class Q364 {
    /* O(n) O(n)
    Deque<Integer> deque;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        deque = new ArrayDeque<>();
        int depth = 1;
        dfs(nestedList);
        int sum = 0;
        while(!deque.isEmpty()) {
            sum += depth * deque.pollLast();
            depth++;
        }
        return sum;
    }
    private void dfs(List<NestedInteger> nestedList) {
        int total = 0;
        List<NestedInteger> nested = new ArrayList();
        for(NestedInteger val : nestedList) {
            if(val.isInteger()) {
                total += val.getInteger();
            } else {
                nested.addAll(val.getList());
            }
        }
        deque.addLast(total);
        if(nested.size() != 0) {
            dfs(nested);
        }
    }
     */

    /* O(n) O(n)
    List<Integer> cache;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        cache = new ArrayList<>();
        int sum = 0;
        dfs(nestedList, 1);
        for(int i = 0; i < cache.size(); i++) {
            sum += (cache.size() - i) * cache.get(i);
        }
        return sum;
    }
    private void dfs(List<NestedInteger> nestedList, int depth) {
        if(cache.size() < depth) {
            cache.add(0);
        }
        int sum = 0;
        for(NestedInteger val : nestedList) {
            if(val.isInteger()) {
                sum += val.getInteger();
            } else {
                dfs(val.getList(), depth + 1);
            }
        }
        cache.set(depth - 1, sum + cache.get(depth - 1));
    }
     */
}
