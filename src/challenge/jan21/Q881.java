package challenge.jan21;

import java.util.Arrays;

// Boats to Save People
public class Q881 {

    // O(nlgn) O(n)
    public int numRescueBoats(int[] people, int limit) {
        if(people == null || people.length == 0) {
            return 0;
        }
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        int count = 0;
        while(i <= j) {
            if(people[j] > limit) {
                count++;
                j--;
            } else if(people[i] + people[j] <= limit) {
                count++;
                i++;
                j--;
            } else {
                j--;
                count++;
            }
        }
        return count;
    }

    public int numRescueBoats1(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        int ans = 0;

        while (i <= j) {
            ans++;
            if (people[i] + people[j] <= limit)
                i++;
            j--;
        }

        return ans;
    }
}
