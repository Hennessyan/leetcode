package google;

import java.util.HashMap;
import java.util.Map;

//LRU Cache
public class Q146 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Q146 q = new Q146();
        LRUCache cache = q.new LRUCache(2); /* capacity */

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
    /*method1*/
    //https://leetcode.com/problems/lru-cache/discuss/46055/Probably-the-%22best%22-Java-solution-extend-LinkedHashMap
    //当选择了true以后,属于access order,每次访问过的内容会加到双链表的尾端,
    //从而根据重写的removeEldestEntry()方法来决定何时删除替代最早未被使用的元素（lru）
//	class LRUCache {
//
//		Map<Integer, Integer> map;
//	    public LRUCache(int capacity) {
//	        map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true){
//	        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
//	        	return map.size() > capacity;
//	        }};
//	    }
//
//	    public int get(int key) {
//	        return map.getOrDefault(key, -1);
//	    }
//
//	    public void put(int key, int value) {
//	        map.put(key, value);
//	    }
//	}
    /*method2 hashmap + double linked list*/
    class Node{
        int key;
        int val;
        Node pre, next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    class LRUCache{
        Map<Integer, Node> map;
        int capacity, count;
        Node head, tail;
        public LRUCache(int capacity){
            map = new HashMap<>();
            this.capacity = capacity;
            this.count = 0;
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
            head.pre = null;
            tail.next = null;
        }
        public void deleteNode(Node node){
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        public void addToHead(Node node){
            node.next = head.next;
            head.next.pre = node;
            node.pre = head;
            head.next = node;
        }
        public int get(int key){
            if(map.get(key) != null){
                Node node = map.get(key);
                deleteNode(node);
                addToHead(node);
                return node.val;
            }
            return -1;
        }
        public void put(int key, int val){
            if(map.get(key) != null){
                Node node = map.get(key);
                node.val = val;
                deleteNode(node);
                addToHead(node);
            }else{
                Node node = new Node(key, val);
                map.put(key, node);
                if(count < capacity){
                    count++;
                    addToHead(node);
                }else{
                    map.remove(tail.pre.key);
                    deleteNode(tail.pre);
                    addToHead(node);
                }
            }
        }
    }
}
