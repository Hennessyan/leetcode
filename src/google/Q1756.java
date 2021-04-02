package google;
// Design Most Recently Used Queue
public class Q1756 {
    /**
     * class MRUQueue {
     *     int n;
     *     int m;
     *     Node[] skipNodes;
     *     Node head;
     *     public MRUQueue(int n) {
     *         this.n = n;
     *         this.m = (int) Math.sqrt(n); // not correct if using sqrt.
     *         if(this.m == 1) {
     *             this.m = 2;
     *         }
     *         this.skipNodes = new Node[n / m];
     *         this.head = new Node(0);
     *         int j = 1;
     *         Node cur = head;
     *         int index = 0;
     *         for(int i = n; i > 0; i--, j++) {
     *             cur.next = new Node(i);
     *             cur.next.prev = cur;
     *             if(j == this.m) {
     *                 skipNodes[index++] = cur.next;
     *                 j = 0;
     *             }
     *             cur = cur.next;
     *         }
     *     }
     *
     *     public int fetch(int k) {
     *         int index = this.n - k + 1;
     *         Node cur = head;
     *         int i = 0;
     *         while(index >= this.m) {
     *             index -= this.m;
     *             cur = skipNodes[i];
     *             skipNodes[i++] = cur.prev;
     *         }
     *         while(index > 0) {
     *             cur = cur.next;
     *             index--;
     *         }
     *
     *         cur.prev.next = cur.next;
     *         if(cur.next != null) {
     *             cur.next.prev = cur.prev;
     *         }
     *         cur.next = head.next;
     *         if(head.next != null) {
     *             head.next.prev = cur;
     *         }
     *         cur.prev = head;
     *         head.next = cur;
     *
     *         return cur.val;
     *     }
     *     class Node {
     *         Node prev, next;
     *         int val;
     *         public Node(int val) {
     *             this.val = val;
     *             this.prev = null;
     *             this.next = null;
     *         }
     *     }
     * }
     */
    //https://leetcode.com/problems/design-most-recently-used-queue/discuss/1065076/Java-Sqrt-Decomposition-Short-Doubly-Linked-List
}
