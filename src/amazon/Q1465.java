package amazon;

import java.util.Arrays;

// Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
public class Q1465 {
    // O(max(nlgn, mlgm)) O(max(lgm, lgn))
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long hDiff = 0, wDiff = 0;      // be careful with type
        int top = h, right = w;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        for(int i = horizontalCuts.length - 1; i >= 0; i--) {
            hDiff = Math.max(hDiff, top - horizontalCuts[i]);
            top = horizontalCuts[i];
        }
        hDiff = Math.max(hDiff, top);
        for(int i = verticalCuts.length - 1; i >= 0; i--) {
            wDiff = Math.max(wDiff, right - verticalCuts[i]);
            right = verticalCuts[i];
        }
        wDiff = Math.max(wDiff, right);
        return (int) (hDiff * wDiff % (1e9+7)); // be careful with type
    }

    public int maxArea1(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int m = horizontalCuts.length, n = verticalCuts.length;
        long hdiff= Math.max(h - horizontalCuts[m - 1], horizontalCuts[0]);
        long vdiff = Math.max(w - verticalCuts[n - 1], verticalCuts[0]);
        for(int i = 1; i < m; i++) {
            hdiff = Math.max(hdiff, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        for(int i = 1; i < n; i++) {
            vdiff = Math.max(vdiff, verticalCuts[i] - verticalCuts[i - 1]);
        }
        return (int) (hdiff * vdiff % (int)(1e9 + 7));  // wrong answer if using (1e9+7) directly.
    }
}
