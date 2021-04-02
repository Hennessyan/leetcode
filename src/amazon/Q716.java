package amazon;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

// Max Stack
public class Q716 {
/*method2 Double Linked List + TreeMap*/
//O(logN) for all operations except peek which is O(1), where N is the number of operations performed.
//Most operations involving TreeMap are O(logN).
//O(N), the size of the data structures used.
    class MaxStack {
        TreeMap<Integer, List<Node>> map;
        DoubleLinkedList dll;
        /** initialize your data structure here. */
        public MaxStack() {
            map = new TreeMap<>();
            dll = new DoubleLinkedList();
        }

        public void push(int x) {
            Node node = dll.add(x);
            map.computeIfAbsent(x, y -> new LinkedList<>()).add(node);
        }

        public int pop() {
            int val = dll.pop();
            List<Node> list = map.get(val);
            list.remove(list.size() - 1);
            if(list.isEmpty()) {
                map.remove(val);
            }
            return val;
        }

        public int top() {
            return dll.peek();
        }

        public int peekMax() {
            return map.lastKey();
        }

        public int popMax() {
            int max = peekMax();
            List<Node> list = map.get(max);
            Node node = list.remove(list.size() - 1);
            dll.unlink(node);
            if(list.isEmpty()) {
                map.remove(max);
            }
            return max;
        }
    }
    class DoubleLinkedList {
        Node head, tail;
        public DoubleLinkedList() {
            head = new Node(0);
            tail = new Node(0);
            head.next = tail;
            tail.prev = head;
        }
        public Node add(int val) {
            Node node = new Node(val);
            node.next = tail;
            node.prev = tail.prev;
            tail.prev = node;
            node.prev.next = node;
            return node;
        }
        public int pop() {
            return unlink(tail.prev).val;
        }
        public Node unlink(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node;
        }
        public int peek() {
            return tail.prev.val;
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