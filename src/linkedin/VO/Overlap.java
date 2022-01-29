package linkedin.VO;

import java.util.*;

public class Overlap {

    public static void main(String[] args) {
        List<String> logs = Arrays.asList("abc,START,100",
                "def,START,150",
                "bla,START,160",
                "hij,START,170",
                "hij,END,200",
                "bla,END,200",
                "def,END,220",
                "job4,START,230",
                "job4,END,250",
                "abc,END,300");
        Overlap q = new Overlap();
        Map<String, int[]> res = q.calculate(logs);
        for(String key : res.keySet()) {
            int[] tmp = res.get(key);
            System.out.println(key + " " + tmp[0] + " " + tmp[1]);
        }
    }
    public Map<String, int[]> calculate(List<String> logs) {
        Map<String, int[]> res = new HashMap<>();
        Deque<Log> stack = new ArrayDeque<>();
        for(String l : logs) {
            Log log = convert(l);
            if(log.isStart) {
                stack.push(log);
            } else {
                Log startPoint = stack.pop();
                int time = log.time - startPoint.time;
                if(!res.containsKey(log.func)) {
                    res.put(log.func, new int[2]);
                }
                int[] cost = res.get(log.func);
                cost[0] += time;
                cost[1] += time;
                if(!stack.isEmpty()) {
                    Log preStart = stack.peek();
                    if(!res.containsKey(preStart.func)) {
                        res.put(preStart.func, new int[2]);
                    }
                    res.get(preStart.func)[0] -= time;
                }
            }
        }
        return res;
    }
    private Log convert(String s) {
        String[] arr = s.split(",");
        return new Log(arr[0], arr[1].equals("START"), Integer.parseInt(arr[2]));
    }

    class Log {
        String func;
        boolean isStart;
        int time;
        public Log(String func, boolean isStat, int time) {
            this.func = func;
            this.isStart = isStat;
            this.time = time;
        }
    }
}
