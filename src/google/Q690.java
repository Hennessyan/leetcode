package google;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

// Employee Importance
public class Q690 {
    // O(n) O(n)
    Map<Integer, Employee> emap;
    public int getImportance(List<Employee> employees, int queryid) {
        emap = new HashMap();
        for (Employee e: employees) emap.put(e.id, e);
        return dfs(queryid);
    }
    public int dfs(int eid) {
        Employee employee = emap.get(eid);
        int ans = employee.importance;
        for (Integer subid: employee.subordinates)
            ans += dfs(subid);
        return ans;
    }

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    Map<Integer, Employee> map;
    public int getImportance1(List<Employee> employees, int id) {
        map = new HashMap<>();
        for(Employee employee : employees) {
            map.put(employee.id, employee);
        }
        int total = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(id);
        while(!stack.isEmpty()) {
            Employee e = map.get(stack.pop());
            total += e.importance;
            for(int v : e.subordinates) {
                stack.push(v);
            }
        }
        return total;
    }
}
