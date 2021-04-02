package amazon;
// Number of Steps to Reduce a Number to Zero
public class Q1342 {
    // O(lgn) O(1)
    public int numberOfSteps (int num) {
        if(num == 0) {
            return 0;
        }
        int steps = 0;
        while(num != 0) {
            if(num % 2 == 0) {
                num /= 2;
            } else {
                num -= 1;
            }
            steps++;
        }
        return steps;
    }

    public int numberOfSteps1 (int num) {
        if(num == 0) {
            return 0;
        }
        int steps = 0;
        while(num != 0) {
            if((num & 1) == 0) {
                steps += 1;
            } else {
                steps += 2;
            }
            num >>= 1;
        }
        return steps - 1;   //last bit always 1, hence we need to add 2 for steps, which adds one extra. need -1.
    }
}
