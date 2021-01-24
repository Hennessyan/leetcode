package apple;
// Two Sum II - Input array is sorted
public class Q167 {
    // O(n) O(1)
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while(numbers[l] + numbers[r] != target) {
            if(numbers[l] + numbers[r] < target) {
                l++;
            }else {
                r--;
            }
        }
        return new int[]{l + 1, r + 1};
    }
}
