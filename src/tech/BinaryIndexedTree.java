package tech;

import java.util.Arrays;
// Q307(M) Q1649(H)
public class BinaryIndexedTree {

    public static void main(String[] args) {
        int[] arr = {0, 1,2,3,4,5,6,7,8};   // 1 ~ 8, arr[0] = 0 仅仅是为了方便BIT,实际是{1,2,3,4,5,6,7,8}
        int n = arr.length;
        int[] tree = Arrays.copyOf(arr, n);

        BinaryIndexedTree bit = new BinaryIndexedTree();
        bit.make(tree, n);

        System.out.println(bit.sum(tree, n - 1));   //36
        bit.add(tree, 1, 5, n);
        System.out.println(bit.sum(tree, n - 1));   //36 + 5 = 41
    }
    // O(n) -> check last method in Q307 to understand when we will get O(nlgn) to build bit
    public void make(int[] tree, int n) {
        for(int i = 1; i < n; i++) {
            int p = i + (i & -i);
            if(p < n) {
                tree[p] += tree[i];
            }
        }
    }
    // O(lgn)
    public int sum(int[] tree, int i) {
        int sum = 0;
        while(i > 0) {
            sum += tree[i];
            i -= i & -i;    //flip the last set bit
        }
        return sum;
    }
    // O(lgn)
    public void add(int[] tree, int i, int val, int n) {
        while(i < n) {
            tree[i] += val;
            i += i & -i;
        }
    }
}
