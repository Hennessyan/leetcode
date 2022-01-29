package linkedin.VO;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// O(1) TC for all operations
/*
思路是用双向链表+hashmap做，双向链表是根据frequency的顺序构建的，用一个hashmap将key指向node，node存frequency以及一个set里面存所有keys，
每当increase的时候就去找对应的node，如果node.next.val = node.val + 1则将hashmap的value指向下一个node，并且更新两个node里的set，
否则就插入一个新的node并且将val设置成当前+1，然后更新hashmap和node里的set
*/
public class MinMaxQueue {

    DoubleLinkedList<Integer> dll;
    Map<Integer, Node<Integer>> map;
    public MinMaxQueue() {
        dll = new DoubleLinkedList();
        map = new HashMap<>();
    }

    public void increase(int key) {
        Node<Integer> node = map.get(key);
        if(node == null) {
            if(dll.head.next.fre != 1) {
                node = new Node(1);
                dll.addToHead(node);
            } else {
                node = dll.head.next;
            }
            node.set.add(key);
            map.put(key, node);
        } else {
            node.set.remove(key);
            int fre = node.fre;
            if(node.next.fre == fre + 1) {
                map.put(key, node.next);
                node.next.set.add(key);
            } else {
                Node cur = new Node(fre + 1);
                map.put(key, cur);
                cur.set.add(key);
                cur.next = node.next;
                node.next.prev = cur;
                cur.prev = node;
                node.next = cur;
            }

            if(node.set.isEmpty()) {
                dll.delete(node);
            }
        }
    }

    public void decrease(int key) {

    }

    public int getMax() {
        return dll.tail.prev.set.iterator().next();
    }

    public int getMin() {
        return dll.head.next.set.iterator().next();
    }
}
class DoubleLinkedList<T> {
    Node<T> head, tail;
    public DoubleLinkedList() {
        head = new Node<T>(0);
        tail = new Node<T>(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }
    public void addToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }
    public void delete(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
class Node<T> {
    int fre;
    Set<T> set;
    Node<T> prev, next;
    public Node(int fre) {
        this.fre = fre;
        this.set = new HashSet<T>();
        this.prev = null;
        this.next = null;
    }
}