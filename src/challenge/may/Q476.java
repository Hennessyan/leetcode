package challenge.may;
// Number Complement : positive, start from 1
// Same as Q1009 : non-negative - if 0 return 1
public class Q476 {

    public static void main(String[] args) {
        Q476 q = new Q476();
        System.out.println(q.findComplement(5));        //2
        System.out.println(q.findComplement(1));        //0
    }

    public int findComplement(int num) {
        int res = 0;
        int i = 0;
        while(num > 0) {
            if((num & 1) == 0) {
                res |= (1 << i);
            }
            i++;
            num >>= 1;
        }
        return res;
    }
}
