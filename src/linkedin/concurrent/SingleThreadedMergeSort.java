package linkedin.concurrent;

public class SingleThreadedMergeSort {

    public static void main(String[] args) {
        int[] input = new int[]{ 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        printArray(input,"Before: ");
        mergeSort(0, input.length-1, input);
        printArray(input,"After:  ");
    }
    private static void printArray(int[] input, String msg) {
        System.out.println();
        System.out.print(msg + " ");
        for (int i = 0; i < input.length; i++)
            System.out.print(" " + input[i] + " ");
        System.out.println();
    }

    private static void mergeSort(int l, int r, int[] arr) {
        if(l == r) return;
        int m = l + (r - l) / 2;
        mergeSort(l, m, arr);
        mergeSort(m + 1, r, arr);
        merge(arr, l, m, r);
    }
    private static void merge(int[] arr, int l, int m, int r) {
        int start2 = m + 1, start1 = l;
        int t = 0, len = r - l + 1;
        int[] tmp = new int[len];

        while(start1 <= m && start2 <= r) {
            if(arr[start1] <= arr[start2]) {
                tmp[t++] = arr[start1++];
            } else {
                tmp[t++] = arr[start2++];
            }
        }
        while(start1 <= m) {
            tmp[t++] = arr[start1++];
        }
        while(start2 <= m) {
            tmp[t++] = arr[start2++];
        }
        t = 0;
        while(t < len) {
            arr[t + l] = tmp[t];
            t++;
        }
    }
}
