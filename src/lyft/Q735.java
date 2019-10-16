package lyft;

import java.util.Arrays;
import java.util.Stack;

//Asteroid Collision (小行星)
public class Q735 {

    public static void main(String[] args) {
        Q735 q = new Q735();
        int[] asteroids = {-2, -1, 1, 2};
        int[] res = q.asteroidCollision(asteroids);
        System.out.println(Arrays.toString(res));   //[-2, -1, 1, 2]
        System.out.println(q.asteroidCollision2(new int[]{5, 10, -5}));
        System.out.println(q.asteroidCollision2(new int[]{8, -8}));
        System.out.println(q.asteroidCollision2(new int[]{10, 2, -5}));
        System.out.println(q.asteroidCollision2(new int[]{-2, -1, 1, 2}));
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int a : asteroids) {
            if (a > 0) {
                stack.push(a);
            } else {
                while (!stack.empty() && stack.peek() > 0 && stack.peek() < -a) {
                    stack.pop();
                }
                if (stack.empty() || stack.peek() < 0) {
                    stack.push(a);
                } else if (stack.peek() == -a) {
                    stack.pop();
                }
            }
        }
        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

    //follow up : only return num of left
    public int asteroidCollision2(int[] asteroids) {
        int total = asteroids.length;
        int r = total - 1, l = r;
        while (r > 0) {
            if (asteroids[r] > 0 || asteroids[r - 1] < 0) { //[a,b] b向右或者a,b都向左.
                r--;
            } else {
                l = r - 1;
                while (l >= 0 && asteroids[l] > 0 && asteroids[l] < -asteroids[r]) {
                    total--;
                    l--;
                }
                if (l >= 0 && asteroids[l] > 0) {
                    if (asteroids[l] == -asteroids[r]) { // [5,-5]
                        total -= 2;
                    } else {                        // [5,-3]
                        total -= 1;
                    }
                    r = l - 1;
                } else {
                    r = l;
                }
            }
        }
        return total;
    }
}
