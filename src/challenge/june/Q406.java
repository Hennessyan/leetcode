package challenge.june;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Queue Reconstruction by Height
public class Q406 {

    public int[][] reconstructQueue(int[][] people) {
        List<int[]> list = new LinkedList<>();
        Arrays.sort(people, (p1, p2) -> p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]);
        for(int[] p : people) {
            list.add(p[1], p);  //向index 0加入两个people,先加入的会自动后移. 因此需要从高向低排.
        }
        return list.toArray(new int[people.length][2]);
    }
}
