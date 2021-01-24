package challenge.dec;
// Can Place Flowers
public class Q605 {

    public static void main(String[] args) {
        Q605 q = new Q605();
        int[] bed = {1,0,0,0,1};
        System.out.println(q.canPlaceFlowers(bed, 1));  //true
        System.out.println(q.canPlaceFlowers(bed, 2));  //false
    }
    // O(n) O(1)
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for(int i = 0; i < flowerbed.length; i++) {
            if(flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0)
                    && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                // flowerbed[i++] = 1;         //i++ to increase loop speed, no need reset to 1
                i++;
                count++;
            }
            if(count >= n) {
                return true;
            }
        }
        return false;
    }
}
