package google;

import java.util.HashSet;
import java.util.Set;

// Longest Duplicate Substring
public class Q1044 {

    public static void main(String[] args) {
        Q1044 q = new Q1044();
        System.out.println(q.longestDupSubstring("banana"));    // "ana"
        System.out.println(q.longestDupSubstring("abcd"));      // ""
    }
    // this method will get wrong result based on long test case, use method2 / 3
    //Rabin-Karp algorithm
    // O(nlgn)
    // SC - 理想状况下重复使用MEMORY -> O(n).
    private static final long MOD = (long)Math.pow(2, 32);
    public String longestDupSubstring(String S) {
        int n = S.length();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = (int)S.charAt(i) - (int)'a';
        }
        int l = 1, r = n;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(findDup(arr, m) != -1) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        int start = findDup(arr, l - 1);        // start = 1, l = 1 if S = "abcd"
        return S.substring(start, start + l - 1);
    }
    //把一些固定重复使用的local variable当作参数传入更好一些.
    // in real life, if modulus is too small and string is too long, will cause O(LN) SC for Rabin-Karp..
    private int findDup(int[] arr, int len) {
        int n = arr.length;
        // Can't use Integer.MAX_VALUE as MOD
        Set<Long> set = new HashSet<>();
        long value = 0l;
        long base = 1l;
        for(int i = 0; i < len; i++) {
            value = (value * 26 + arr[i]) % MOD;
            base  = (base * 26) % MOD;
        }
        set.add(value);
        for(int i = 1; i < n - len + 1; i++) {
            // rolling hash - O(1)
            //  h = (h - s.charAt(i-1)*pow + MOD) % MOD;   -->  maybe negative value too large that plus a MOD cannot make it positive
            //  h = (h - s.charAt(i-1)*pow%MOD) % MOD;  --> can be negative
            // correct version
            value = (value * 26 - base * arr[i - 1] % MOD + MOD) % MOD;
            value = (value + arr[i + len - 1]) % MOD;
            if(set.contains(value)) {
                return i;
            }
            set.add(value);
        }
        return -1;
    }
    // method2
    public String longestDupSubstring2(String s) {
        int n = s.length(), l = 1, r = n;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(find(s, m) != -1) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        int start = find(s, l - 1);
        return s.substring(start, start + l - 1);
    }
    private int find(String s,  int len) {
        Set<Long> set = new HashSet<>();
        long value = 0l, base = 1l;
        for(int i = 0; i < len; i++) {
            value = value * 31 + s.charAt(i);
            base *= 31;
        }
        set.add(value);
        for(int i = 1; i < s.length() - len + 1; i++) {
            value = value * 31 - s.charAt(i - 1) * base + s.charAt(i + len - 1);
            if(!set.add(value)) {
                return i;
            }
        }
        return -1;
    }
    // method3
    public String longestDupSubstring3(String s) {
        int left=1;
        int right=s.length()-1;

        String result="";
        while(left<=right){
            int mid=left + (right-left)/2;

            String str=rabinKarp(s,mid);
            if(str.length()!=0){
                result=str;
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return result;
    }

    private String rabinKarp(String s,int len){
        Set<Long> set=new HashSet<>();
        long h=hash(s.substring(0,len));
        set.add(h);
        long pow=1;
        for(int l=1;l<len;l++) pow*=31;

        for(int i=1;i<=s.length()-len;i++){
            h=nextHash(pow,h,s.charAt(i-1),s.charAt(i+len-1));
            if(set.contains(h)){
                return s.substring(i,i+len);
            }
            set.add(h);
        }
        return "";
    }

    private long nextHash(long pow,long hash,char left,char right){
        return (hash - left*pow)*31 + right;
        // abcd   bcdf
    }

    private long hash(String s) {
        long hash = 0;
        long mul=1;
        for (int i = s.length()-1; i >=0; i--) {
            hash += s.charAt(i)*mul;
            mul*=31;
        }
        return hash;
    }
}
