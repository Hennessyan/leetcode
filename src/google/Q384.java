package google;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// Shuffle an Array
public class Q384 {
    // O(n^2) O(n)
    class Solution {

        int[] arr;
        int[] original;
        Random random;
        public Solution(int[] nums) {
            arr = nums;
            original = arr.clone();
            random = new Random();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            arr = original.clone();
            return arr;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            List<Integer> list = new LinkedList<>();
            for(int val : arr) {
                list.add(val);
            }
            for(int i = 0; i < arr.length; i++) {
                arr[i] = list.remove(random.nextInt(list.size()));
            }
            return arr;
        }
    }
    // O(n) O(n)
    class Solution1 {
        int[] arr;
        int[] originals;
        Random random;
        public Solution1(int[] nums) {
            arr = nums;
            originals = nums.clone();
            random = new Random();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            arr = originals.clone();
            return arr;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            for(int i = 0; i < arr.length; i++) {
                swap(i, randomRange(i, arr.length));
            }
            return arr;
        }

        private void swap(int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        private int randomRange(int l, int r) {
            return random.nextInt(r - l) + l;
        }
    }
}
