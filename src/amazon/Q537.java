package amazon;

// Complex Number Multiplication
public class Q537 {
    // O(1) O(1)
    public String complexNumberMultiply(String num1, String num2) {
        String[] n1 = num1.split("\\+");
        String[] n2 = num2.split("\\+");
        int real1 = Integer.parseInt(n1[0]);
        int real2 = Integer.parseInt(n2[0]);
        int im1 = Integer.parseInt(n1[1].substring(0, n1[1].length() - 1));
        int im2 = Integer.parseInt(n2[1].substring(0, n2[1].length() - 1));
        int real = real1 * real2 - im1 * im2;
        int im = real1 * im2 + real2 * im1;
        StringBuilder sb = new StringBuilder();
        sb.append(real).append('+').append(im).append('i');
        return sb.toString();
    }

    public String complexNumberMultiply1(String a, String b) {
        String x[] = a.split("\\+|i");
        String y[] = b.split("\\+|i");
        int a_real = Integer.parseInt(x[0]);
        int a_img = Integer.parseInt(x[1]);
        int b_real = Integer.parseInt(y[0]);
        int b_img = Integer.parseInt(y[1]);
        return (a_real * b_real - a_img * b_img) + "+" + (a_real * b_img + a_img * b_real) + "i";

    }
}
