package google;

import java.util.Arrays;

// Put Boxes Into the Warehouse II
public class Q1580 {
    // O(nlgn + m) O(1)
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);
        int l = 0, r = warehouse.length - 1;
        int count = 0, i = boxes.length - 1;
        while(l <= r && i >= 0) {
            if(boxes[i] <= warehouse[l]) {

                count++;
                l++;
            } else if(boxes[i] <= warehouse[r]) {
                count++;
                r--;
            }
            i--;
        }
        return count;
    }
}
