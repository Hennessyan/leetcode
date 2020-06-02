package microsoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

// Max Stack
public class Q716 {
    /*method1 Two Stacks*/
    //O(N) for the popMax operation, and O(1) for the other operations, where N is the number of operations performed.
    //O(N), the maximum size of the stack.
    class MaxStack {
        Stack<Integer> stack;
        Stack<Integer> mStack;
        /** initialize your data structure here. */
        public MaxStack() {
            stack = new Stack<>();
            mStack = new Stack<>();
        }

        public void push(int x) {
            int max = mStack.isEmpty() ? x : mStack.peek();
            stack.push(x);
            max = x > max ? x : max;
            mStack.push(max);
        }

        public int pop() {
            mStack.pop();
            return stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int peekMax() {
            return mStack.peek();
        }

        public int popMax() {
            Stack<Integer> tmp = new Stack<>();
            int max = mStack.peek();
            while(stack.peek() != max){
                tmp.push(pop());	//注意这里是pop()
            }
            pop();
            while(!tmp.isEmpty()){
                push(tmp.pop());	//注意这里是push()
            }
            return max;
        }
    }

    /*method2 Double Linked List + TreeMap*/
//O(logN) for all operations except peek which is O(1), where N is the number of operations performed.
//Most operations involving TreeMap are O(logN).
//O(N), the size of the data structures used.
    class MaxStack2 {
        TreeMap<Integer, List<Node>> map;
        DoubleLinkedList dll;
        /** initialize your data structure here. */
        public MaxStack2() {
            map = new TreeMap<>();
            dll = new DoubleLinkedList();
        }

        public void push(int x) {
            Node node = dll.add(x);
            if(!map.containsKey(x)){
                map.put(x, new ArrayList<>());
            }
            map.get(x).add(node);
        }

        public int pop() {
            int val = dll.pop();
            List<Node> list = map.get(val);
            list.remove(list.size() - 1);
            if(list.isEmpty()){
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
            if(list.isEmpty()){
                map.remove(max);
            }
            return max;
        }
        class DoubleLinkedList{
            Node head, tail;
            public DoubleLinkedList(){
                head = new Node(0);
                tail = new Node(0);
                head.next = tail;
                tail.prev = head;
            }
            public Node add(int val){
                Node node = new Node(val);
                node.next = tail;
                node.prev = tail.prev;
                tail.prev = tail.prev.next = node;
                return node;
            }
            public int pop(){
                return unlink(tail.prev).val;
            }
            public Node unlink(Node node){
                node.prev.next = node.next;
                node.next.prev = node.prev;
                return node;
            }
            public int peek(){
                return tail.prev.val;
            }
        }
        class Node{
            int val;
            Node next, prev;
            public Node(int x){
                val = x;
            }
        }
    }

}
