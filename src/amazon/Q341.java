package amazon;
// Flatten Nested List Iterator
public class Q341 {
    // D - maximum depth of the nested list, N - total # of integers, L - total # of lists
    // SC - O(D)
    /*
    public class NestedIterator implements Iterator<Integer> {
        Stack<Iterator<NestedInteger>> stack;
        int cur;
        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            stack.push(nestedList.iterator());
            cur = 0;
        }

        @Override
        public Integer next() {
            return cur;
        }
        // (N+L)/N => O(L/N) if list or O(1) if integer
        @Override
        public boolean hasNext() {
            while(!stack.isEmpty()) {
                Iterator<NestedInteger> it = stack.peek();
                if(it.hasNext()) {
                    NestedInteger ni = it.next();
                    if(ni.isInteger()) {
                        cur = ni.getInteger();
                        return true;
                    } else {
                        stack.push(ni.getList().iterator());
                    }
                } else {
                    stack.pop();
                }
            }
            return false;
        }
    }
    */

    /*
    import java.util.NoSuchElementException;

    public class NestedIterator implements Iterator<Integer> {

        // In Java, the Stack class is considered deprecated. Best practice is to use
        // a Deque instead. We'll use addFirst() for push, and removeFirst() for pop.
        private Deque<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) { // O(N) - constructor adds elements one by one
            // The constructor puts them on in the order we require. No need to reverse.
            stack = new ArrayDeque(nestedList);
        }


        @Override
        public Integer next() {
            // As per java specs, throw an exception if there's no elements left.
            if (!hasNext()) throw new NoSuchElementException();
            // hasNext ensures the stack top is now an integer. Pop and return
            // this integer.
            return stack.removeFirst().getInteger();
        }


        @Override
        public boolean hasNext() {
            // Check if there are integers left by getting one onto the top of stack.
            makeStackTopAnInteger();
            // If there are any integers remaining, one will be on the top of the stack,
            // and therefore the stack can't possibly be empty.
            return !stack.isEmpty();
        }


        private void makeStackTopAnInteger() {
            // While there are items remaining on the stack and the front of
            // stack is a list (i.e. not integer), keep unpacking.
            while (!stack.isEmpty() && !stack.peekFirst().isInteger()) {
                // Put the NestedIntegers onto the stack in reverse order.
                List<NestedInteger> nestedList = stack.removeFirst().getList();
                for (int i = nestedList.size() - 1; i >= 0; i--) {
                    stack.addFirst(nestedList.get(i));
                }
            }
        }
    }
     */
}
