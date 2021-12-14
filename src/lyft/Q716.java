package lyft;

import java.util.Stack;

//Max Stack
public class Q716 {
}

/*method1 Two Stacks*/
//O(N) for the popMax operation, and O(1) for the other operations, where N is the number of operations performed.
//O(N), the maximum size of the stack.
class MaxStack {

    Stack<Integer> s;
    Stack<Integer> ms;

    /**
     * initialize your data structure here.
     */
    public MaxStack() {
        s = new Stack<>();
        ms = new Stack<>();
    }

    public void push(int x) {
        int max = ms.empty() ? x : ms.peek();
        s.push(x);
        ms.push(max > x ? max : x);
    }

    public int pop() {
        ms.pop();
        return s.pop();
    }

    public int top() {
        return s.peek();
    }

    public int peekMax() {
        return ms.peek();
    }

    public int popMax() {
        Stack<Integer> t = new Stack<>();
        int max = ms.peek();
        while (s.peek() != max) {
            t.push(pop());
        }
        pop();
        while (!t.empty()) {
            push(t.pop());
        }
        return max;
    }
}
/*method2 Double Linked List + TreeMap*/
//O(logN) for all operations except peek which is O(1), where N is the number of operations performed.
//Most operations involving TreeMap are O(logN).
//O(N), the size of the data structures used.
/*
class MaxStack {

    TreeMap<Integer, List<Node>> tMap;
    DoubleLinkedList dll;

    public MaxStack() {
        dll = new DoubleLinkedList();
        tMap = new TreeMap<>();
    }

    public void push(int x) {
        Node node = new Node(x);
        dll.add(node);
        tMap.computeIfAbsent(x, y -> new ArrayList<>()).add(node);
    }

    public int pop() {
        Node node = dll.head.next;
        dll.delete(node);
        List<Node> list = tMap.get(node.val);
        list.remove(list.size() - 1);   // always remove the last one hence O(lgn), list.remove(node) -> O(n)
        if(list.isEmpty()) {
            tMap.remove(node.val);
        }
        return node.val;
    }

    public int top() {
        return dll.head.next.val;
    }

    public int peekMax() {
        return tMap.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        List<Node> list = tMap.get(max);
        Node node = list.remove(list.size() - 1);
        if(list.isEmpty()) {
            tMap.remove(node.val);
        }
        dll.delete(node);
        return max;
    }

    class DoubleLinkedList {
        Node head, tail;
        public DoubleLinkedList() {
            head = new Node(0);
            tail = new Node(0);
            head.next = tail;
            tail.prev = head;
        }
        public void delete(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        public void add(Node node) {
            node.next = head.next;
            head.next.prev = node;
            node.prev = head;
            head.next = node;
        }
    }

    class Node {
        int val;
        Node prev, next;
        public Node(int val) {
            this.val = val;
        }
    }
}
*/
