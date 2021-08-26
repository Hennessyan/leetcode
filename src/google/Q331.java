package google;
// Verify Preorder Serialization of a Binary Tree
// Q150
public class Q331 {
    // O(n) O(n)
    public boolean isValidSerialization(String preorder) {
        int count = 1;
        String[] nodes = preorder.split("\\,");
        for(String node : nodes) {
            if(--count < 0) return false;
            if(!node.equals("#")) count += 2;
        }
        return count == 0;
    }
    // O(n) O(1)
    public boolean isValidSerialization2(String preorder) {
        int slots = 1;

        int n = preorder.length();
        for(int i = 0; i < n; ++i) {
            if (preorder.charAt(i) == ',') {
                --slots;

                if (slots < 0) return false;
                // L20 guarantee i > 0
                if (preorder.charAt(i - 1) != '#') slots += 2;
            }
        }

        slots = (preorder.charAt(n - 1) == '#') ? slots - 1 : slots + 1;
        return slots == 0;
    }
    // O(n) O(1)

    public boolean isValidSerialization1(String preorder) {
        int count = 1, n = preorder.length();
        for(int i = 1; i < n; i++) {
            if(preorder.charAt(i) == ',') {
                // each node occupy one slot
                if(--count < 0) return false;
                if(preorder.charAt(i - 1) != '#') {
                    count += 2; // one non-end node will have two positions (two children)
                }
            }
        }
        count = preorder.charAt(n - 1) == '#' ? count - 1 : count + 1;  // last one is not ',' !
        return count == 0;                                              // no need count + 1 in L45 actually because last one must be '#' for valid tree.
    }
}
