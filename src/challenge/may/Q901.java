package challenge.may;

import java.util.LinkedList;
import java.util.Stack;

// Online Stock Span
public class Q901 {

    // DP: next operation - amortized to O(1) : increase-order is O(1), decrease-order is O(1).
    // SC: O(n)
    /*
    class StockSpanner {
        LinkedList<Integer> count;
        LinkedList<Integer> prices;
        int i;

        public StockSpanner() {
            count = new LinkedList<>();
            prices = new LinkedList<>();
            i = 0;
        }

        public int next(int price) {
            if (i == 0 || price < prices.get(i - 1)) {
                count.add(1);
            } else {
                int j = i - 1;
                while (j >= 0 && price >= prices.get(j)) {
                    j -= count.get(j);
                }
                count.add(i - j);
            }
            prices.add(price);
            i++;
            return count.peekLast();
        }
    }
    */
    // Stack O(1) -> each element will push and pop once, total 2n operations.
    // SC : O(n) - decrease-order
    class StockSpanner {

        Stack<Integer> count;
        Stack<Integer> prices;
        public StockSpanner() {
            count = new Stack<>();
            prices = new Stack<>();
        }

        public int next(int price) {
            int span = 1;
            while(!prices.empty() && price >= prices.peek()) {
                span += count.pop();
                prices.pop();
            }
            prices.push(price);
            count.push(span);
            return span;
        }
    }
}
