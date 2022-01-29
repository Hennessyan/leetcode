package linkedin;
// Nested List Weight Sum II
public class Q364 {
    // DFS - O(N) O(N)
    /*
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
    // BFS - O(N) O(N) - use this method
    public int depthSumInverse1(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);
        int sum = 0, total = 0, d = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                NestedInteger ni = queue.poll();
                if(ni.isInteger()) {
                    int val = ni.getInteger();
                    sum += val;
                    total += val * d;
                } else {
                    queue.addAll(ni.getList());
                }
            }
            d++;
        }
        return d * sum - total;
    }
     */


}
