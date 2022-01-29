package linkedin;
// Implement Rand10() Using Rand7()
public class Q470 {

    // E = 2 * (40/49) + (2+E) * (1 - 40/49) => 2.45
    // https://leetcode.com/problems/implement-rand10-using-rand7/discuss/150301/Three-line-Java-solution-the-idea-can-be-generalized-to-%22Implement-RandM()-Using-RandN()%22
    // rand()7 => rand()49 => rand40() => rand10()
//    public int rand10() {
//        int result = 40;
//        while(result >= 40) {
//            result = 7 * (rand7() - 1) + rand7() - 1;
//        }
//        return result % 10 + 1;
//    }
}
