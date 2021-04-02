package amazon;
// Find the Celebrity
public class Q277 {
    /*
    method1 : O(n^2) O(1)
    public int findCelebrity(int n) {
        for(int i = 0; i < n; i++) {
            boolean celebrity = true;
            for(int j = 0; j < n; j++) {
                if(i == j || (knows(j, i) && !knows(i, j))) {
                    continue;
                }
                celebrity = false;
                break;
            }
            if(celebrity) {
                return i;
            }
        }
        return -1;
    }
    method2 : O(n) O(1)
    public int findCelebrity(int n) {
        int celebrity = 0;
        for(int i = 1; i < n; i++) {
            if(knows(celebrity, i)) {
                celebrity = i;
            }
        }

        for(int j = 0; j < n; j++) {
            if(celebrity == j) {
                continue;
            }
            if(knows(celebrity, j) || !knows(j, celebrity)) {
                return -1;
            }
        }
        return celebrity;
    }

    Map<Integer, Boolean> cache;
    int n;
    @Override
    public boolean knows(int a, int b) {
        int key = a * n + b;
        if(!cache.containsKey(key)) {
            cache.put(key, super.knows(a, b));  // be careful !! super.knows(a,b), otherwise StackOverFlow
        }
        return cache.get(key);
    }

    public int findCelebrity(int n) {
        this.n = n;
        cache = new HashMap<>();
        int celebrity = 0;
        for(int i = 1; i < n; i++) {
            if(knows(celebrity, i)) {
                celebrity = i;
            }
        }
        if(isCelebrity(celebrity)) {
            return celebrity;
        }
        return -1;
    }
    private boolean isCelebrity(int i) {
        for(int j = 0; j < this.n; j++) {
            if(i == j) {
                continue;
            }
            if(knows(i, j) || !knows(j, i)) {
                return false;
            }
        }
        return true;
    }
    */
}
