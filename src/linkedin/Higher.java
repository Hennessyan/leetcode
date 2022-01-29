package linkedin;
/*
    给你一个排好序的数组，一个数x ，让你找出第一个比这个数大的位置
 */
public class Higher {

    public static void main(String[] args) {
        int[] arr = {1,2,3,3,3};
        System.out.println(higher(arr, 2));
        System.out.println(higher(arr, 3));
        int[] arr1 = {1,2,3,3,3,4};
        System.out.println(higher(arr1, 0));
        System.out.println(higher(arr1, 3));
    }

    public static int higher(int[] arr, int val) {
        if(arr == null || arr.length == 0) return -1;
        int l = 0, r = arr.length;
        while(l < r) {
            int m = l + (r - l) / 2;
            if(arr[m] > val) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l == arr.length ? -1 : arr[l];
    }
}
