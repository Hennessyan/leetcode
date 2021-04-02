package google;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// Shortest Bridge
public class Q934 {
    // O(mn) O(mn)
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    Deque<Integer> stack;
    Queue<Integer> queue;
    int m, n;
    public int shortestBridge(int[][] A) {
        stack = new ArrayDeque<>();
        queue = new LinkedList<>();
        m = A.length;
        n = A[0].length;
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(A[r][c] == 1) {
                    stack.push(r * n + c);
                    A[r][c] = 2;
                    break;
                }
            }
            if(!stack.isEmpty()) {
                break;
            }
        }
        propogate(A);

        int step = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for(int i = 0; i < size; i++) {
                int val = queue.poll();
                int r = val / n;
                int c = val % n;
                for(int[] d : dirs) {
                    int x = r + d[0];
                    int y = c + d[1];
                    if(x >= 0 && x < m && y >= 0 && y < n) {
                        if(A[x][y] == 2) {
                            continue;
                        }
                        if(A[x][y] == 1) {
                            return step;
                        }
                        A[x][y] = 2;
                        queue.offer(x * n + y);
                    }
                }
            }
        }
        return -1;
    }
    private void propogate(int[][] A) {
        while(!stack.isEmpty()) {
            int val = stack.pop();
            int r = val / n;
            int c = val % n;
            for(int[] d : dirs) {
                int x = r + d[0];
                int y = c + d[1];
                if(x >= 0 && x < m && y >= 0 && y < n) {
                    if(A[x][y] == 2) {
                        continue;
                    }
                    if(A[x][y] == 0) {
                        queue.offer(x * n + y);
                    }
                    stack.push(x * n + y);
                    A[x][y] = 2;
                }
            }
        }
    }
    /*
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    Deque<Integer> stack;
    Queue<Integer> queue;
    int m, n;
    public int shortestBridge(int[][] A) {
        stack = new ArrayDeque<>();
        queue = new LinkedList<>();
        m = A.length;
        n = A[0].length;
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(A[r][c] == 1) {
                    stack.push(r * n + c);
                    A[r][c] = 2;
                    break;
                }
            }
            if(!stack.isEmpty()) {
                break;
            }
        }
        propogate(A);

        int step = 0;   // 可以存1临近的0来提高PERFORMANCE.
        while(!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for(int i = 0; i < size; i++) {
                int val = queue.poll();
                int r = val / n;
                int c = val % n;
                for(int[] d : dirs) {
                    int x = r + d[0];
                    int y = c + d[1];
                    if(x >= 0 && x < m && y >= 0 && y < n) {
                        if(A[x][y] == 2) {
                            continue;
                        }
                        if(A[x][y] == 1) {
                            return step;
                        }
                        A[x][y] = 2;
                        queue.offer(x * n + y);
                    }
                }
            }
        }
        return -1;
    }
    private void propogate(int[][] A) {
        while(!stack.isEmpty()) {
            int val = stack.pop();
            int r = val / n;
            int c = val % n;
            for(int[] d : dirs) {
                int x = r + d[0];
                int y = c + d[1];
                if(x >= 0 && x < m && y >= 0 && y < n) {
                    if(A[x][y] == 2) {
                        continue;
                    }
                    if(A[x][y] == 0) {
                        queue.offer(x * n + y);
                    } else {
                        stack.push(x * n + y);
                        A[x][y] = 2;
                    }
                }
            }
        }
    }
     */
}
