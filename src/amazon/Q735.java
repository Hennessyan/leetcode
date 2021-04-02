package amazon;

import java.util.Stack;

// Asteroid Collision
public class Q735 {
    // O(n) O(n)
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for(int a : asteroids) {
            if(a > 0) {
                stack.push(a);
            }else {
                while(!stack.empty() && stack.peek() > 0 && stack.peek() < -a) {
                    stack.pop();
                }
                if(stack.empty() || stack.peek() < 0) {
                    stack.push(a);
                }else if(stack.peek() == -a) {
                    stack.pop();
                }
            }
        }
        int[] res = new int[stack.size()];
        for(int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
