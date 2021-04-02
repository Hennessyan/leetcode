package microsoft;

import java.util.ArrayList;
import java.util.List;

// Gray Code
public class Q89 {

    /*method1*/
    //使用这种,因为可以合理的解释原因
    //00,01,11,10 -> (000,001,011,010 ) (110,111,101,100)
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);							//也判断了针对0的情况
        if(n == 0){
            return list;
        }
        for(int i = 0; i < n; i++){
            int size = list.size();
            for(int j = size - 1; j >= 0; j--){
                list.add(list.get(j) | 1 << i);
                //list.add(list.get(j) + (1 << i));	//如果用加号,就需要注意运算优先级,加括号
            }
        }
        return list;
    }

    public List<Integer> grayCode1(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        backtrack(0, n, list);
        return list;
    }
    private void backtrack(int i, int n, List<Integer> list) {
        if(i < n) {
            int size = list.size();
            for(int j = size - 1; j >= 0; j--) {
                list.add(list.get(j) | 1 << i);
            }
            backtrack(i + 1, n, list);
        }
    }
}
