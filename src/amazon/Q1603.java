package amazon;
// Design Parking System
public class Q1603 {

    class ParkingSystem {
        int[] pls;
        public ParkingSystem(int big, int medium, int small) {
            pls = new int[3];
            pls[0] = big;
            pls[1] = medium;
            pls[2] = small;
        }

        public boolean addCar(int carType) {
            if(pls[carType - 1] == 0) {
                return false;
            }
            pls[carType - 1]--;
            return true;
        }
    }
}
