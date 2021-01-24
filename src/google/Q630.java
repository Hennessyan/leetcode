package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// Course Schedule III
public class Q630 {

    public static void main(String[] args) {
        Q630 q = new Q630();
        int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
        System.out.println(q.scheduleCourse(courses));
    }

    //注意这道题的特点是:之前选择的course如果duration>当前想选择的,可以直接替换,
    //因为替换后的课程必定会满足约束条件(详细分析见approach3第五段)
    //O(n*count) O(1)   count is final number of courses (don't use n^2)
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> (a[1] - b[1]));
        int count = 0, time = 0;
        for(int i = 0; i < courses.length; i++) {
            if(time + courses[i][0] <= courses[i][1]) {
                time += courses[i][0];
                courses[count++] = courses[i];
            } else {
                int max = i;
                for(int j = 0; j < count; j++) {
                    if(courses[j][0] > courses[max][0]) {
                        max = j;
                    }
                }
                if(courses[max][0] > courses[i][0]) {
                    time += courses[i][0] - courses[max][0];
                    courses[max] = courses[i];
                }
            }
        }
        return count;
    }
    //O(n*m) O(n)
    public int scheduleCourse1(int[][] courses) {
        Arrays.sort(courses, (a,b) -> (a[1] - b[1]));
        List<Integer> list = new ArrayList<>();
        int time = 0;
        for(int i = 0; i < courses.length; i++) {
            if(time + courses[i][0] <= courses[i][1]) {
                time += courses[i][0];
                list.add(courses[i][0]);
            } else if(list.size() > 0){	//注意使用了list与method1的区别
                int max = 0;
                for(int j = 1; j < list.size(); j++) {
                    if(list.get(j) > list.get(max)) {
                        max = j;
                    }
                }
                if(list.get(max) > courses[i][0]) {
                    time += courses[i][0] - list.get(max);
                    list.set(max, courses[i][0]);
                }
            }
        }
        return list.size();
    }
    //O(nlgn) O(n)
    public int scheduleCourse2(int[][] courses) {
        Arrays.sort(courses, (a,b) -> (a[1] - b[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int time = 0;
        for(int[] c : courses) {
            if(time + c[0] <= c[1]) {
                time += c[0];
                pq.add(c[0]);
            } else if(!pq.isEmpty() && pq.peek() > c[0]) {
                time += c[0] - pq.poll();
                pq.add(c[0]);
            }
        }
        return pq.size();
    }
}
