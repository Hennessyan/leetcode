package amazon;
// Rectangle Area
public class Q223 {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaA = (C - A) * (D - B);
        int areaB = (G - E) * (H - F);
        int total = areaA + areaB;
        if(C <= E || G <= A || D <= F || H <= B) {
            return total;
        }else {
            int w = Math.min(C, G) - Math.max(A, E);
            int h = Math.min(D, H) - Math.max(B, F);
            return total - w * h;
        }
    }
}
