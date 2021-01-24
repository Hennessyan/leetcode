package challenge.september;

// Largest Time for Given Digits
public class Q949 {

    public static void main(String[] args) {
        Q949 q = new Q949();
        int[] A = {1, 2, 3, 4};
        System.out.println(q.largestTimeFromDigits(A));
    }

    // O(1) O(1) -> 4! （factorial of 4)
    private int time;

    public String largestTimeFromDigits(int[] A) {
        time = -1;
        permutation(A, 0);
        return time == -1 ? "" : String.format("%02d:%02d", time / 60, time % 60);
    }

    private void permutation(int[] A, int start) {
        if (start == A.length) {
            build_time(A);
            return;
        }
        for (int i = start; i < A.length; i++) {
            swap(A, i, start);
            permutation(A, start + 1);
            swap(A, i, start);
        }
    }

    private void build_time(int[] A) {
        int hour = A[0] * 10 + A[1];
        int minute = A[2] * 10 + A[3];
        if (hour < 24 && minute < 60) {
            int val = hour * 60 + minute;
            if (time < val) {
                time = val;
            }
        }
    }

    private void swap(int[] A, int i, int j) {
        if (i != j && A[i] != A[j]) {
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
    }
}
