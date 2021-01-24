package challenge.dec;

import common.ListNode;

import java.util.ArrayList;
import java.util.List;

// Linked List Random Node
/*
Follow up:
What if the linked list is extremely large and its length is unknown to you?
Could you solve this efficiently without using extra space?
*/

public class Q382 {

//    // fixed range: O(N) O(N)
//    private List<ListNode> list;
//    class Solution {
//
//        /** @param head The linked list's head.
//        Note that the head is guaranteed to be not null, so it contains at least one node. */
//        // O(N)
//        public Solution(ListNode head) {
//            list = new ArrayList<>();
//            while(head != null) {
//                list.add(head);
//                head = head.next;
//            }
//        }
//
//        /** Returns a random node's value. */
//        // O(1)
//        public int getRandom() {
//            int index = (int) (Math.random() * list.size());
//            return list.get(index).val;
//        }
//    }
    // https://leetcode.com/problems/linked-list-random-node/solution/
    // 1 / j <= (1 / k) * (k / j)
    // (j - 1) / j = 1 - (1 / j)
    // Reservoir Sampling - O(N) O(N)
    /*
    The reservoir sampling algorithm is intended to sample k elements from an population of unknown size.
    In our case, the k happens to be one.
     */
    class Solution {
        private ListNode head;
        /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        public Solution(ListNode head) {
            this.head = head;
        }

        /** Returns a random node's value. */
        // unknown size with constant space !!!
        public int getRandom() {
            int scope = 1, val = 0;
            ListNode tmp = this.head;
            while(tmp != null) {
                if(Math.random() < 1.0 / scope) {
                    val = tmp.val;
                }
                scope++;
                tmp = tmp.next;
            }
            return val;
        }
    }
}
