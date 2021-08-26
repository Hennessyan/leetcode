package amazon;

import java.util.Arrays;

//  Maximum Units on a Truck
public class Q1710 {
    // O(nlgn) O(lgn)
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int max = 0;
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        for(int i = 0; i < boxTypes.length && truckSize > 0; i++) {
            int num = Math.min(boxTypes[i][0], truckSize);
            max += num * boxTypes[i][1];
            truckSize -= num;
        }
        return max;
    }
}
