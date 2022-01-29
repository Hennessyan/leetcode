package linkedin.VO;

public class Pizza {

    /**
     * f(0) = 1
     * f(1) = 1 + f(0) = 2
     * f(2) = 2 + f(1) = 4
     * f(3) = 3 + f(2) = 7
     * f(4) = 4 + f(3) = 11
     * f(n) = n + f(n - 1) => (1+n)*n/2+1 => (n*n+n+2)/2
     */
    public static void main(String[] args) {
        System.out.println(cut(4));
        System.out.println(cut(10));
    }
    public static int cut(int n) {
        return (n * n + n + 2) / 2;
    }
}
