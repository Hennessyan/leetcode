package google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Car Fleet
public class Q853 {
    // O(nlgn) O(n)
    public int carFleet(int target, int[] position, int[] speed) {
        if(position == null || position.length == 0) {
            return 0;
        }
        List<Car> list = new ArrayList<>();
        int total = position.length;
        for(int i = 0; i < total; i++) {
            list.add(new Car(target, position[i], speed[i]));
        }
        Collections.sort(list, (c1, c2) -> c1.p - c2.p);
        for(int i = list.size() - 1; i > 0; i--) {
            Car c1 = list.get(i - 1);
            Car c2 = list.get(i);
            if(c1.time <= c2.time) {
                total--;
                c1.time = c2.time;
            }
        }
        return total;
    }

    class Car {
        int p;
        double time;
        public Car(int target, int p, int speed) {
            this.p = p;
            time = (target - p + 0.0) / speed;
        }
    }
}
