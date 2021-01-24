package amaon;

import java.util.ArrayList;
import java.util.List;

// Shift 2D Grid
public class Q1260 {
    // O(k * m * n) O(m * n)
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        // Repeat the transform k times.
        for (;k > 0; k--) {

            int previous = grid[grid.length - 1][grid[0].length - 1];
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    int temp = grid[row][col];
                    grid[row][col] = previous;
                    previous = temp;
                }
            }
        }

        // Copy the grid into a list for returning.
        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : grid) {
            List<Integer> listRow = new ArrayList<>();
            result.add(listRow);
            for (int v : row) listRow.add(v);
        }

        return result;
    }
    // can't use this way as grid[i][j] may < 0
    public List<List<Integer>> shiftGrid1(int[][] grid, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int m = grid.length, n = grid[0].length;
        int times = 1001;
        int x = 0, y = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                x = (i + (j + k) / n) % m;
                y = (j + k) % n;
                grid[x][y] = (grid[i][j] % times) * times + grid[x][y];
            }
        }
        for(int[] row : grid) {
            List<Integer> list = new ArrayList<>();
            for(int i : row) {
                list.add(i / times);
            }
            res.add(list);
        }
        return res;
    }
    // O(m * n) O(m * n)
    public List<List<Integer>> shiftGrid2(int[][] grid, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int m = grid.length, n = grid[0].length, x = 0, y = 0;
        for(int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                list.add(0);
            }
            res.add(list);
        }
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                x = (i + (j + k) / n) % m;
                y = (j + k) % n;
                res.get(x).set(y, grid[i][j]);
            }
        }
        return res;
    }
//    idea is fine, but don't use it as high complexity
//    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
//        List<List<Integer>> res = new ArrayList<>();
//        LinkedList<Integer> flat = new LinkedList<>();
//
//        for(int[] row : grid) for(int i : row) flat.add(i);
//        for(int i = 0; i < k; i++) flat.add(0, flat.pollLast());
//
//        List<Integer> temp = new LinkedList<>();
//        for(int i : flat){
//            if(temp.size() == grid[0].length){
//                res.add(new LinkedList<>(temp));
//                temp.clear();
//            }
//            temp.add(i);
//        }
//        res.add(new LinkedList<>(temp));
//        return res;
//    }
}
