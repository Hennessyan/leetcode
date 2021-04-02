package challenge.feb21;
// Squirrel Simulation
public class Q573 {
    // O(n) O(1)
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int total = 0, d = Integer.MIN_VALUE;
        for(int[] nut : nuts) {
            total += 2 * dist(nut, tree);
            d = Math.max(d, dist(nut, tree) - dist(nut, squirrel));
        }
        return total - d;
    }
    private int dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    // total - a + b <=> total - (a - b)
    // a = dist(tree, nut), b = dist(nut, squirrel)
    // a should as bigger as possible, and b should as lower as possible
    // a - b should as bigger as possible !!!
}
