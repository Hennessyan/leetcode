package google;

import java.util.Arrays;

// Put Boxes Into the Warehouse I
public class Q1564 {
    // add smallest box to the rightmost warehouse
    // O(nlgn + m) O(1) -> O(lgn) for sort
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes); // O(lgn)
        int m = boxes.length, n = warehouse.length;
        // Preprocess the height of the warehouse rooms to get usable heights
        for(int i = 1; i < n; i++) {
            warehouse[i] = Math.min(warehouse[i], warehouse[i - 1]);
        }
        int count = 0;
        for(int i = n - 1; i >= 0; i--) {
            if(count < m && boxes[count] <= warehouse[i]) {
                count++;
            }
        }
        return count;
    }
    // follow-up: O(1) space without modifying warehouse
    // add largest box from left to right warehouse
    public int maxBoxesInWarehouse1(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);
        int i = boxes.length - 1, n = warehouse.length;
        int j = 0, count = 0;
        while(i >= 0 && j < n) {
            if(boxes[i] <= warehouse[j]) {
                count++;
                j++;
            }
            i--;
        }
//        for(int room : warehouse) {
//            while(i >= 0 && boxes[i] > room) {
//                i--;
//            }
//            if(i < 0) return count;
//            count++;
//            i--;
//        }
        return count;
    }
}
