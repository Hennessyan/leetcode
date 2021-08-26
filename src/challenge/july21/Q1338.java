package challenge.july21;

import java.util.*;

// Reduce Array Size to The Half
// next : Q1776
public class Q1338 {
    // O(nlgn) O(n)
    public int minSetSize(int[] arr) {

        Map<Integer, Integer> countsMap = new HashMap<>();
        for (int num : arr) {
            if (!countsMap.containsKey(num)) {
                countsMap.put(num, 1);
                continue;
            }
            countsMap.put(num, countsMap.get(num) + 1);
        }
        List<Integer> counts = new ArrayList<>(countsMap.values());
        Collections.sort(counts, Collections.reverseOrder());

        int numbersRemovedFromArr = 0;
        int setSize = 0;
        for (int count : counts) {
            numbersRemovedFromArr += count;
            setSize += 1;
            if (numbersRemovedFromArr >= arr.length / 2) {
                break;
            }
        }

        return setSize;
    }
    // O(n) O(n)
    public int minSetSize1(int[] arr) {

        Map<Integer, Integer> counts = new HashMap<>();
        int maxCount = 0;
        for (int num : arr) {
            if (!counts.containsKey(num)) {
                counts.put(num, 0);
            }
            int newCount = counts.get(num) + 1;
            counts.put(num, newCount);
            maxCount = Math.max(maxCount, newCount);
        }

        int[] buckets = new int[maxCount + 1];
        for (int count : counts.values()) {
            buckets[count]++;
        }

        int setSize = 0;
        int numbersToRemoveFromArr = arr.length / 2;
        int bucket = maxCount;
        while (numbersToRemoveFromArr > 0) {
            int maxNeededFromBucket = numbersToRemoveFromArr / bucket;
            if (numbersToRemoveFromArr % bucket != 0) {
                maxNeededFromBucket++;
            }
            int setSizeIncrease = Math.min(buckets[bucket], maxNeededFromBucket);
            setSize += setSizeIncrease;
            numbersToRemoveFromArr -= setSizeIncrease * bucket;
            bucket--;
        }
        return setSize;
    }
}
