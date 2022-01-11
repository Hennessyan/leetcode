package amazon;
// First Missing Positive
public class Q41 {

    /*method1       Put each number in its right place.*/
    // O(n) O(1) ?
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while(i < nums.length){
            //不能使用： nums[i] != i + 1 来判断,［1，1］会死循环
            //nums[i] != nums[nums[i] - 1] 只在某位置的值不是对应该位置的值时才swap,而且swap后该位置一定是该有的值
            //可以理解为：当位置nums[i]-1的值不是nums[i]的时候swap
            if(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }
        i = 0;
        while(i < nums.length && nums[i] == i+1) i++;
        return i+1;
    }
    //array需要用这种方法来swap,直接交换array[i],array[j]没用
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
