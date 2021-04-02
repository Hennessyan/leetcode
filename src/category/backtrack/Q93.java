package category.backtrack;

import java.util.ArrayList;
import java.util.List;

// Restore IP Addresses
public class Q93 {
    // O(3^3) O(19) - as most 19 results
    // O(1) O(1)
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() == 0){
            return res;
        }
        backtrack(s, "", 0, res, 0);
        return res;
    }
    private void backtrack(String s, String ip, int index, List<String> res, int count){
        if(count > 4){
            return;
        }
        if(count ==4 && index == s.length()){
            res.add(ip);
        }
        for(int i = 1; i < 4; i++){
            if(index + i > s.length()){
                break;
            }
            String tmp = s.substring(index, index + i);
            if((tmp.startsWith("0") && tmp.length() > 1) || (i==3 && Integer.parseInt(tmp) >= 256)){
                continue;	//用break更好
            }
            backtrack(s, ip + tmp + (count == 3 ? "" : "."), index + i, res, count + 1);
        }
    }
    //需要确保没有前导0的情况： "010010"
    //parseInt()会把 00 -> 0  01 -> 1
    //ip.length() == len + 3 确保了答案的正确性
    public List<String> restoreIpAddresses1(String s) {
        List<String> res = new ArrayList<String>();
        int len = s.length();
        StringBuffer ip = new StringBuffer();
        for (int a=1; a<=3; a++){
            for (int b=1; b<=3; b++){
                for (int c=1; c<=3; c++){
                    int d = len - a - b - c;
                    if ( d > 0 && d <= 3) {
                        int A = Integer.parseInt(s.substring(0, a));
                        int B = Integer.parseInt(s.substring(a, a+b));
                        int C = Integer.parseInt(s.substring(a+b, a+b+c));
                        int D = Integer.parseInt(s.substring(a+b+c));
                        if (A<=255 && B<=255 && C<=255 && D<=255){
                            ip.append(A).append(".").append(B).append(".").append(C).append(".").append(D);
                            if(ip.length() == len + 3){
                                res.add(ip.toString());
                            }
                            ip.delete(0, ip.length() + 1);	//ip = new StringBuilder();
                        }
                    }
                }
            }
        }
        return res;
    }
}
