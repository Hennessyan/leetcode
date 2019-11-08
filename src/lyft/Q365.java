package lyft;

import java.util.*;

//Water and Jug Problem
public class Q365 {

    public static void main(String[] args) {
        Q365 q = new Q365();
        System.out.println(q.canMeasureWater1(3, 5, 4));   //true
        System.out.println(q.canMeasureWater1(2, 6, 5));   //false
    }

    //method1
    //4 = -2 * 3 + 2 * 5
    //ax + by = d的解为 d = gcd(x, y)
    public boolean canMeasureWater(int x, int y, int z) {
        if (z == 0) {
            return true;
        }
        if (x + y < z) {
            return false;
        }
        return z % gcd(x, y) == 0;
    }

    private int gcd(int x, int y) {
        if (x == 0) {
            return y;
        }
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }

    //https://leetcode.com/problems/water-and-jug-problem/discuss/303415/Java-BFS-and-DFS-solution
    public boolean canMeasureWater1(int x, int y, int z) {
        State s = new State(0, 0);
        Queue<State> queue = new LinkedList<>();
        queue.offer(s);
        Set<State> visited = new HashSet<>();
        visited.add(s);
        while (!queue.isEmpty()) {
            State tmp = queue.poll();
            if (tmp.a + tmp.b == z) {
                return true;
            }
            for (State v : getStateList(tmp, x, y)) {
                if (visited.add(v)) {
                    queue.add(v);
                }
            }
        }
        return false;
    }

    private List<State> getStateList(State tmp, int x, int y) {
        List<State> list = new ArrayList<>();
        list.add(new State(0, tmp.b));  // empty jug1
        list.add(new State(tmp.a, 0));  // empty jug2
        list.add(new State(x, tmp.b));     // fill jug1
        list.add(new State(tmp.a, y));     // fill jug2
        list.add(new State(tmp.a - Math.min(tmp.a, y - tmp.b), tmp.b + Math.min(tmp.a, y - tmp.b)));    // pour from jug1 to jug2
        list.add(new State(tmp.a + Math.min(tmp.b, x - tmp.a), tmp.b - Math.min(tmp.b, x - tmp.a)));    // pour from jug2 to jug1
        return list;
    }

    class State {
        int a, b;

        public State(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int hashCode() {
            return a * 31 + b;
        }

        public boolean equals(Object o) {
            State t = (State) o;
            return this.a == t.a && this.b == t.b;
        }
    }
}
