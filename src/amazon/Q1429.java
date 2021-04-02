package amazon;

import java.util.HashMap;
import java.util.Map;

// First Unique Number
public class Q1429 {

    class FirstUnique {

        DoubleLinkedList dll;
        Map<Integer, Node> map;
        public FirstUnique(int[] nums) {
            dll = new DoubleLinkedList();
            map = new HashMap<>();
            for(int num : nums) {
                add(num);
            }
        }

        public int showFirstUnique() {
            if(dll.head.next != dll.tail) {
                return dll.head.next.val;
            }
            return -1;
        }

        public void add(int value) {
            map.putIfAbsent(value, new Node(value));
            Node node =  map.get(value);
            if(node.fre == 0) {
                dll.insertToTail(node);
            } else if(node.fre == 1) {
                dll.delete(node);
            }
            node.fre++;
        }

        class DoubleLinkedList {
            Node head, tail;
            public DoubleLinkedList() {
                head = new Node(-1);
                tail = new Node(-1);
                head.next = tail;
                tail.prev = head;
            }
            public void insertToTail(Node node) {
                node.next = tail;
                tail.prev.next = node;
                node.prev = tail.prev;
                tail.prev = node;
            }
            public void delete(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }
        class Node {
            int val;
            int fre;
            Node prev, next;
            public Node(int val) {
                this.val = val;
                this.fre = 0;
                this.prev = null;
                this.next = null;
            }
        }
    }

//    class FirstUnique {
//
//        Map<Integer, Boolean> isUnique;
//        Queue<Integer> queue;
//        public FirstUnique(int[] nums) {
//            queue = new LinkedList<>();
//            isUnique = new HashMap<>();
//            for(int num : nums) {
//                this.add(num);
//            }
//        }
//        // amortized to O(1)
//        public int showFirstUnique() {
//            while(!queue.isEmpty() && !isUnique.get(queue.peek())) {
//                queue.poll();
//            }
//            return queue.isEmpty() ? -1 : queue.peek();
//        }
//
//        public void add(int value) {
//            if(!isUnique.containsKey(value)) {
//                isUnique.put(value, true);
//                queue.add(value);
//            } else {
//                isUnique.put(value, false);
//            }
//        }
//    }

//    class FirstUnique {
//
//        private Set<Integer> setQueue = new LinkedHashSet<>();
//        private Map<Integer, Boolean> isUnique = new HashMap<>();
//
//        public FirstUnique(int[] nums) {
//            for (int num : nums) {
//                this.add(num);
//            }
//        }
//
//        public int showFirstUnique() {
//            if (!setQueue.isEmpty()) {
//                return setQueue.iterator().next();
//            }
//            return -1;
//        }
//
//        public void add(int value) {
//
//            if (!isUnique.containsKey(value)) {
//                isUnique.put(value, true);
//                setQueue.add(value);
//
//            } else if (isUnique.get(value)) {
//                isUnique.put(value, false);
//                setQueue.remove(value);
//            }
//        }
//    }
}
