package google;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// Validate Stack Sequences
public class Q946 {
    // O(n) O(n)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int j = 0, N = popped.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int val : pushed) {
            stack.push(val);
            while(!stack.isEmpty() && j < N && stack.peek() == popped[j]) { // j < N is needed if length of two arrays are different
                stack.pop();
                j++;
            }
        }
        return j == N;
    }
    // faster than method1
    public boolean validateStackSequences1(int[] pushed, int[] popped) {
        int j = 0, N = popped.length;
        int i = 0, M = pushed.length;
        Deque<Integer> stack = new ArrayDeque<>();
        while(j < N) {
            while(i < M && pushed[i] != popped[j]) {
                stack.push(pushed[i++]);
            }
            if(i == M) {
                break;
            }
            i++;
            j++;
            while(!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return j == N;
    }
    // O(n) O(1)
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        int i = 0, j = 0, n = popped.length;
        for(int val : pushed) {
            pushed[i] = val;
            while(i >= 0 && pushed[i] == popped[j]) {
                i--;
                j++;
            }
            i++;
        }
        return i == 0 && j == n;    // j == n in case length are different
    }
}
