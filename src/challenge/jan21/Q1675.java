package challenge.jan21;

import java.util.*;

// Minimize Deviation in Array
public class Q1675 {
    // O(n * lgm * lgn) O(n)
    // lgm (candidate) * n (total nums) * lgn (sort time)
    // m - largest num in arr.
    // 先把arrary变成list然后放入PQ效率会更高一些.
    public int minimumDeviation0(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int min = Integer.MAX_VALUE;
        for(int num : nums) {
            if(num % 2 == 1) {
                num *= 2;
            }
            pq.add(num);
            min = Math.min(min, num);
        }
        int minDev = Integer.MAX_VALUE;

        while(!pq.isEmpty()) {
            int max = pq.poll();
            minDev = Math.min(minDev, max - min);
            if(max % 2 == 0) {
                max /= 2;
                min = Math.min(min, max);
                pq.add(max);
            } else {
                break;
            }
        }
        return minDev;
    }
    // k = n * lgm
    // O(klgk) O(k)
    public int minimumDeviation1(int[] nums) {
        int n = nums.length;
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int num = nums[i];
            if(num % 2 == 0) {
                list.add(new int[]{num, i});
                while(num % 2 == 0) {
                    num /= 2;
                    list.add(new int[]{num, i});
                }
            } else {
                list.add(new int[]{num, i});
                list.add(new int[]{num * 2, i});
            }
        }
        Collections.sort(list, (a, b) -> a[0] - b[0]);
        int[] needInclude = new int[n];
        for(int i = 0; i < n; i++) {
            needInclude[i] = 1;
        }
        int notIncluded = n;
        int l = 0, min = Integer.MAX_VALUE;
        for(int r = 0; r < list.size(); r++) {
            int[] pair = list.get(r);
            if(--needInclude[pair[1]] == 0) {
                notIncluded -= 1;
            }
            if(notIncluded == 0) {
                while(l <= r && notIncluded == 0) {
                    int[] p1 = list.get(l++);
                    if(++needInclude[p1[1]] > 0) {
                        notIncluded += 1;
                    }
                }
                min = Math.min(min, pair[0] - list.get(l - 1)[0]);
            }
        }
        return min;
    }
    // O(klgn) O(n + k)
    public int minimumDeviation2(int[] nums) {
        int n = nums.length;
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = null;
        int minDev = Integer.MAX_VALUE, max = 0;
        for(int i = 0; i < n; i++) {
            int num = nums[i];
            list = new ArrayList<>();
            if(num % 2 == 0) {
                list.add(num);
                while(num % 2 == 0) {
                    num /= 2;
                    list.add(num);
                }
                Collections.sort(list);
            } else {
                list.add(num);
                list.add(num * 2);
            }
            max = Math.max(max, list.get(0));
            lists.add(list);
        }

        int[] index = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> lists.get(a).get(index[a]) - lists.get(b).get(index[b]));
        for(int i = 0; i < n; i++) {
            pq.offer(i);
        }
        while(!pq.isEmpty()) {
            int i = pq.poll();
            int min = lists.get(i).get(index[i]);
            minDev = Math.min(minDev, max - min);
            if(++index[i] == lists.get(i).size()) {
                break;
            }
            int tmp = lists.get(i).get(index[i]);
            max = Math.max(max, tmp);
            pq.add(i);
        }
        return minDev;
    }
    // O(klgk) O(k)
    public int minimumDeviation(int[] nums) {
        int n = nums.length;
        // pretreatment
        List<List<Integer>> possibleList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> candidates = new ArrayList<>();
            if (nums[i] % 2 == 0) {
                int temp = nums[i];
                candidates.add(temp);
                while (temp % 2 == 0) {
                    temp /= 2;
                    candidates.add(temp);
                }
            } else {
                candidates.add(nums[i] * 2);
                candidates.add(nums[i]);
            }
            // reverse candidates to sort from small to large
            Collections.reverse(candidates);
            possibleList.add(candidates);
        }
        List<int[]> pointers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int size = possibleList.get(i).size();
            for (int j = 0; j < size; j++) {
                pointers.add(new int[] { possibleList.get(i).get(j), i, j });
            }
        }
        pointers.sort(Comparator.comparingInt(p -> p[0]));
        int minDeviation = Integer.MAX_VALUE;
        int currentEnd = 0;
        for (int i = 0; i < n; i++) {   // "guarantee range in all lists"
            currentEnd = Math.max(currentEnd, possibleList.get(i).get(0));
        }
        for (int[] current : pointers) {
            int currentStart = current[0];
            int index = current[1];
            int indexInCandidates = current[2];
            if (minDeviation > currentEnd - currentStart) {
                minDeviation = currentEnd - currentStart;
            }
            // if the pointer reach last
            if (indexInCandidates + 1 == possibleList.get(index).size()) {
                return minDeviation;
            }
            int nextValue = possibleList.get(index).get(indexInCandidates + 1);
            currentEnd = Math.max(currentEnd, nextValue);
        }
        return minDeviation;
    }
}
