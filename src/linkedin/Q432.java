package linkedin;

import java.util.*;

// All O`one Data Structure
public class Q432 {
    /* just inc / dec one, no need to use TreeMap.
    class AllOne {

        Map<String, Node> fmap;
        TreeMap<Integer, DoubleLinkedList> nmap;
        public AllOne() {
            fmap = new HashMap<>();
            nmap = new TreeMap<>();
        }

        public void inc(String key) {
            Node node;
            if(!fmap.containsKey(key)) {
                node = new Node(key, 0);
                fmap.put(key, node);
            } else {
                node = fmap.get(key);
                remove(node);
            }

            node.fre++;
            nmap.computeIfAbsent(node.fre, x -> new DoubleLinkedList()).addToTail(node);
        }

        public void dec(String key) {
            Node node = fmap.get(key);
            remove(node);
            if(--node.fre == 0) {
                fmap.remove(key);
            } else {
                nmap.computeIfAbsent(node.fre, x -> new DoubleLinkedList()).addToTail(node);
            }
        }

        private void remove(Node node) {
            DoubleLinkedList dll = nmap.get(node.fre);
            dll.delete(node);
            if(dll.size == 0) nmap.remove(node.fre);
        }

        public String getMaxKey() {
            if(nmap.isEmpty()) return "";
            return nmap.get(nmap.lastKey()).top();
        }

        public String getMinKey() {
            if(nmap.isEmpty()) return "";
            return nmap.get(nmap.firstKey()).top();
        }
    }
    class DoubleLinkedList {
        Node head, tail;
        int size;
        public DoubleLinkedList() {
            this.head = new Node("", 0);
            this.tail = new Node("", 0);
            this.size = 0;
            head.next = tail;
            tail.prev = head;
        }
        public String top() {
            return head.next.key;
        }
        public void addToTail(Node node) {
            size++;
            node.next = tail;
            node.prev = tail.prev;
            tail.prev.next = node;
            tail.prev = node;
        }
        public void delete(Node node) {
            size--;
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
    class Node {
        String key;
        int fre;
        Node prev, next;
        public Node(String key, int fre) {
            this.key = key;
            this.fre = fre;
            this.prev = null;
            this.next = null;
        }
    }
    */
    /*
    class AllOne {

        Map<String, Node> nmap;
        DoubleLinkedList dll;
        public AllOne() {
            nmap = new HashMap<>();
            dll = new DoubleLinkedList();
        }

        public void inc(String key) {
            Node node = nmap.get(key);
            if(node == null) {
                node = new Node(key, 1);
                nmap.put(key, node);
                dll.link(dll.head, node);
            } else {
                node.count++;
                dll.unlink(node);
                dll.addBack(node);
            }
        }

        public void dec(String key) {
            Node node = nmap.get(key);
            dll.unlink(node);
            if(--node.count == 0) {
                nmap.remove(key);
            } else {
                dll.addFront(node);
            }
        }

        public String getMaxKey() {
            return nmap.isEmpty() ? "" : dll.tail.prev.key;
        }

        public String getMinKey() {
            return nmap.isEmpty() ? "" : dll.head.next.key;
        }
    }
    class Node {
        String key;
        int count;
        Node next, prev;
        public Node(String key, int count) {
            this.key = key;
            this.count = count;
            this.next = null;
            this.prev = null;
        }
    }
    class DoubleLinkedList {
        Node head, tail;
        public DoubleLinkedList() {
            this.head = new Node("", 0);
            this.tail = new Node("", Integer.MAX_VALUE);
            head.next = tail;
            tail.prev = head;
        }

        public void unlink(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        public void link(Node prev, Node node) {
            node.prev = prev;
            node.next = prev.next;
            prev.next.prev = node;
            prev.next = node;
        }
        public void addBack(Node node) {
            Node next = node.next;
            while(next != null && node.count > next.count) {
                next = next.next;
            }
            link(next.prev, node);
        }
        public void addFront(Node node) {
            Node prev = node.prev;
            while(prev != null && node.count < prev.count) {
                prev = prev.prev;
            }
            link(prev, node);
        }
    }
    */
    class AllOne {

        DoubleLinkedList dll;
        Map<String, Node> nmap;
        public AllOne() {
            dll = new DoubleLinkedList();
            nmap = new HashMap<>();
        }

        public void inc(String key) {
            if(!nmap.containsKey(key)) {
                Node node = dll.first();
                if(node.fre == 1) {
                    node.set.add(key);
                } else {
                    node = new Node(1);
                    node.set.add(key);
                    dll.link(dll.head, node);
                }
                nmap.put(key, node);
            } else {
                Node node = nmap.get(key);
                Set<String> set = node.set;
                set.remove(key);
                Node next = node.next;
                if(next.fre > node.fre + 1) {
                    next = new Node(node.fre + 1);
                    dll.link(node, next);
                }
                next.set.add(key);
                nmap.put(key, next);
                if(set.isEmpty()) {
                    dll.delete(node);
                }
            }
        }

        public void dec(String key) {
            if(!nmap.containsKey(key)) return;
            Node node = nmap.get(key);
            Set<String> set = node.set;
            set.remove(key);
            int fre = node.fre - 1;
            if(fre == 0) {
                nmap.remove(key);
            } else {
                Node pre = node.prev;
                if(pre.fre < fre) {
                    pre = new Node(fre);
                    dll.link(node.prev, pre);
                }
                pre.set.add(key);
                nmap.put(key, pre);
            }
            if(set.isEmpty()) {
                dll.delete(node);
            }
        }

        public String getMaxKey() {
            if(dll.last() == dll.head) return "";
            return dll.last().set.iterator().next();
        }

        public String getMinKey() {
            if(dll.first() == dll.tail) return "";
            return dll.first().set.iterator().next();
        }
    }
    class DoubleLinkedList {
        Node head, tail;
        public DoubleLinkedList() {
            this.head = new Node(0);
            this.tail = new Node(Integer.MAX_VALUE);
            head.next = tail;
            tail.prev = head;
        }
        public Node first() {
            return head.next;
        }
        public Node last() {
            return tail.prev;
        }
        public void delete(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        public void link(Node pre, Node cur) {
            cur.next = pre.next;
            pre.next.prev = cur;
            cur.prev = pre;
            pre.next = cur;
        }
    }
    class Node {
        int fre;
        Set<String> set;
        Node prev, next;
        public Node(int fre) {
            this.fre = fre;
            this.set = new HashSet<>();
            this.prev = null;
            this.next = null;
        }
    }
}
