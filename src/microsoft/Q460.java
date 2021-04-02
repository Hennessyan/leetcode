package microsoft;

import java.util.HashMap;
import java.util.Map;
// LFU Cache
public class Q460 {

    class LFUCache {
        Map<Integer, Node> map;
        Map<Integer, DoubleLinkedList> fremap;
        int capacity, count, minfre;
        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.count = 0;
            this.minfre = 0;
            map = new HashMap<>();
            fremap = new HashMap<>();
        }

        public int get(int key) {
            if(map.containsKey(key)) {
                Node node = map.get(key);
                promotion(node);
                return node.val;
            }
            return -1;
        }

        public void put(int key, int value) {
            if(capacity == 0) {
                return;
            }
            if(map.containsKey(key)) {
                Node node = map.get(key);
                node.val = value;
                promotion(node);
            }else {
                if(capacity == count) {
                    DoubleLinkedList dll = fremap.get(minfre);
                    Node eldest = dll.tail.prev;
                    dll.delete(eldest);
                    if(dll.size == 0) {
                        fremap.remove(minfre);
                    }
                    map.remove(eldest.key);
                }else {
                    count++;
                }
                Node node = new Node(key, value, 0);
                minfre = 0;
                map.put(key, node);
                fremap.computeIfAbsent(minfre, x -> new DoubleLinkedList()).addFirst(node);
            }
        }
        private void promotion(Node node) {
            DoubleLinkedList dll = fremap.get(node.fre);
            dll.delete(node);
            if(dll.size == 0) {
                if(minfre == node.fre) {
                    minfre++;
                }
                fremap.remove(node.fre);
            }
            fremap.computeIfAbsent(++node.fre, x -> new DoubleLinkedList()).addFirst(node);
        }
        class DoubleLinkedList {
            Node head, tail;
            int size;
            public DoubleLinkedList() {
                size = 0;
                head = new Node(0,0,0);
                tail = new Node(0,0,0);
                head.next = tail;
                tail.prev = head;
            }
            public void addFirst(Node node) {
                node.next = head.next;
                head.next.prev = node;
                node.prev = head;
                head.next = node;
                size++;
            }
            public void delete(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
            }
        }

        class Node {
            int key, val, fre;
            Node prev, next;
            public Node(int key, int val, int fre) {
                this.key = key;
                this.val = val;
                this.fre = fre;
                prev = null;
                next = null;
            }
        }
    }
}
