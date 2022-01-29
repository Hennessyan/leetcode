package linkedin.VO;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Traffic Light Controlled Intersection
public class Q1279 {

    class TrafficLight {

        private Lock lock;
        private volatile int flag;
        public TrafficLight() {
            lock = new ReentrantLock();;
            flag = 1;   // road with green light
        }

        public void carArrived(
                int carId,           // ID of the car
                int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
                int direction,       // Direction of the car
                Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
                Runnable crossCar    // Use crossCar.run() to make car cross the intersection
        ) {
            lock.lock();
            try {
                if(flag != roadId) {
                    turnGreen.run();
                    flag = 3 - flag;
                }

//                if(!(direction <= 2 && flag == 1 || direction > 2 && flag == 0)) {
//                    turnGreen.run();
//                    flag ^= 1;
//                }
                crossCar.run();
            } finally {
                lock.unlock();
            }
        }
    }
}
