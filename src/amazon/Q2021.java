package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 2021. Brightest Position on Street
public class Q2021 {

    public int brightestPosition(int[][] lights) {
        List<int[]> list = new ArrayList<>();
        for(int[] light : lights) {
            list.add(new int[]{light[0] - light[1], 1});
            list.add(new int[]{light[0] + light[1], -1});
        }
        Collections.sort(list, (l1, l2) -> l1[0] == l2[0] ? l2[1] - l1[1] : l1[0] - l2[0]);
        int ans = list.get(0)[0], count = 0, max = 0;
        for(int[] cur : list) {
            count += cur[1];
            if(count > max) {
                ans = cur[0];
                max = count;
            }
        }
        return ans;
    }

    public int brightestPosition1(int[][] lights) {
        for (int[] light : lights) {
            int pivot = light[0];
            light[0] = pivot - light[1];
            light[1] = pivot + light[1];
        }
        int[] start = new int[lights.length];
        int[] end = new int[lights.length];
        for (int i = 0; i < lights.length; i++) {
            start[i] = lights[i][0];
            end[i] = lights[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int j = 0;
        int result = start[0];
        int max = 0;
        int cur = 0;
        int n = lights.length;
        for (int i = 0; i < n; i++) {
            cur++;
            while (j < n && end[j] < start[i]) {
                j++;
                cur--;
            }
            if (cur > max) {
                max = cur;
                result = start[i];
            }
        }
        return result;
    }
}
