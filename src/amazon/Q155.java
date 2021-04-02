package amazon;

import java.util.Stack;

// Min Stack
public class Q155 {
}

// check and use approach3 in https://leetcode.com/problems/min-stack/solution/: (below one)
//class MinStack {
//
//    private Stack<Integer> stack;
//    private Stack<int[]> minS;
//    /** initialize your data structure here. */
//    public MinStack() {
//        stack = new Stack<>();
//        minS = new Stack<>();
//    }
//
//    public void push(int val) {
//        stack.push(val);
//        if(minS.isEmpty() || minS.peek()[0] > val) {
//            minS.push(new int[]{val, 1});
//        } else if(minS.peek()[0] == val) {
//            minS.peek()[1]++;
//        }
//    }
//
//    public void pop() {
//        if(stack.pop() == minS.peek()[0]) {
//            minS.peek()[1]--;
//        }
//        if(minS.peek()[1] == 0) {
//            minS.pop();
//        }
//    }
//
//    public int top() {
//        return stack.peek();
//    }
//
//    public int getMin() {
//        return minS.peek()[0];
//    }
//}

class MinStack {
    Stack<Integer> stack;
    int min;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (min >= x) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (min == stack.pop()) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
//method2
//	class MinStack {
//
//		class Node{
//			int val;
//			int min;
//			Node next;
//			public Node(int x, int min){
//				val = x;
//				this.min = min;
//				next = null;
//			}
//		}
//		private Node head;
//	    /** initialize your data structure here. */
//	    public MinStack() {
//	        head = new Node(0,Integer.MAX_VALUE);
//	    }
//
//	    public void push(int x) {	//注意这的实现方法,不能使用n.next = head.next; head.next = n;
//	    	Node n = new Node(x, Math.min(x,head.min));	//因为无法使用head.next.min去比较求最小值
//            n.next=head;
//            head=n;
//	    }
//
//	    public void pop() {
//	        head = head.next;
//	    }
//
//	    public int top() {
//	        return head.val;
//	    }
//
//	    public int getMin() {
//	        return head.min;
//	    }
//	}